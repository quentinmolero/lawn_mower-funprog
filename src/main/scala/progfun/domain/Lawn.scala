package progfun.domain

class Lawn(topRightCorner: (Int, Int)) {
  val bottomLeftCorner: (Int, Int) = (0, 0)

  def getTopRightCorner: (Int, Int) = topRightCorner

  def isPositionValid(position: (Int, Int)): Boolean = {
    position._1 >= bottomLeftCorner._1 && position._1 <= topRightCorner._1 &&
    position._2 >= bottomLeftCorner._2 && position._2 <= topRightCorner._2
  }
}
