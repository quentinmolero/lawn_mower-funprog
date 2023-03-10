package progfun.domain

object Direction extends Enumeration {
  type Direction = Value
  val N = Value("N")
  val E = Value("E")
  val S = Value("S")
  val W = Value("W")
  val NONE = Value("")

  def getDirection(orientation: Char): Direction = {
    orientation match {
      case 'N' => Direction.N
      case 'E' => Direction.E
      case 'S' => Direction.S
      case 'W' => Direction.W
      case _   => Direction.NONE
    }
  }
}
