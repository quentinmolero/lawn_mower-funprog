package progfun.infrastructure.adapters

import progfun.domain.Lawn
import progfun.infrastructure.responses.LawnResponse

class LawnAdapter(lawn: Lawn) {
  def toJSON: String = {
    s"""{"x":${lawn.getTopRightCorner._1.toString},"y":${lawn.getTopRightCorner._2.toString}}"""
  }

  def toDTO: LawnResponse = {
    LawnResponse(lawn.getTopRightCorner._1, lawn.getTopRightCorner._2)
  }
}
