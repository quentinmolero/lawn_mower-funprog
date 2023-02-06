package progfun.infrastructure.responses

import progfun.dto
import progfun.dto.MowerResultDTO

class MowerResultAdapter {
  def toJSON: String = {
    val mowersJson = mowerAdapters.map(_.toJSON).mkString(",")
    s"""{"limite":${lawnAdapter.toJSON},"tondeuses":[$mowersJson]}"""
  }

  def toDTO: MowerResultDTO = {
    dto.MowerResultDTO(lawnAdapter.toDTO, mowerAdapters.map(_.toDTO))
  }

  def toCSV: String = {
    val header =
      "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions"
    val mowersCSV = mowerAdapters.zipWithIndex.map(mowerWithIndex => {
      val mower = mowerWithIndex._1
      val index = mowerWithIndex._2
      val mowerDTO = mower.toDTO
      s"${index.toString};${mowerDTO.debut.point.x.toString};${mowerDTO.debut.point.y.toString};${mowerDTO.debut.direction};${mowerDTO.fin.point.x.toString};${mowerDTO.fin.point.y.toString};${mowerDTO.fin.direction};${mowerDTO.instructions.mkString}"
    })
    s"$header\n${mowersCSV.mkString("\n")}"
  }
}
