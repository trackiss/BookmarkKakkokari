package domain.models.tag

import java.time.Instant

import domain.models.user.UserId
import domain.models.ActiveStatus
import domain.models.bookmark.BookmarkId

final case class Tag(id: TagId,
                     name: TagName,
                     ownerId: UserId,
                     bookmarkId: BookmarkId,
                     activeStatus: ActiveStatus,
                     createdAt: Instant,
                     updatedAt: Instant)
