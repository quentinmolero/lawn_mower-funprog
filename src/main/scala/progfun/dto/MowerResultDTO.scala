package progfun.dto

import progfun.infrastructure.responses.{LawnResponse, MowerResponse}

case class MowerResultDTO(limit: LawnResponse, tondeuses: List[MowerResponse])
