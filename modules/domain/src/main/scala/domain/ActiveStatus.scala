package domain

sealed trait ActiveStatus {
  def asBoolean: Boolean
}

object ActiveStatus {
  def fromBoolean(value: Boolean): ActiveStatus =
    if (value) Active else InActive
}

case object InActive extends ActiveStatus {
  override def asBoolean: Boolean = false
}

case object Active extends ActiveStatus {
  override def asBoolean: Boolean = true
}
