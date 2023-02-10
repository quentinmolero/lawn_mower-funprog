package progfun.domain

import org.scalatest.funsuite.AnyFunSuite

class MowerTest extends AnyFunSuite {
  val mower: Mower = new Mower(
    1,
    2,
    Direction.N,
    List(
      (1, 2, Direction.N, Instruction.A),
      (1, 3, Direction.N, Instruction.G),
      (1, 3, Direction.W, Instruction.A)
    )
  )

  test("testGetters") {
    assert(mower.getX == 1)
    assert(mower.getY == 2)
    assert(mower.getDirection == Direction.N)
    assert(
      mower.getHistory == List(
        (1, 2, Direction.N, Instruction.A),
        (1, 3, Direction.N, Instruction.G),
        (1, 3, Direction.W, Instruction.A)
      )
    )
  }
}
