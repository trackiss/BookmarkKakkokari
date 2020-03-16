package interface_adapter

// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import java.time.Instant

  import io.jvm.uuid._
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema
    : profile.SchemaDescription = Bookmarks.schema ++ Tags.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Bookmarks
    *  @param id Database column id SqlType(uuid), PrimaryKey
    *  @param title Database column title SqlType(varchar)
    *  @param url Database column url SqlType(varchar)
    *  @param comment Database column comment SqlType(text)
    *  @param isFavorited Database column is_favorited SqlType(bool), Default(false)
    *  @param ownerId Database column owner_id SqlType(uuid)
    *  @param isActive Database column is_active SqlType(bool), Default(true)
    *  @param createdAt Database column created_at SqlType(timestamp)
    *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class BookmarksRow(id: UUID,
                          title: String,
                          url: String,
                          comment: String,
                          isFavorited: Boolean = false,
                          ownerId: UUID,
                          isActive: Boolean = true,
                          createdAt: Instant,
                          updatedAt: Instant)

  /** GetResult implicit for fetching BookmarksRow objects using plain SQL queries */
  implicit def GetResultBookmarksRow(implicit e0: GR[UUID],
                                     e1: GR[String],
                                     e2: GR[Boolean],
                                     e3: GR[Instant]): GR[BookmarksRow] = GR {
    prs =>
      import prs._
      BookmarksRow.tupled(
        (
          <<[UUID],
          <<[String],
          <<[String],
          <<[String],
          <<[Boolean],
          <<[UUID],
          <<[Boolean],
          <<[Instant],
          <<[Instant]
        )
      )
  }

  /** Table description of table bookmarks. Objects of this class serve as prototypes for rows in queries. */
  class Bookmarks(_tableTag: Tag)
      extends profile.api.Table[BookmarksRow](_tableTag, "bookmarks") {
    def * =
      (
        id,
        title,
        url,
        comment,
        isFavorited,
        ownerId,
        isActive,
        createdAt,
        updatedAt
      ) <> (BookmarksRow.tupled, BookmarksRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      (
        (
          Rep.Some(id),
          Rep.Some(title),
          Rep.Some(url),
          Rep.Some(comment),
          Rep.Some(isFavorited),
          Rep.Some(ownerId),
          Rep.Some(isActive),
          Rep.Some(createdAt),
          Rep.Some(updatedAt)
        )
      ).shaped.<>(
        { r =>
          import r._;
          _1.map(
            _ =>
              BookmarksRow.tupled(
                (
                  _1.get,
                  _2.get,
                  _3.get,
                  _4.get,
                  _5.get,
                  _6.get,
                  _7.get,
                  _8.get,
                  _9.get
                )
            )
          )
        },
        (_: Any) =>
          throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[UUID] = column[UUID]("id", O.PrimaryKey)

    /** Database column title SqlType(varchar) */
    val title: Rep[String] = column[String]("title")

    /** Database column url SqlType(varchar) */
    val url: Rep[String] = column[String]("url")

    /** Database column comment SqlType(text) */
    val comment: Rep[String] = column[String]("comment")

    /** Database column is_favorited SqlType(bool), Default(false) */
    val isFavorited: Rep[Boolean] =
      column[Boolean]("is_favorited", O.Default(false))

    /** Database column owner_id SqlType(uuid) */
    val ownerId: Rep[UUID] = column[UUID]("owner_id")

    /** Database column is_active SqlType(bool), Default(true) */
    val isActive: Rep[Boolean] = column[Boolean]("is_active", O.Default(true))

    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[Instant] = column[Instant]("created_at")

    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[Instant] = column[Instant]("updated_at")

    /** Foreign key referencing Users (database name bookmarks_owner_id_fkey) */
    lazy val usersFk = foreignKey("bookmarks_owner_id_fkey", ownerId, Users)(
      r => r.id,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )
  }

  /** Collection-like TableQuery object for table Bookmarks */
  lazy val Bookmarks = new TableQuery(tag => new Bookmarks(tag))

  /** Entity class storing rows of table Tags
    *  @param id Database column id SqlType(uuid), PrimaryKey
    *  @param name Database column name SqlType(varchar)
    *  @param countItem Database column count_item SqlType(int4), Default(0)
    *  @param ownerId Database column owner_id SqlType(uuid)
    *  @param bookmarkId Database column bookmark_id SqlType(uuid)
    *  @param isActive Database column is_active SqlType(bool), Default(true)
    *  @param createdAt Database column created_at SqlType(timestamp)
    *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class TagsRow(id: UUID,
                     name: String,
                     countItem: Int = 0,
                     ownerId: UUID,
                     bookmarkId: UUID,
                     isActive: Boolean = true,
                     createdAt: Instant,
                     updatedAt: Instant)

  /** GetResult implicit for fetching TagsRow objects using plain SQL queries */
  implicit def GetResultTagsRow(implicit e0: GR[UUID],
                                e1: GR[String],
                                e2: GR[Int],
                                e3: GR[Boolean],
                                e4: GR[Instant]): GR[TagsRow] = GR { prs =>
    import prs._
    TagsRow.tupled(
      (
        <<[UUID],
        <<[String],
        <<[Int],
        <<[UUID],
        <<[UUID],
        <<[Boolean],
        <<[Instant],
        <<[Instant]
      )
    )
  }

  /** Table description of table tags. Objects of this class serve as prototypes for rows in queries. */
  class Tags(_tableTag: Tag)
      extends profile.api.Table[TagsRow](_tableTag, "tags") {
    def * =
      (id, name, countItem, ownerId, bookmarkId, isActive, createdAt, updatedAt) <> (TagsRow.tupled, TagsRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      (
        (
          Rep.Some(id),
          Rep.Some(name),
          Rep.Some(countItem),
          Rep.Some(ownerId),
          Rep.Some(bookmarkId),
          Rep.Some(isActive),
          Rep.Some(createdAt),
          Rep.Some(updatedAt)
        )
      ).shaped.<>(
        { r =>
          import r._;
          _1.map(
            _ =>
              TagsRow.tupled(
                (_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)
            )
          )
        },
        (_: Any) =>
          throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[UUID] = column[UUID]("id", O.PrimaryKey)

    /** Database column name SqlType(varchar) */
    val name: Rep[String] = column[String]("name")

    /** Database column count_item SqlType(int4), Default(0) */
    val countItem: Rep[Int] = column[Int]("count_item", O.Default(0))

    /** Database column owner_id SqlType(uuid) */
    val ownerId: Rep[UUID] = column[UUID]("owner_id")

    /** Database column bookmark_id SqlType(uuid) */
    val bookmarkId: Rep[UUID] = column[UUID]("bookmark_id")

    /** Database column is_active SqlType(bool), Default(true) */
    val isActive: Rep[Boolean] = column[Boolean]("is_active", O.Default(true))

    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[Instant] = column[Instant]("created_at")

    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[Instant] = column[Instant]("updated_at")

    /** Foreign key referencing Bookmarks (database name tags_bookmark_id_fkey) */
    lazy val bookmarksFk =
      foreignKey("tags_bookmark_id_fkey", bookmarkId, Bookmarks)(
        r => r.id,
        onUpdate = ForeignKeyAction.NoAction,
        onDelete = ForeignKeyAction.NoAction
      )

    /** Foreign key referencing Users (database name tags_owner_id_fkey) */
    lazy val usersFk = foreignKey("tags_owner_id_fkey", ownerId, Users)(
      r => r.id,
      onUpdate = ForeignKeyAction.NoAction,
      onDelete = ForeignKeyAction.NoAction
    )
  }

  /** Collection-like TableQuery object for table Tags */
  lazy val Tags = new TableQuery(tag => new Tags(tag))

  /** Entity class storing rows of table Users
    *  @param id Database column id SqlType(uuid), PrimaryKey
    *  @param emailAddress Database column email_address SqlType(varchar)
    *  @param encryptedPassword Database column encrypted_password SqlType(varchar)
    *  @param passwordSalt Database column password_salt SqlType(varchar)
    *  @param countItem Database column count_item SqlType(int4), Default(0)
    *  @param isActive Database column is_active SqlType(bool), Default(true)
    *  @param createdAt Database column created_at SqlType(timestamp)
    *  @param updatedAt Database column updated_at SqlType(timestamp) */
  case class UsersRow(id: UUID,
                      emailAddress: String,
                      encryptedPassword: String,
                      passwordSalt: String,
                      countItem: Int = 0,
                      isActive: Boolean = true,
                      createdAt: Instant,
                      updatedAt: Instant)

  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[UUID],
                                 e1: GR[String],
                                 e2: GR[Int],
                                 e3: GR[Boolean],
                                 e4: GR[Instant]): GR[UsersRow] = GR { prs =>
    import prs._
    UsersRow.tupled(
      (
        <<[UUID],
        <<[String],
        <<[String],
        <<[String],
        <<[Int],
        <<[Boolean],
        <<[Instant],
        <<[Instant]
      )
    )
  }

  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag)
      extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * =
      (
        id,
        emailAddress,
        encryptedPassword,
        passwordSalt,
        countItem,
        isActive,
        createdAt,
        updatedAt
      ) <> (UsersRow.tupled, UsersRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? =
      (
        (
          Rep.Some(id),
          Rep.Some(emailAddress),
          Rep.Some(encryptedPassword),
          Rep.Some(passwordSalt),
          Rep.Some(countItem),
          Rep.Some(isActive),
          Rep.Some(createdAt),
          Rep.Some(updatedAt)
        )
      ).shaped.<>(
        { r =>
          import r._;
          _1.map(
            _ =>
              UsersRow.tupled(
                (_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)
            )
          )
        },
        (_: Any) =>
          throw new Exception("Inserting into ? projection not supported.")
      )

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[UUID] = column[UUID]("id", O.PrimaryKey)

    /** Database column email_address SqlType(varchar) */
    val emailAddress: Rep[String] = column[String]("email_address")

    /** Database column encrypted_password SqlType(varchar) */
    val encryptedPassword: Rep[String] = column[String]("encrypted_password")

    /** Database column password_salt SqlType(varchar) */
    val passwordSalt: Rep[String] = column[String]("password_salt")

    /** Database column count_item SqlType(int4), Default(0) */
    val countItem: Rep[Int] = column[Int]("count_item", O.Default(0))

    /** Database column is_active SqlType(bool), Default(true) */
    val isActive: Rep[Boolean] = column[Boolean]("is_active", O.Default(true))

    /** Database column created_at SqlType(timestamp) */
    val createdAt: Rep[Instant] = column[Instant]("created_at")

    /** Database column updated_at SqlType(timestamp) */
    val updatedAt: Rep[Instant] = column[Instant]("updated_at")

    /** Uniqueness Index over (emailAddress) (database name users_email_address_key) */
    val index1 = index("users_email_address_key", emailAddress, unique = true)
  }

  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
