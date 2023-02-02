package progfun.domain

import org.scalatest.funsuite.AnyFunSuite

class InstructionTest extends AnyFunSuite {
  test("Instruction.getInstruction") {
    assert(Instruction.getInstruction('A') == Instruction.A)
    assert(Instruction.getInstruction('D') == Instruction.D)
    assert(Instruction.getInstruction('G') == Instruction.G)
    assert(Instruction.getInstruction('X') == Instruction.A)
  }

  test("Instruction.toString") {
    assert(Instruction.A.toString == "A")
    assert(Instruction.D.toString == "D")
    assert(Instruction.G.toString == "G")
  }
}
