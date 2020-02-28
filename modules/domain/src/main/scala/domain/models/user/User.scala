package domain.models.user

import java.time.Instant

final case class User(id: UserId,
                      emailAddress: UserEmailAddress,
                      encryptedPassword: UserEncryptedPassword,
                      passwordSalt: UserPasswordSalt,
                      activeStatus: UserActiveStatus,
                      createdAt: Instant,
                      updatedAt: Instant) {
  def authenticate(password: UserPlainPassword): Boolean =
    encryptedPassword.authenticate(password)
}
