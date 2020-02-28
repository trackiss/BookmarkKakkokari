package domain.models.user

final case class UserEmailAddress(private val value: String) {
  require(value.matches("""[^\s]+@[^\s]+"""))

  def asString: String = value
}
