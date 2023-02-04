package progfun.domain

import org.scalatest.funsuite.AnyFunSuite

class DirectionTest extends AnyFunSuite {
  test("Direction.getDirection") {
    assert(Direction.getDirection('N') == Direction.N)
    assert(Direction.getDirection('E') == Direction.E)
    assert(Direction.getDirection('S') == Direction.S)
    assert(Direction.getDirection('W') == Direction.W)
    assert(Direction.getDirection('X') == Direction.NONE)
  }

  test("Direction.toString") {
    assert(Direction.N.toString == "N")
    assert(Direction.E.toString == "E")
    assert(Direction.S.toString == "S")
    assert(Direction.W.toString == "W")
  }
}
