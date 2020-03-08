package interface_adapter.repository

import domain.models.user._
import domain.models.ActiveStatus
import interface_adapter.Tables
import interface_adapter.exception._
import slick.jdbc.{JdbcProfile, PostgresProfile}
import slick.jdbc.PostgresProfile.api._
import use_case.error.{
  DuplicatedEmailAddressError,
  DuplicatedIdError,
  UserError
}
import use_case.repository.UserRepository

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class UserRepositoryOnSlick extends UserRepository with Tables {

  override val profile: JdbcProfile = PostgresProfile

  private val db = Database.forConfig("slick")

  override def insert(
    user: User
  )(implicit ec: ExecutionContext): Future[Either[UserError, Unit]] = {
    val error: Future[Option[UserError]] = for {
      e1 <- findById(user.id)
      e2 <- findByEmailAddress(user.emailAddress)
    } yield
      if (e1.isDefined)
        Some(DuplicatedIdError)
      else if (e2.isDefined)
        Some(DuplicatedEmailAddressError)
      else
        None

    error.transformWith {
      case Success(v) =>
        Future.successful(
          if (v.isDefined)
            db.run(Users += entityToRow(user)).transformWith {
              case Success(_) => Future.successful(Right(()))
              case Failure(e) =>
                Future.failed(UserInsertFailedException(user.id, e))
            } else
            Future(Left(v.get))
        )
      case Failure(e) => Future.failed(UserInsertFailedException(user.id, e))
    }.flatten
  }

  override def findById(
    id: UserId
  )(implicit ec: ExecutionContext): Future[Option[User]] =
    db.run(Users.findBy(_.id).applied(id.asUuid).result.headOption)
      .transformWith {
        case Success(v) => Future.successful(v.map(rowToEntity))
        case Failure(e) => Future.failed(UserFindByIdFailedException(id, e))
      }

  override def findByEmailAddress(
    emailAddress: UserEmailAddress
  )(implicit ec: ExecutionContext): Future[Option[User]] =
    db.run(
        Users
          .findBy(_.emailAddress)
          .applied(emailAddress.asString)
          .result
          .headOption
      )
      .transformWith {
        case Success(v) => Future.successful(v.map(rowToEntity))
        case Failure(e) =>
          Future.failed(UserFindByEmailAddressFailedException(emailAddress, e))
      }

  override def updateEmailAddress(id: UserId, emailAddress: UserEmailAddress)(
    implicit ec: ExecutionContext
  ): Future[Option[Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption).transformWith {
      case Success(v) =>
        Future.successful(v.map { _ =>
          db.run(query.map(_.emailAddress).update(emailAddress.asString))
          ()
        })
      case Failure(e) =>
        Future.failed(UserUpdateEmailAddressFailedException(id, e))
    }
  }

  override def updatePassword(
    id: UserId,
    encryptedPassword: UserEncryptedPassword,
    passwordSalt: UserPasswordSalt
  )(implicit ec: ExecutionContext): Future[Option[Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption).transformWith {
      case Success(v) =>
        Future.successful(v.map { _ =>
          db.run(
            query
              .map(u => (u.encryptedPassword, u.passwordSalt))
              .update(encryptedPassword.asString, passwordSalt.asString)
          )
          ()
        })
      case Failure(e) => Future.failed(UserUpdatePasswordFailedException(id, e))
    }
  }

  override def deleteById(
    id: UserId
  )(implicit ec: ExecutionContext): Future[Option[Unit]] = {
    val query = Users.findBy(_.id).applied(id.asUuid)

    db.run(query.result.headOption).transformWith {
      case Success(v) =>
        Future.successful(v.map { _ =>
          db.run(query.delete)
          ()
        })
      case Failure(e) => Future.failed(UserDeleteFailedException(id, e))
    }
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
