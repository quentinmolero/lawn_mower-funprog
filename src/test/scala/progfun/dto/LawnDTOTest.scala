package progfun.dto

import org.scalatest.funsuite.AnyFunSuite
import progfun.infrastructure.responses.LawnResponse

class LawnDTOTest extends AnyFunSuite {
  val lawnDTO: LawnResponse = LawnResponse(5, 5)

  test("testLawnDTO") {
    assert(lawnDTO.x == 5)
    assert(lawnDTO.y == 5)
  }
}
