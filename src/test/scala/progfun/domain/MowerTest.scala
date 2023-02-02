package progfun.domain

import org.scalatest.funsuite.AnyFunSuite
import progfun.dto.MowerDTO

class MowerTest extends AnyFunSuite {
  val mower: Mower = new Mower(1, 2, Direction.N, List((1, 2, Direction.N, Instruction.A), (1, 3, Direction.N, Instruction.G), (1, 3, Direction.W, Instruction.A)))

  test("testGetters") {
    assert(mower.getX == 1)
    assert(mower.getY == 2)
    assert(mower.getDirection == Direction.N)
    assert(mower.getHistory == List((1, 2, Direction.N, Instruction.A), (1, 3, Direction.N, Instruction.G), (1, 3, Direction.W, Instruction.A)))
  }

  test("testToJSON") {
    assert(mower.toJSON == """{"debut":{"point":{"x":1,"y":2,"direction":"N"},"instructions":["A","G","A"],"fin":{"point":{"x":1,"y":2},"direction":"N"}}""")
  }

  test("testToDTO") {
    assert(mower.toDTO == MowerDTO(1, 2, Direction.N, List(Instruction.A, Instruction.G, Instruction.A), 1, 2, Direction.N))
  }

  test("testToString") {
    assert(mower.toString == "1 2 N")
  }
}
