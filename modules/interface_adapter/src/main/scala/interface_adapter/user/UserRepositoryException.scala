package interface_adapter.user

import domain.user.{UserEmailAddress, UserId}

sealed abstract class UserRepositoryException(message: String, cause: Throwable)
    extends RuntimeException(message, cause)

case class UserInsertFailedException(id: UserId, cause: Throwable)
    extends UserRepositoryException("UserId: " + id.asString, cause)

case class UserFindByIdFailedException(id: UserId, cause: Throwable)
    extends UserRepositoryException("UserId: " + id.asString, cause)

case class UserFindByEmailAddressFailedException(emailAddress: UserEmailAddress,
                                                 cause: Throwable)
    extends UserRepositoryException(
      "UserEmailAddress: " + emailAddress.asString,
      cause
    )

case class UserUpdateEmailAddressFailedException(id: UserId, cause: Throwable)
    extends UserRepositoryException("UserId: " + id.asString, cause)

case class UserUpdatePasswordFailedException(id: UserId, cause: Throwable)
    extends UserRepositoryException("UserId: " + id.asString, cause)

case class UserDeleteFailedException(id: UserId, cause: Throwable)
    extends UserRepositoryException("UserId: " + id.asString, cause)
