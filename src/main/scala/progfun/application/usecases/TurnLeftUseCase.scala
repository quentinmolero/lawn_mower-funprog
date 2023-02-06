package progfun.application.usecases

import progfun.domain.Direction.Direction
import progfun.domain.{Direction, Instruction, Mower}

case class TurnLeftUseCase() {
  def execute(mower: Mower): Mower = {
    val newDirection: Direction = mower.getDirection match {
      case Direction.N => Direction.W
      case Direction.E => Direction.N
      case Direction.S => Direction.E
      case Direction.W => Direction.S
      case _           => mower.getDirection
    }
    new Mower(
      mower.getX,
      mower.getY,
      newDirection,
      mower.getHistory :+ (
        (
          mower.getX,
          mower.getY,
          mower.getDirection,
          Instruction.G
        )
        )
    )
  }
}
