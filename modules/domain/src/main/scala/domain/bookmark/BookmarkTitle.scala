package domain.bookmark

final case class BookmarkTitle(private val value: String) {
  def asString: String = value
}
