package progfun.infrastructure.responses

import progfun.domain.Lawn

class LawnAdapter(lawn: Lawn) {
  def toJSON: String = {
    s"""{"x":${lawn.getTopRightCorner._1.toString},"y":${lawn.getTopRightCorner._2.toString}}"""
  }

  def toDTO: LawnResponse = {
    LawnResponse(lawn.getTopRightCorner._1, lawn.getTopRightCorner._2)
  }
}
