package domain.models.tag

final case class TagName(private val value: String) {
  def asString: String = value
}
