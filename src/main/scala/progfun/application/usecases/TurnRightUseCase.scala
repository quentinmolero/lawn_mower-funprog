package progfun.application.usecases

import progfun.domain.Direction.Direction
import progfun.domain.{Direction, Instruction, Mower}

case class TurnRightUseCase() {
  def execute(mower: Mower): Mower = {
    val newDirection: Direction = mower.getDirection match {
      case Direction.N => Direction.E
      case Direction.E => Direction.S
      case Direction.S => Direction.W
      case Direction.W => Direction.N
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
          Instruction.D
        )
        )
    )
  }
}
