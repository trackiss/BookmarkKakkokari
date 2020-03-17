package domain.models.user

import domain.error.UserError

final case class UserEmailAddress private (private val value: String) {
  def asString: String = value
}

object UserEmailAddress {
  def apply(value: String): Either[UserError, UserEmailAddress] =
    Either.cond(
      value.matches("""[^\s]+@[^\s]+"""),
      new UserEmailAddress(value),
      UserError.InvalidEmailAddressError
    )
}
