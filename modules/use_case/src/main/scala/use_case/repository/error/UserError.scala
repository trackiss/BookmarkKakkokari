package use_case.repository.error

sealed trait UserError

object UserError {
  case object DuplicatedIdError extends UserError
  case object DuplicatedEmailAddressError extends UserError
  case object NotFoundIdError extends UserError
  case object NotFoundEmailAddressError extends UserError
}
