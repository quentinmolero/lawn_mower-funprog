package progfun.dto

import org.scalatest.funsuite.AnyFunSuite

class PointDTOTest extends AnyFunSuite {
  val pointDTO: PointDTO = PointDTO(1, 2)

  test("PointDTO should be created") {
    assert(pointDTO.x == 1)
    assert(pointDTO.y == 2)
  }
}
