package domain.user

import com.github.t3hnar.bcrypt._

final case class UserPasswordSalt private[domain] (private val value: String) {
  def asString: String = value
}

object UserPasswordSalt {
  def apply: UserPasswordSalt = new UserPasswordSalt(generateSalt)
}
