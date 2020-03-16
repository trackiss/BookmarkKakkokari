package domain.user

import java.time.Instant

import domain.ActiveStatus

final case class User(id: UserId,
                      emailAddress: UserEmailAddress,
                      encryptedPassword: UserEncryptedPassword,
                      passwordSalt: UserPasswordSalt,
                      activeStatus: ActiveStatus,
                      createdAt: Instant,
                      updatedAt: Instant) {
  def authenticate(password: UserPlainPassword): Boolean =
    encryptedPassword.authenticate(password)
}
