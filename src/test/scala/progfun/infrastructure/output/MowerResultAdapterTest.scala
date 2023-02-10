package progfun.infrastructure.output

import org.scalatest.funsuite.AnyFunSuite
import progfun.application.MowerInitializationData
import progfun.application.usecases.mowerengine.{MowerEngine, MowerEngineProps}
import progfun.domain.{Direction, Lawn}
import progfun.infrastructure.adapters.MowerResultAdapter

class MowerResultAdapterTest extends AnyFunSuite {
  val lawn = new Lawn((5, 5));
  val mowerData: List[MowerInitializationData] = List.apply(
    MowerInitializationData(1, 2, Direction.N, "GAGAGAGAA"),
    MowerInitializationData(3, 3, Direction.E, "AADAADADDA")
  )

  val mowerEngineProps: MowerEngineProps = MowerEngineProps(lawn, mowerData)
  val mowerEngine = new MowerEngine(mowerEngineProps)
  val mowerResult = mowerEngine.calculateMowerResult

  test("MowerResultAdapter with a valid mowerResult to a json") {
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)

    assert(
      mowerResultAdapter.toJSON == "{\"limite\":{\"x\":5,\"y\":5},\"tondeuses\":[{\"debut\":{\"point\":{\"x\":1,\"y\":2,\"direction\":\"N\"},\"instructions\":[\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"G\",\"A\",\"A\"],\"fin\":{\"point\":{\"x\":1,\"y\":3},\"direction\":\"N\"}},{\"debut\":{\"point\":{\"x\":3,\"y\":3,\"direction\":\"E\"},\"instructions\":[\"A\",\"A\",\"D\",\"A\",\"A\",\"D\",\"A\",\"D\",\"D\",\"A\"],\"fin\":{\"point\":{\"x\":5,\"y\":1},\"direction\":\"E\"}}]}"
    )
  }

  test("MowerResultAdapter with a valid mowerResult to a csv result") {

    val mowerResultAdapter = new MowerResultAdapter(mowerResult)

    assert(
      mowerResultAdapter.toCSV == "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n0;1;2;N;1;3;N;GAGAGAGAA\n1;3;3;E;5;1;E;AADAADADDA"
    )
  }

  test("MowerResultAdapter with an empty mowerResult to a json result") {
    val lawn = new Lawn((0, 0));
    val mowerData: List[MowerInitializationData] = List.apply()

    val mowerEngineProps: MowerEngineProps = MowerEngineProps(lawn, mowerData)
    val mowerEngine = new MowerEngine(mowerEngineProps)
    val mowerResult = mowerEngine.calculateMowerResult
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)

    assert(
      mowerResultAdapter.toJSON == "{\"limite\":{\"x\":0,\"y\":0},\"tondeuses\":[]}"
    )
  }

  test("MowerResultAdapter with an empty mowerResult to a csv result") {
    val lawn = new Lawn((0, 0));
    val mowerData: List[MowerInitializationData] = List.apply()

    val mowerEngineProps: MowerEngineProps = MowerEngineProps(lawn, mowerData)
    val mowerEngine = new MowerEngine(mowerEngineProps)
    val mowerResult = mowerEngine.calculateMowerResult
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)

    assert(
      mowerResultAdapter.toCSV == "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n"
    )
  }

}
