package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.application.usecases.MowerExecutor
import progfun.domain.Instruction.Instruction
import progfun.domain.{Direction, Instruction, Lawn, Mower}

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

  test("testExecuteNoneInstruction") {
    val finalMower = MowerExecutor.executeInstructions(lawn, "X", mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.N)
    assert(finalMower.getHistory.isEmpty)
  }

  test("testForward") {
    val finalMower = MowerExecutor.forward(lawn, mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 3)
    assert(finalMower.getDirection == Direction.N)
    assert(finalMower.getHistory.length == 1)
  }

  test("testForwardWithNoneDirection") {
    val finalMower = MowerExecutor.forward(lawn, new Mower(1, 2, Direction.NONE, List()))
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.NONE)
    assert(finalMower.getHistory.length == 1)
  }

  test("testForwardWithInvalidPosition") {
    val finalMower = MowerExecutor.forward(lawn, new Mower(5, 5, Direction.N, List()))
    assert(finalMower.getX == 5)
    assert(finalMower.getY == 5)
    assert(finalMower.getDirection == Direction.N)
    assert(finalMower.getHistory.isEmpty)
  }

  test("testTurnRight") {
    val finalMower = MowerExecutor.turnRight(mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.E)
    assert(finalMower.getHistory.length == 1)
  }

  test("testTurnRightWithNoneDirection") {
    val finalMower = MowerExecutor.turnRight(new Mower(1, 2, Direction.NONE, List()))
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.NONE)
    assert(finalMower.getHistory.length == 1)
  }

  test("testTurnLeft") {
    val finalMower = MowerExecutor.turnLeft(mower)
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.W)
    assert(finalMower.getHistory.length == 1)
  }

  test("testTurnLeftWithNoneDirection") {
    val finalMower = MowerExecutor.turnLeft(new Mower(1, 2, Direction.NONE, List()))
    assert(finalMower.getX == 1)
    assert(finalMower.getY == 2)
    assert(finalMower.getDirection == Direction.NONE)
    assert(finalMower.getHistory.length == 1)
  }

  test("testExecuteInstruction") {
    val method = MowerExecutor.getClass.getDeclaredMethod("executeInstruction", classOf[Lawn], classOf[Instruction], classOf[Mower])
    method.setAccessible(true)
    val finalMower = method.invoke(MowerExecutor, lawn, Instruction.A, mower)
    finalMower match {
      case mower: Mower => {
        assert(mower.getX == 1)
        assert(mower.getY == 3)
        assert(mower.getDirection == Direction.N)
        assert(mower.getHistory == List((1, 2, Direction.N, Instruction.A)))
      }
      case _ => fail("Invalid return type")
    }
  }

  test("testExecuteInstructionWithInvalidInstruction") {
    val method = MowerExecutor.getClass.getDeclaredMethod("executeInstruction", classOf[Lawn], classOf[Instruction], classOf[Mower])
    method.setAccessible(true)
    val finalMower = method.invoke(MowerExecutor, lawn, Instruction.NONE, mower)
    finalMower match {
      case mower: Mower => {
        assert(mower.getX == 1)
        assert(mower.getY == 2)
        assert(mower.getDirection == Direction.N)
        assert(mower.getHistory.isEmpty)
      }
      case _ => fail("Invalid return type")
    }
  }
}
