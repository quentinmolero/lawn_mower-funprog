package progfun.application.usecases.mowerengine

import progfun.application.MowerInitializationData
import progfun.domain.Lawn

case class MowerEngineProps(lawn: Lawn,mowersData: List[MowerInitializationData]) {
}
