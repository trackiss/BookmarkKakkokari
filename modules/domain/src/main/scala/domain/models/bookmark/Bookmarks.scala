package domain.models.bookmark

final case class Bookmarks(private val values: Set[Bookmark]) {
  def size: Int = values.size
  def contains(value: Bookmark): Boolean = values.contains(value)
  def exists(p: Bookmark => Boolean): Boolean = values.exists(p)
  def find(p: Bookmark => Boolean): Option[Bookmark] = values.find(p)
  def isEmpty: Boolean = values.isEmpty
  def nonEmpty: Boolean = values.nonEmpty
}

object Bookmarks {
  def empty: Bookmarks = Bookmarks(Set.empty)
}
