package domain

import dto.LawnDTO

class Lawn(topRightCorner: (Int, Int)) {
  val bottomLeftCorner: (Int, Int) = (0, 0)

  def isPositionValid(position: (Int, Int)): Boolean = {
    position._1 >= bottomLeftCorner._1 && position._1 <= topRightCorner._1 &&
      position._2 >= bottomLeftCorner._2 && position._2 <= topRightCorner._2
  }

  def toJSON: String = {
    s"""{"topRightCorner":{"x":${topRightCorner._1.toString},"y":${topRightCorner._2.toString}}}"""
  }

  def toDTO: LawnDTO = {
    LawnDTO(topRightCorner._1, topRightCorner._2)
  }

  override def toString: String = {
    val (x, y) = topRightCorner
    val (_, minY) = bottomLeftCorner
    (minY to y).reverse.map {
      y =>
        if (y == topRightCorner._2) "┌" + "─" * x + "┐\n" + "│" + " " * x + "│\n"
        else if (y == 0) "└" + "─" * x + "┘\n"
        else "│" + " " * x + "│\n"
    }.mkString
  }
}
