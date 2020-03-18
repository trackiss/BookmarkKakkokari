package domain.error

sealed trait BookmarkError

object BookmarkError {
  case object InvalidChracterUrlError extends BookmarkError
  case object InvalidUrlError extends BookmarkError
}
