package progfun.dto

import org.scalatest.funsuite.AnyFunSuite

class LawnDTOTest extends AnyFunSuite {
  val lawnDTO: LawnDTO = LawnDTO(5, 5)

  test("testLawnDTO") {
    assert(lawnDTO.x == 5)
    assert(lawnDTO.y == 5)
  }
}
