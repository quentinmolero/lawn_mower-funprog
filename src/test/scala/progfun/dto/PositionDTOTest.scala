package progfun.dto

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.Direction

class PositionDTOTest extends AnyFunSuite {
  val positionDTO: PositionDTO = PositionDTO(1, 2, Direction.N)

  test("PositionDTO should be created") {
    assert(positionDTO.point.x == 1)
    assert(positionDTO.point.y == 2)
    assert(positionDTO.direction == "N")
  }
}
