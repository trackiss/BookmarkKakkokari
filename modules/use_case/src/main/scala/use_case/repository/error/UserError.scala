package use_case.repository.error

sealed trait UserError

case object DuplicatedIdError extends UserError
case object DuplicatedEmailAddressError extends UserError
case object NotFoundIdError extends UserError
