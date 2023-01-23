package dto

import domain.Direction.Direction
import domain.Instruction.Instruction

case class MowerDTO(
                     private val startingX: Int,
                     private val startingY: Int,
                     private val startingDirection: Direction,
                     private val instructionList: List[Instruction],
                     private val endingX: Int,
                     private val endingY: Int,
                     private val endingDirection: Direction) {
  val debut: PositionDTO = PositionDTO(startingX, startingY, startingDirection)
  val instructions: List[String] = instructionList.map(_.toString)
  val fin: PositionDTO = PositionDTO(endingX, endingY, endingDirection)
}
