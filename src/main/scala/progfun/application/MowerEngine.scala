package progfun.application

import progfun.application
import progfun.application.usecases.MowerExecutor
import progfun.domain.{Lawn, Mower}

class MowerEngine(parser: InitializeMower) {

  def calculateMowerResult: MowerResult = {
    val lawn = new Lawn(parser.getLawnSize())
    val mowersData = parser.getMowersData()
    val mowersResult = mowersData.map(mowerData => {
      val mower = buildMower(mowerData)
      val finalMower =
        MowerExecutor.executeInstructions(lawn, mowerData.instructions, mower)
      finalMower
    })
    application.MowerResult(lawn, mowersResult)
  }

  private def buildMower(mowerData: MowerInitializationData): Mower = {
    new Mower(mowerData.x, mowerData.y, mowerData.direction, List())
  }
}
