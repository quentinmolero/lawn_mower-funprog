package progfun.dto

import progfun.domain.Direction.Direction
import progfun.domain.Instruction.Instruction
import progfun.dto

case class MowerDTO(
                     private val startingX: Int,
                     private val startingY: Int,
                     private val startingDirection: Direction,
                     private val instructionList: List[Instruction],
                     private val endingX: Int,
                     private val endingY: Int,
                     private val endingDirection: Direction) {
  val debut: PositionDTO = dto.PositionDTO(startingX, startingY, startingDirection)
  val instructions: List[String] = instructionList.map(_.toString)
  val fin: PositionDTO = dto.PositionDTO(endingX, endingY, endingDirection)
}
