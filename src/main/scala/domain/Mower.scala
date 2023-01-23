package domain

import domain.Direction.Direction
import domain.Instruction.Instruction
import dto.MowerDTO

class Mower(x: Int, y: Int, direction: Direction, history: List[(Int, Int, Direction, Instruction)]) {
  def getX: Int = x

  def getY: Int = y

  def getDirection: Direction = direction

  def getHistory: List[(Int, Int, Direction, Instruction)] = history

  def toJSON: String = {
    val historyJson = history.map { case (x, y, direction, instruction) =>
      s"""{"x":${x.toString},"y":${y.toString},"direction":"${direction.toString}","instruction":"${instruction.toString}"}"""
    }.mkString(",")

    s"""{"x":${x.toString},"y":${y.toString},"direction":"${direction.toString}","history":[$historyJson]}"""
  }

  def toDTO: MowerDTO = {
    if (history.isEmpty) {
      MowerDTO(x, y, direction, List(), x, y, direction)
    } else {
      history.headOption.map(head => MowerDTO(head._1, head._2, head._3, history.map(_._4), x, y, direction)).getOrElse(MowerDTO(x, y, direction, List(), x, y, direction))
    }
  }

  override def toString: String = s"${x.toString} ${y.toString} ${direction.toString}"
}
