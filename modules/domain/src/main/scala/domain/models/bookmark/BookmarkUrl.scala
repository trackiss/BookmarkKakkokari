package domain.models.bookmark

import domain.exception.{
  BookmarkException,
  InvalidChracterUrlException,
  InvalidUrlException
}

final case class BookmarkUrl private (private val value: String) {
  def asString: String = value
}

object BookmarkUrl {
  def apply(value: String): Either[BookmarkException, BookmarkUrl] =
    for {
      e1 <- Either.cond(
        value.forall(c => 0x20 < c && c < 0x7f),
        new BookmarkUrl(value),
        InvalidChracterUrlException
      )
      e2 <- Either.cond(
        value.matches("""[^\s]+:[^\s]+"""),
        e1,
        InvalidUrlException
      )
    } yield e2
}
