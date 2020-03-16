package use_case.user

sealed trait UserError

case object DuplicatedIdError extends UserError
case object DuplicatedEmailAddressError extends UserError
case object NotFoundIdError extends UserError
case object NotFoundEmailAddressError extends UserError
