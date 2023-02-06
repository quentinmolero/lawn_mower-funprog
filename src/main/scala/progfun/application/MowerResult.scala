package progfun.application

import progfun.domain.{Lawn, Mower}
import progfun.dto
import progfun.dto.MowerResultDTO
import progfun.infrastructure.responses.{LawnAdapter, MowerAdapter}

case class MowerResult(lawn: Lawn, mowers: List[Mower]) {
}
