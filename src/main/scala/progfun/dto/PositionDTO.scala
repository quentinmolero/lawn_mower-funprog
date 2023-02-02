package progfun.dto

import progfun.domain.Direction.Direction

case class PositionDTO(
    private val x: Int,
    private val y: Int,
    private val positionDirection: Direction
) {
  val point: PointDTO = PointDTO(x, y)
  val direction: String = positionDirection.toString
}
