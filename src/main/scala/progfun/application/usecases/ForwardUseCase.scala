package progfun.application.usecases

import progfun.domain.{Direction, Instruction, Lawn, Mower}

case class ForwardUseCase() {

  def execute(lawn: Lawn, mower: Mower): Mower = {
    val newPosition: (Int, Int) = mower.getDirection match {
      case Direction.N => (mower.getX, mower.getY + 1)
      case Direction.E => (mower.getX + 1, mower.getY)
      case Direction.S => (mower.getX, mower.getY - 1)
      case Direction.W => (mower.getX - 1, mower.getY)
      case _           => (mower.getX, mower.getY)
    }

    if (lawn.isPositionValid(newPosition)) {
      new Mower(
        newPosition._1,
        newPosition._2,
        mower.getDirection,
        mower.getHistory :+ (
          (
            mower.getX,
            mower.getY,
            mower.getDirection,
            Instruction.A
          )
          )
      )
    } else {
      mower
    }
  }
}
