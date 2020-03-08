package interface_adapter.exception

import domain.models.user.{UserEmailAddress, UserId}

sealed abstract class UserException(message: String, cause: Throwable)
    extends RuntimeException(message, cause)

case class UserInsertFailedException(id: UserId, cause: Throwable)
    extends UserException("UserId: " + id.asString, cause)

case class UserFindByIdFailedException(id: UserId, cause: Throwable)
    extends UserException("UserId: " + id.asString, cause)

case class UserFindByEmailAddressFailedException(emailAddress: UserEmailAddress,
                                                 cause: Throwable)
    extends UserException("UserEmailAddress: " + emailAddress.asString, cause)

case class UserUpdateEmailAddressFailedException(id: UserId, cause: Throwable)
    extends UserException("UserId: " + id.asString, cause)

case class UserUpdatePasswordFailedException(id: UserId, cause: Throwable)
    extends UserException("UserId: " + id.asString, cause)

case class UserDeleteFailedException(id: UserId, cause: Throwable)
    extends UserException("UserId: " + id.asString, cause)
