package progfun.domain

import Direction.Direction
import Instruction.Instruction

class Mower(
    x: Int,
    y: Int,
    direction: Direction,
    history: List[(Int, Int, Direction, Instruction)]
) {
  def getX: Int = x

  def getY: Int = y

  def getDirection: Direction = direction

  def getHistory: List[(Int, Int, Direction, Instruction)] = history
}
