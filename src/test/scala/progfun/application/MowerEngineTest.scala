package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.application
import progfun.domain.{Direction, Instruction, Lawn, Mower}
import progfun.infrastructure.{FileParser, responses}
import progfun.infrastructure.responses.MowerResponse

class MowerEngineTest extends AnyFunSuite {
  val mowerEngine = new MowerEngine(new FileParser("./src/test/resources/testLawn.txt"))

  test("testCalculateMowerResult") {
    val mowerResult = mowerEngine.calculateMowerResult
    assert(mowerResult.lawn.toDTO == new Lawn((5, 5)).toDTO)
    assert(mowerResult.mowers.size == 2)
    assert(mowerResult.mowers(0).getX == 1)
    assert(mowerResult.mowers(0).getY == 3)
    assert(mowerResult.mowers(0).getDirection == Direction.N)
    assert(mowerResult.mowers(0).getHistory == List((1, 2, Direction.N, Instruction.G), (1, 2, Direction.W, Instruction.A), (0, 2, Direction.W, Instruction.G), (0, 2, Direction.S, Instruction.A), (0, 1, Direction.S, Instruction.G), (0, 1, Direction.E, Instruction.A), (1, 1, Direction.E, Instruction.G), (1, 1, Direction.N, Instruction.A), (1, 2, Direction.N, Instruction.A)))
    assert(mowerResult.mowers(0).toDTO == responses.MowerResponse(1, 2, Direction.N, List(Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.G, Instruction.A, Instruction.A), 1, 3, Direction.N))
    assert(mowerResult.mowers(1).getX == 5)
    assert(mowerResult.mowers(1).getY == 1)
    assert(mowerResult.mowers(1).getDirection == Direction.E)
    assert(mowerResult.mowers(1).getHistory == List((3, 3, Direction.E, Instruction.A), (4, 3, Direction.E, Instruction.A), (5, 3, Direction.E, Instruction.D), (5, 3, Direction.S, Instruction.A), (5, 2, Direction.S, Instruction.A), (5, 1, Direction.S, Instruction.D), (5, 1, Direction.W, Instruction.A), (4, 1, Direction.W, Instruction.D), (4, 1, Direction.N, Instruction.D), (4, 1, Direction.E, Instruction.A)))
  }

  test("testBuildMower") {
    val method = classOf[MowerEngine].getDeclaredMethod("buildMower", classOf[MowerInitializationData])
    method.setAccessible(true)
    val result = method.invoke(mowerEngine, application.MowerInitializationData(1, 2, Direction.N, ""))
    result match {
      case mower: Mower => {
        assert(mower.getX == 1)
        assert(mower.getY == 2)
        assert(mower.getDirection == Direction.N)
        assert(mower.getHistory == List())
      }
      case _ => fail("Expected a Mower")
    }
  }
}
