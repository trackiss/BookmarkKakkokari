package domain.models.bookmark

import io.jvm.uuid._

final case class BookmarkId(private val value: UUID = UUID.random) {
  def asString: String = value.string
  def asUuid: UUID = value
}
