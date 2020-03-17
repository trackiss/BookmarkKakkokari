package domain.models.bookmark

final case class BookmarkComment(private val value: String) {
  def asString: String = value
}
