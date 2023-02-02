package progfun.domain

import org.scalatest.funsuite.AnyFunSuite

class MowerInitializationDataTest extends AnyFunSuite {
  test("MowerInitializationData") {
    val mowerInitializationData =
      MowerInitializationData(1, 2, Direction.N, "GAGAGAGAA")
    assert(mowerInitializationData.x == 1)
    assert(mowerInitializationData.y == 2)
    assert(mowerInitializationData.direction == Direction.N)
    assert(mowerInitializationData.instructions == "GAGAGAGAA")
  }
}
