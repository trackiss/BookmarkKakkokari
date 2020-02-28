package domain.models.user

sealed trait UserActiveStatus

case object InActive extends UserActiveStatus
case object Active extends UserActiveStatus
