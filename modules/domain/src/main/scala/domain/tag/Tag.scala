package domain.tag

import java.time.Instant

import domain.user.UserId
import domain.bookmark.BookmarkId
import domain.ActiveStatus

final case class Tag(id: TagId,
                     name: TagName,
                     ownerId: UserId,
                     bookmarkId: BookmarkId,
                     activeStatus: ActiveStatus,
                     createdAt: Instant,
                     updatedAt: Instant)
