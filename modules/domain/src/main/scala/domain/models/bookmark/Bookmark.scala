package domain.models.bookmark

import java.time.Instant

import domain.models.tag.Tags
import domain.models.user.UserId
import domain.models.ActiveStatus

final class Bookmark(id: BookmarkId,
                     title: BookmarkTitle,
                     url: BookmarkUrl,
                     comment: BookmarkComment,
                     tags: Tags,
                     favoriteStatus: BookmarkFavoriteStatus,
                     ownerId: UserId,
                     activeStatus: ActiveStatus,
                     createdAt: Instant,
                     updatedAt: Instant)
