package domain.bookmark

sealed trait BookmarkFavoriteStatus

object BookmarkFavoriteStatus {
  def fromBoolean(value: Boolean): BookmarkFavoriteStatus =
    if (value) Favorited else Normal
}

case object Normal extends BookmarkFavoriteStatus {
  def asBoolean: Boolean = false
}

case object Favorited extends BookmarkFavoriteStatus {
  def asBoolean: Boolean = true
}
