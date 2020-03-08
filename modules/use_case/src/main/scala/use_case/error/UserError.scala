package use_case.error

sealed trait UserError

case object DuplicatedIdError extends UserError
case object DuplicatedEmailAddressError extends UserError
case object NotFoundIdError extends UserError
