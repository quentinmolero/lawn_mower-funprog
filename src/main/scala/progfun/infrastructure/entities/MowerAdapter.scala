package progfun.infrastructure.entities

import progfun.domain.{Instruction, Mower}
import progfun.dto.MowerDTO

class MowerAdapter(mower: Mower) {
  def toJSON: String = {
    val initialData =
      mower.getHistory.headOption.getOrElse((mower.getX, mower.getY, mower.getDirection, Instruction.A))
    val instructionsJson = mower.getHistory
      .map {
        case (_, _, _, instruction) =>
          s""""${instruction.toString}""""
      }
      .mkString(",")

    s"""{"debut":{"point":{"x":${initialData._1.toString},"y":${initialData._2.toString},"direction":"${initialData._3.toString}"},"instructions":[$instructionsJson],"fin":{"point":{"x":${mower.getX.toString},"y":${mower.getY.toString}},"direction":"${mower.getDirection.toString}"}}"""
  }

  def toDTO: MowerDTO = {
    if (mower.getHistory.isEmpty) {
      MowerDTO(mower.getX, mower.getY, mower.getDirection, List(), mower.getX, mower.getY, mower.getDirection)
    } else {
      mower.getHistory.headOption
        .map(
          head =>
            MowerDTO(
              head._1,
              head._2,
              head._3,
              mower.getHistory.map(_._4),
              mower.getX,
              mower.getY,
              mower.getDirection
            )
        )
        .getOrElse(MowerDTO(mower.getX, mower.getY, mower.getDirection, List(), mower.getX, mower.getY, mower.getDirection))
    }
  }

  override def toString: String =
    s"${mower.getX.toString} ${mower.getY.toString} ${mower.getDirection.toString}"
}
