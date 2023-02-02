package progfun.domain

import Direction.Direction
import Instruction.Instruction
import progfun.dto
import progfun.dto.MowerDTO

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

  def toJSON: String = {
    val initialData =
      history.headOption.getOrElse((x, y, direction, Instruction.A))
    val instructionsJson = history
      .map {
        case (_, _, _, instruction) =>
          s""""${instruction.toString}""""
      }
      .mkString(",")

    s"""{"debut":{"point":{"x":${initialData._1.toString},"y":${initialData._2.toString},"direction":"${initialData._3.toString}"},"instructions":[$instructionsJson],"fin":{"point":{"x":${x.toString},"y":${y.toString}},"direction":"${direction.toString}"}}"""
  }

  def toDTO: MowerDTO = {
    if (history.isEmpty) {
      dto.MowerDTO(x, y, direction, List(), x, y, direction)
    } else {
      history.headOption
        .map(
          head =>
            dto.MowerDTO(
              head._1,
              head._2,
              head._3,
              history.map(_._4),
              x,
              y,
              direction
            )
        )
        .getOrElse(dto.MowerDTO(x, y, direction, List(), x, y, direction))
    }
  }

  override def toString: String =
    s"${x.toString} ${y.toString} ${direction.toString}"
}
