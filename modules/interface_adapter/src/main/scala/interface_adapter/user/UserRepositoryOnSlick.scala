package interface_adapter.user

import domain.user._
import domain.ActiveStatus
import interface_adapter.Tables
import slick.jdbc.{JdbcProfile, PostgresProfile}
import slick.jdbc.PostgresProfile.api._
import use_case.user.{UserError => _, _}

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryOnSlick extends UserRepository with Tables {

  override val profile: JdbcProfile = PostgresProfile

  private val db = Database.forConfig("slick")

  override def insert(
    user: User
  )(implicit ec: ExecutionContext): Future[Either[UserError, Unit]] = {
    val id = findById(user.id)
    val email = findByEmailAddress(user.emailAddress)

    val error: Future[Either[UserError, User]] = for {
      e1 <- id
      e2 <- email
      dupId = e1.left.map(_ => DuplicatedIdError)
      dupEmail = e2.left.map(_ => DuplicatedEmailAddressError)
    } yield dupId.left.flatMap(_ => dupEmail)

    error.transform(
      v =>
        v.map { _ =>
          db.run(Users += entityToRow(user))
          ()
      },
      e => UserInsertFailedException(user.id, e)
    )
  }

  override def findById(
    id: UserId
  )(implicit ec: ExecutionContext): Future[Either[UserError, User]] =
    db.run(Users.findBy(_.id).applied(id.asUuid).result.headOption)
      .transform(
        v => Either.cond(v.isDefined, rowToEntity(v.get), NotFoundIdError),
        e => UserFindByIdFailedException(id, e)
      )

  override def findByEmailAddress(
    emailAddress: UserEmailAddress
  )(implicit ec: ExecutionContext): Future[Either[UserError, User]] =
    db.run(
        Users
          .findBy(_.emailAddress)
          .applied(emailAddress.asString)
          .result
          .headOption
      )
      .transform(
        v =>
          Either
            .cond(v.isDefined, rowToEntity(v.get), NotFoundEmailAddressError),
        e => UserFindByEmailAddressFailedException(emailAddress, e)
      )

  override def updateEmailAddress(id: UserId, emailAddress: UserEmailAddress)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption)
      .transform(
        v =>
          Either.cond(v.isDefined, {
            db.run(query.map(_.emailAddress).update(emailAddress.asString))
            ()
          }, NotFoundIdError),
        e => UserUpdateEmailAddressFailedException(id, e)
      )
  }

  override def updatePassword(
    id: UserId,
    encryptedPassword: UserEncryptedPassword,
    passwordSalt: UserPasswordSalt
  )(implicit ec: ExecutionContext): Future[Either[UserError, Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption)
      .transform(
        v =>
          Either.cond(v.isDefined, {
            db.run(
              query
                .map(u => (u.encryptedPassword, u.passwordSalt))
                .update(encryptedPassword.asString, passwordSalt.asString)
            )
            ()
          }, NotFoundIdError),
        e => UserUpdatePasswordFailedException(id, e)
      )
  }

  override def deleteById(
    id: UserId
  )(implicit ec: ExecutionContext): Future[Either[UserError, Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption)
      .transform(
        v =>
          Either.cond(v.isDefined, {
            db.run(query.delete)
            ()
          }, NotFoundIdError),
        e => UserDeleteFailedException(id, e)
      )
  }

  private def entityToRow(user: User): UsersRow =
    UsersRow(
      id = user.id.asUuid,
      emailAddress = user.emailAddress.asString,
      encryptedPassword = user.encryptedPassword.asString,
      passwordSalt = user.passwordSalt.asString,
      isActive = user.activeStatus.asBoolean,
      createdAt = user.createdAt,
      updatedAt = user.updatedAt
    )

  private def rowToEntity(row: Users#TableElementType): User =
    User(
      id = UserId(row.id),
      emailAddress = UserEmailAddress(row.emailAddress) match {
        case Right(v) => v
        case Left(_)  => throw new RuntimeException
      },
      encryptedPassword = UserEncryptedPassword(row.encryptedPassword) match {
        case Right(v) => v
        case Left(_)  => throw new RuntimeException
      },
      passwordSalt = UserPasswordSalt(row.passwordSalt),
      activeStatus = ActiveStatus.fromBoolean(row.isActive),
      createdAt = row.createdAt,
      updatedAt = row.updatedAt
    )
}
