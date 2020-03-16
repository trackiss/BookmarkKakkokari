package domain.tag

final case class Tags(private val values: Set[Tag]) {
  def size: Int = values.size
  def contains(value: Tag): Boolean = values.contains(value)
  def exists(p: Tag => Boolean): Boolean = values.exists(p)
  def find(p: Tag => Boolean): Option[Tag] = values.find(p)
  def isEmpty: Boolean = values.isEmpty
  def nonEmpty: Boolean = values.nonEmpty
}

object Tags {
  def empty: Tags = Tags(Set.empty)
}
