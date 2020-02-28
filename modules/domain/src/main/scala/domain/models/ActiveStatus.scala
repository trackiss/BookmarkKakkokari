package domain.models

sealed trait ActiveStatus

case object InActive extends ActiveStatus
case object Active extends ActiveStatus
