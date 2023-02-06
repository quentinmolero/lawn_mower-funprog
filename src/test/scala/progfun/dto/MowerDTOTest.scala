package progfun.dto

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.{Direction, Instruction}

class MowerDTOTest extends AnyFunSuite {
  test("MowerDTO") {
    val mowerDTO = MowerResponse(
    1,
    2,
    Direction.N,
    List(Instruction.A, Instruction.G, Instruction.D, Instruction.A),
    1,
    3,
    Direction.N
    )
    assert(mowerDTO.debut.point.x == 1)
    assert(mowerDTO.debut.point.y == 2)
    assert(mowerDTO.debut.direction == "N")
    assert(mowerDTO.instructions == List("A", "G", "D", "A"))
    assert(mowerDTO.fin.point.x == 1)
    assert(mowerDTO.fin.point.y == 3)
    assert(mowerDTO.fin.direction == "N")
  }
}
