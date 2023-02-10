package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.application.usecases.mowerengine.MowerEngine
import progfun.domain.{Direction, Instruction}
import progfun.infrastructure.{FileParser, SimpleParseValidator}

class MowerEngineTest extends AnyFunSuite {
  val parseValidator = new SimpleParseValidator("./src/test/resources/testLawn.txt")
  val mowerEngine = new MowerEngine(new FileParser(parseValidator, "./src/test/resources/testLawn.txt"))

  test("testCalculateMowerResult") {
    val mowerResult = mowerEngine.calculateMowerResult
    assert(mowerResult.lawn.getTopRightCorner == ((5, 5): (Int, Int)))
    assert(mowerResult.mowers.size == 2)
    assert(mowerResult.mowers(0).getX == 1)
    assert(mowerResult.mowers(0).getY == 3)
    assert(mowerResult.mowers(0).getDirection == Direction.N)
    assert(mowerResult.mowers(0).getHistory == List((1, 2, Direction.N, Instruction.G), (1, 2, Direction.W, Instruction.A), (0, 2, Direction.W, Instruction.G), (0, 2, Direction.S, Instruction.A), (0, 1, Direction.S, Instruction.G), (0, 1, Direction.E, Instruction.A), (1, 1, Direction.E, Instruction.G), (1, 1, Direction.N, Instruction.A), (1, 2, Direction.N, Instruction.A)))
    assert(mowerResult.mowers(1).getX == 5)
    assert(mowerResult.mowers(1).getY == 1)
    assert(mowerResult.mowers(1).getDirection == Direction.E)
    assert(mowerResult.mowers(1).getHistory == List((3, 3, Direction.E, Instruction.A), (4, 3, Direction.E, Instruction.A), (5, 3, Direction.E, Instruction.D), (5, 3, Direction.S, Instruction.A), (5, 2, Direction.S, Instruction.A), (5, 1, Direction.S, Instruction.D), (5, 1, Direction.W, Instruction.A), (4, 1, Direction.W, Instruction.D), (4, 1, Direction.N, Instruction.D), (4, 1, Direction.E, Instruction.A)))
  }
}
