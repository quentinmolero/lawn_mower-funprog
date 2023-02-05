package progfun.application

import progfun.domain.Direction.Direction

case class MowerInitializationData(
  x: Int,
  y: Int,
  direction: Direction,
  instructions: String
)
