package use_case.repository

import domain.models.tag.{Tag, TagId, Tags}
import domain.models.user.UserId
import use_case.error.TagError

import scala.concurrent.Future

trait TagRepository {
  def insert(tag: Tag): Future[Either[TagError, Unit]]

  def findById(id: TagId): Future[Either[TagError, Tag]]

  def findByUserId(id: UserId): Future[Either[TagError, Tags]]

  def findByBookmarkId
}
