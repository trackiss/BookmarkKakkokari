package use_case.error

sealed trait UserError

case object ExistedIdError extends UserError
case object ExistedEmailAddressError extends UserError
