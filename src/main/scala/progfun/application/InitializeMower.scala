package progfun.application

trait InitializeMower {
  def execute()
  def getLawnSize(): (Int, Int)
  def getMowersData(): List[MowerInitializationData]
}
