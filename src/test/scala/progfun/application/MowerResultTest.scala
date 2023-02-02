package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.{Direction, Lawn, Mower}
import progfun.dto.PositionDTO

class MowerResultTest extends AnyFunSuite {
  val lawn = new Lawn((5, 5))
  val mower1 = new Mower(1, 2, Direction.N, List())
  val mower2 = new Mower(3, 3, Direction.E, List())

  test("MowerResult.toJSON") {
    val mowerResult = progfun.application.MowerResult(lawn, List(mower1, mower2))
    val json = mowerResult.toJSON
    assert(json == """{"limite":{"x":5,"y":5},"tondeuses":[{"debut":{"point":{"x":1,"y":2,"direction":"N"},"instructions":[],"fin":{"point":{"x":1,"y":2},"direction":"N"}},{"debut":{"point":{"x":3,"y":3,"direction":"E"},"instructions":[],"fin":{"point":{"x":3,"y":3},"direction":"E"}}]}""")
  }

  test("MowerResult.toDTO") {
    val mowerResult = progfun.application.MowerResult(lawn, List(mower1, mower2))
    val dto = mowerResult.toDTO
    assert(dto.limit.x == 5)
    assert(dto.limit.y == 5)
    assert(dto.tondeuses.length == 2)
    assert(dto.tondeuses(0).debut == PositionDTO(1, 2, Direction.N))
    assert(dto.tondeuses(1).debut == PositionDTO(3, 3, Direction.E))
  }

  test("MowerResult.toCSV") {
    val mowerResult = progfun.application.MowerResult(lawn, List(mower1, mower2))
    val csv = mowerResult.toCSV
    assert(csv == "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n0;1;2;N;1;2;N;\n1;3;3;E;3;3;E;")
  }
}
