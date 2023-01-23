package application

import domain.{Lawn, Mower, MowerInitializationData}

class MowerEngine(parser: Parser) {

  def calculateMowerResult: MowerResult = {
    val lawn = new Lawn(parser.getLawnSize())
    val mowersData = parser.getMowersData()
    val mowersResult = mowersData.map(mowerData => {
      val mower = buildMower(mowerData)
      val finalMower = MowerExecutor.executeInstructions(lawn, mowerData.instructions, mower)
      finalMower
    })
    MowerResult(lawn, mowersResult)
  }

  private def buildMower(mowerData: MowerInitializationData): Mower = {
    new Mower(mowerData.x, mowerData.y, mowerData.direction, List())
  }
}
