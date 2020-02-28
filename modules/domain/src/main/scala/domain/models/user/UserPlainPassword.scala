package domain.models.user

import com.github.t3hnar.bcrypt._

final case class UserPlainPassword(private val value: String) {
  require(value.forall(c => 0x20 < c && c < 0x7f))
  require(value.length >= 4)

  private[domain] def encrypt(salt: UserPasswordSalt): UserEncryptedPassword =
    UserEncryptedPassword(value.bcrypt(salt.asString))

  def asString: String = value
}
