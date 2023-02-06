package progfun.dto

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.{Direction, Instruction}
import progfun.infrastructure.responses

class MowerResultDTOTest extends AnyFunSuite {
  val lawnDTO: LawnResponse = LawnResponse(5, 5)
  val mowerDTO: MowerResponse = responses.MowerResponse(1, 2, Direction.N, List(Instruction.A, Instruction.G, Instruction.A), 1, 3, Direction.N)
  val mowerResultDTO: MowerResultDTO = MowerResultDTO(lawnDTO, List(mowerDTO))

  test("MowerResultDTO") {
    assert(mowerResultDTO.limit.x == 5)
    assert(mowerResultDTO.limit.y == 5)
    assert(mowerResultDTO.tondeuses == List(mowerDTO))
  }
}
