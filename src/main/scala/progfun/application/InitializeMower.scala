package progfun.application

trait InitializeMower {
  def validate(): Unit
  def getLawnSize(): (Int, Int)
  def getMowersData(): List[MowerInitializationData]
}
