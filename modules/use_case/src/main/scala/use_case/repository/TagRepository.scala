package use_case.repository

import domain.models.bookmark.BookmarkId
import domain.models.tag.{Tag, TagId, TagName, Tags}
import domain.models.user.UserId
import use_case.repository.error.TagError

import scala.concurrent.{ExecutionContext, Future}

trait TagRepository {
  def insert(tag: Tag)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]

  def findById(id: TagId)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Tag]]

  def findByUserId(userId: UserId)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Tags]]

  def findByUserIdWithName(userId: UserId, name: TagName)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Tag]]

  def findByUserIdWithKeyword(userId: UserId, keyword: String)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Tags]]

  def findByBookmarkId(bookmarkId: BookmarkId)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Tags]]

  def updateName(userId: UserId, oldName: TagName, newName: TagName)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]

  def deleteById(id: TagId)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]

  def deleteByName(userId: UserId, name: TagName)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]

  def deleteByBookmarkId(bookmarkId: BookmarkId)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]

  def deleteByBookmarkIdWithName(bookmarkId: BookmarkId, name: TagName)(
    implicit ec: ExecutionContext
  ): Future[Either[TagError, Unit]]
}
