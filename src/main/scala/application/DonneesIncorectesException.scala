package application

class DonneesIncorectesException(message: String) extends Exception {
  override def getMessage: String = "Données incorrectes: " + message
}
