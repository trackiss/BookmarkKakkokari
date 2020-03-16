package domain.bookmark

sealed trait BookmarkError

case object InvalidChracterUrlError extends BookmarkError
case object InvalidUrlError extends BookmarkError
