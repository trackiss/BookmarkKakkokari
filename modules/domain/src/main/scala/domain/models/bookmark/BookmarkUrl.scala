package domain.models.bookmark

final case class BookmarkUrl(private val value: String) {
  require(value.forall(c => 0x20 < c && c < 0x7f))
  require(value.matches("""[^\s]+:[^\s]+"""))

  def asString: String = value
}
