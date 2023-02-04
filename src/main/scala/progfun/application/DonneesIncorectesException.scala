package progfun.application

case class DonneesIncorectesException(message: String) extends Exception {
  override def getMessage: String = "Données incorrectes: " + message
}
