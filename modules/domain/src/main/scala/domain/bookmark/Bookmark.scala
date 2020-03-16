package domain.bookmark

import java.time.Instant

import domain.tag.Tags
import domain.user.UserId
import domain.ActiveStatus

final case class Bookmark(id: BookmarkId,
                          title: BookmarkTitle,
                          url: BookmarkUrl,
                          comment: BookmarkComment,
                          tags: Tags,
                          favoriteStatus: BookmarkFavoriteStatus,
                          ownerId: UserId,
                          activeStatus: ActiveStatus,
                          createdAt: Instant,
                          updatedAt: Instant)
