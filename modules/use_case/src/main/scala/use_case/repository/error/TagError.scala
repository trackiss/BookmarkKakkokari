package use_case.repository.error

sealed trait TagError

object TagError {
  case object DuplicatedIdError extends TagError
  case object DuplicatedUserIdAndNameError extends TagError
  case object DuplicatedBookmarkIdAndNameError extends TagError
  case object NotFoundIdError extends TagError
  case object NotFoundNameError extends TagError
  case object NotFoundUserIdError extends TagError
  case object NotFoundBookmarkIdError extends TagError
}
