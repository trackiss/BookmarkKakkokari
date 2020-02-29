package domain.models.user

import domain.exception.{InvalidEmailAddressException, UserException}

final case class UserEmailAddress private (private val value: String) {
  def asString: String = value
}

object UserEmailAddress {
  def apply(value: String): Either[UserException, UserEmailAddress] =
    Either.cond(
      value.matches("""[^\s]+@[^\s]+"""),
      new UserEmailAddress(value),
      InvalidEmailAddressException
    )
}
