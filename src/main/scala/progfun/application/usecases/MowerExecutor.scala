package progfun.application.usecases

import progfun.domain.Instruction.Instruction
import progfun.domain.{Instruction, Lawn, Mower}

object MowerExecutor {

  def executeInstructions(
      lawn: Lawn,
      instructions: String,
      mower: Mower
  ): Mower = {
    if (instructions.isEmpty) {
      mower
    } else {
      executeInstructions(
        lawn,
        instructions.tail,
        executeInstruction(
          lawn,
          Instruction.getInstruction(instructions.head),
          mower
        )
      )
    }
  }

  private def executeInstruction(
      lawn: Lawn,
      instruction: Instruction,
      mower: Mower
  ): Mower = {
    instruction match {
      case Instruction.A => ForwardUseCase().execute(lawn, mower)
      case Instruction.D => TurnRightUseCase().execute(mower)
      case Instruction.G => TurnLeftUseCase().execute(mower)
      case _             => mower
    }
  }
}
