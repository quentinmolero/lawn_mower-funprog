package progfun.domain

object Instruction extends Enumeration {
  type Instruction = Value
  val A = Value("A")
  val D = Value("D")
  val G = Value("G")

  def getInstruction(instruction: Char): Instruction = {
    instruction match {
      case 'A' => Instruction.A
      case 'D' => Instruction.D
      case 'G' => Instruction.G
      case _ => Instruction.A
    }
  }
}
