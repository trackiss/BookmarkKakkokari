package domain.models.user

import com.github.t3hnar.bcrypt._
import domain.exception.{
  InvalidCharacterPasswordExcetion,
  NotEnoughLengthPasswordException,
  UserException
}

final case class UserPlainPassword private (private val value: String) {
  private[domain] def encrypt(salt: UserPasswordSalt): UserEncryptedPassword =
    UserEncryptedPassword(value.bcrypt(salt.asString)) match {
      case Right(v) => v
      case Left(_)  => throw new RuntimeException
    }

  def asString: String = value
}

object UserPlainPassword {
  def apply(value: String): Either[UserException, UserPlainPassword] =
    for {
      e1 <- Either.cond(
        value.forall(c => 0x20 < c && c < 0x7f),
        new UserPlainPassword(value),
        InvalidCharacterPasswordExcetion
      )
      e2 <- Either.cond(value.length >= 4, e1, NotEnoughLengthPasswordException)
    } yield e2
}
