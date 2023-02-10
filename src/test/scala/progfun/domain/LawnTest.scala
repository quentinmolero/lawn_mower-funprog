package progfun.domain

import org.scalatest.BeforeAndAfter
import org.scalatest.funsuite.AnyFunSuite

class LawnTest extends AnyFunSuite with BeforeAndAfter {
  val lawn: Lawn = new Lawn((5, 5))

  test("testIsPositionValid") {
    assert(lawn.isPositionValid((0, 0)))
    assert(lawn.isPositionValid((1, 2)))
    assert(lawn.isPositionValid((5, 5)))
    assert(!lawn.isPositionValid((6, 5)))
    assert(!lawn.isPositionValid((5, 6)))
    assert(!lawn.isPositionValid((-1, 5)))
    assert(!lawn.isPositionValid((5, -1)))
  }
}
