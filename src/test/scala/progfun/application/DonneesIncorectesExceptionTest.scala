package progfun.application

import org.scalatest.funsuite.AnyFunSuite

class DonneesIncorectesExceptionTest extends AnyFunSuite {
  test("testDonneesIncorectesException") {
    val exception = new DonneesIncorectesException("message")
    assert(exception.getMessage == "Données incorrectes: message")
  }
}
