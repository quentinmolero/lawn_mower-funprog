package progfun.application

import progfun.domain.Direction.Direction
import progfun.domain.Instruction.Instruction
import progfun.domain.{Direction, Instruction, Lawn, Mower}

object MowerExecutor {

  def executeInstructions(lawn: Lawn, instructions: String, mower: Mower): Mower = {
    if (instructions.isEmpty) {
      mower
    } else {
      executeInstructions(lawn, instructions.tail, executeInstruction(lawn, Instruction.getInstruction(instructions.head), mower))
    }
  }

  def forward(lawn: Lawn, mower: Mower): Mower = {
    val newPosition: (Int, Int) = mower.getDirection match {
      case Direction.N => (mower.getX, mower.getY + 1)
      case Direction.E => (mower.getX + 1, mower.getY)
      case Direction.S => (mower.getX, mower.getY - 1)
      case Direction.W => (mower.getX - 1, mower.getY)
      case _ => (mower.getX, mower.getY)
    }

    if (lawn.isPositionValid(newPosition)) {
      new Mower(newPosition._1, newPosition._2, mower.getDirection, mower.getHistory :+ ((mower.getX, mower.getY, mower.getDirection, Instruction.A)))
    } else {
      mower
    }
  }

  def turnRight(mower: Mower): Mower = {
    val newDirection: Direction = mower.getDirection match {
      case Direction.N => Direction.E
      case Direction.E => Direction.S
      case Direction.S => Direction.W
      case Direction.W => Direction.N
      case _ => mower.getDirection
    }
    new Mower(mower.getX, mower.getY, newDirection, mower.getHistory :+ ((mower.getX, mower.getY, mower.getDirection, Instruction.D)))
  }

  def turnLeft(mower: Mower): Mower = {
    val newDirection: Direction = mower.getDirection match {
      case Direction.N => Direction.W
      case Direction.E => Direction.N
      case Direction.S => Direction.E
      case Direction.W => Direction.S
      case _ => mower.getDirection
    }
    new Mower(mower.getX, mower.getY, newDirection, mower.getHistory :+ ((mower.getX, mower.getY, mower.getDirection, Instruction.G)))
  }

  private def executeInstruction(lawn: Lawn, instruction: Instruction, mower: Mower): Mower = {
    instruction match {
      case Instruction.A => forward(lawn, mower)
      case Instruction.D => turnRight(mower)
      case Instruction.G => turnLeft(mower)
      case _ => mower
    }
  }
}
