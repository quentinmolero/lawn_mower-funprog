package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.{Direction, Lawn, Mower}

class MowerExecutorTest extends AnyFunSuite {
  val lawn = new Lawn((5, 5))
  val mower = new Mower(1, 2, Direction.N, List())
  val instructions = "GAGAGAGAA"

  test("testExecuteInstructions") {
    val finalMower = MowerExecutor.executeInstructions(lawn, instructions, mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 3)
    assert(finalMower.getDirection == Direction.N)
    assert(finalMower.getHistory.length == 9)
  }

  test("testForward") {
    val finalMower = MowerExecutor.forward(lawn, mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 3)
    assert(finalMower.getDirection == Direction.N)
    assert(finalMower.getHistory.length == 1)
  }

  test("testTurnRight") {
    val finalMower = MowerExecutor.turnRight(mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.E)
    assert(finalMower.getHistory.length == 1)
  }

  test("testTurnLeft") {
    val finalMower = MowerExecutor.turnLeft(mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.W)
    assert(finalMower.getHistory.length == 1)
  }
}
