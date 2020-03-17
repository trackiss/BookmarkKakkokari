package domain.error

sealed trait UserError

object UserError {
  case object InvalidEmailAddressError extends UserError
  case object IllegalEncryptedPasswordError extends UserError
  case object InvalidCharacterPasswordError extends UserError
  case object NotEnoughLengthPasswordError extends UserError
}
