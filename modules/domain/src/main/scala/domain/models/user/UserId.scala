package domain.models.user

import io.jvm.uuid._

final case class UserId(private val value: UUID = UUID.random) {
  def asString: String = value.string
  def asUuid: UUID = value
}
