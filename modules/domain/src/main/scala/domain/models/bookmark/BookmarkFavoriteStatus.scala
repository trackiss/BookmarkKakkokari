package domain.models.bookmark

sealed trait BookmarkFavoriteStatus

object BookmarkFavoriteStatus {
  case object Normal extends BookmarkFavoriteStatus {
    def asBoolean: Boolean = false
  }

  case object Favorited extends BookmarkFavoriteStatus {
    def asBoolean: Boolean = true
  }

  def fromBoolean(value: Boolean): BookmarkFavoriteStatus =
    if (value) Favorited else Normal
}
