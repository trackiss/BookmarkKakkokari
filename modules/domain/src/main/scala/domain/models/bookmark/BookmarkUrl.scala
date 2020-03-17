package domain.models.bookmark

import domain.error.BookmarkError

final case class BookmarkUrl private (private val value: String) {
  def asString: String = value
}

object BookmarkUrl {
  def apply(value: String): Either[BookmarkError, BookmarkUrl] =
    for {
      e1 <- Either.cond(
        value.forall(c => 0x20 < c && c < 0x7f),
        new BookmarkUrl(value),
        BookmarkError.InvalidChracterUrlError
      )
      e2 <- Either.cond(
        value.matches("""[^\s]+:[^\s]+"""),
        e1,
        BookmarkError.InvalidUrlError
      )
    } yield e2
}
