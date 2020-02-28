package domain.models.tag

import java.time.Instant

import domain.models.user.UserId
import domain.models.ActiveStatus

case class Tag(id: TagId,
               name: TagName,
               ownerId: UserId,
               activeStatus: ActiveStatus,
               createdAt: Instant,
               updatedAt: Instant)
