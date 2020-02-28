package domain.models.tag

import io.jvm.uuid._

final case class TagId(private val value: UUID = UUID.random) {
  def asString: String = value.string
}
