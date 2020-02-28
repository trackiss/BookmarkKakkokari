package domain.models.user

import com.github.t3hnar.bcrypt._

import scala.util.Success

final case class UserEncryptedPassword private[domain] (
  private val value: String
) {
  require(value.startsWith("""$2a$10"""))

  def authenticate(password: UserPlainPassword): Boolean = {
    password.asString.isBcryptedSafe(value) match {
      case Success(true) => true
      case _             => false
    }
  }

  private[domain] def asString: String = value
}
