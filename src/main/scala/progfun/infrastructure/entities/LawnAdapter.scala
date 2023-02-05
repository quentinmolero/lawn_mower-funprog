package progfun.infrastructure.entities

import progfun.domain.Lawn
import progfun.dto.LawnDTO

class LawnAdapter(lawn: Lawn) {
  def toJSON: String = {
    s"""{"x":${lawn.getTopRightCorner._1.toString},"y":${lawn.getTopRightCorner._2.toString}}"""
  }

  def toDTO: LawnDTO = {
    LawnDTO(lawn.getTopRightCorner._1, lawn.getTopRightCorner._2)
  }
}
