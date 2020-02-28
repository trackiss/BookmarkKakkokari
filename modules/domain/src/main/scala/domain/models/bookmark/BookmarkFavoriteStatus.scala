package domain.models.bookmark

sealed trait BookmarkFavoriteStatus

case object Normal extends BookmarkFavoriteStatus
case object Favorited extends BookmarkFavoriteStatus
