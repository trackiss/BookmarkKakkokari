package use_case.repository

import domain.models.user._
import use_case.repository.error.UserError

import scala.concurrent.{ExecutionContext, Future}

trait UserRepository {
  def insert(user: User)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, Unit]]

  def findById(id: UserId)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, User]]

  def findByEmailAddress(emailAddress: UserEmailAddress)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, User]]

  def updateEmailAddress(id: UserId, emailAddress: UserEmailAddress)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, Unit]]

  def updatePassword(
    id: UserId,
    encryptedPassword: UserEncryptedPassword,
    passwordSalt: UserPasswordSalt
  )(implicit ec: ExecutionContext): Future[Either[UserError, Unit]]

  def deleteById(id: UserId)(
    implicit ec: ExecutionContext
  ): Future[Either[UserError, Unit]]
}
