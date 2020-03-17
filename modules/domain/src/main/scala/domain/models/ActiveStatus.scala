package domain.models

sealed trait ActiveStatus {
  def asBoolean: Boolean
}

object ActiveStatus {
  case object InActive extends ActiveStatus {
    override def asBoolean: Boolean = false
  }

  case object Active extends ActiveStatus {
    override def asBoolean: Boolean = true
  }

  def fromBoolean(value: Boolean): ActiveStatus =
    if (value) Active else InActive
}
