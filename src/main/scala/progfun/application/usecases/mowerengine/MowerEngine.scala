package progfun.application.usecases.mowerengine

import progfun.application
import progfun.application.usecases.MowerExecutor
import progfun.application.{MowerResult}
import progfun.domain.{Mower}

class MowerEngine(mowerEngineProps: MowerEngineProps) {

  def calculateMowerResult: MowerResult = {
    val mowersResult = mowerEngineProps.mowersData.map(mowerData => {
      val mower =
        new Mower(mowerData.x, mowerData.y, mowerData.direction, List())
      val finalMower =
        MowerExecutor.executeInstructions(
          mowerEngineProps.lawn,
          mowerData.instructions,
          mower
        )
      finalMower
    })
    application.MowerResult(mowerEngineProps.lawn, mowersResult)
  }
}
