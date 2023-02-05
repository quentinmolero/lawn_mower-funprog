package progfun.application

trait InitializeMower {
  def execute(): Unit
  def getLawnSize(): (Int, Int)
  def getMowersData(): List[MowerInitializationData]
}
