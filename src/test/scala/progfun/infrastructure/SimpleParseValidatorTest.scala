package progfun.infrastructure

import org.scalatest.funsuite.AnyFunSuite
import progfun.application.DonneesIncorectesException

import scala.util.{Failure, Success}

class SimpleParseValidatorTest extends AnyFunSuite {
  val parseValidator = new SimpleParseValidator("./src/test/resources/testLawn.txt")

  test("testValidateFile") {
      parseValidator.validate()
  }

  test("testValidateLawnDefinition") {
    val method = parseValidator.getClass.getDeclaredMethod("validateLawnDefinition", classOf[String])
    method.setAccessible(true)
    val result = method.invoke(parseValidator, "5 5")
    assert(result == Success(()))
  }

  test("testValidateLawnDefinitionWithInvalidLawnDefinition") {
    val method = parseValidator.getClass.getDeclaredMethod("validateLawnDefinition", classOf[String])
    method.setAccessible(true)
    val result = method.invoke(parseValidator, "A B")
    assert(result == Failure(DonneesIncorectesException("Expected first line to contain two integers separated by a space")))
  }

  test("testValidateMowersData") {
    val method = parseValidator.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parseValidator, List("1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"))
    assert(result == Success(()))
  }

  test("testValidateMowersDataWithInvalidMowersInitialData") {
    val method = parseValidator.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parseValidator, List("1 2 X", "GAGAGAGAA"))
    assert(result == Failure(DonneesIncorectesException("Line 1 is not valid")))
  }

  test("testValidateMowersDataWithInvalidMowersInstructionsData") {
    val method = parseValidator.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parseValidator, List("1 2 N", "ABCDEFGHI"))
    assert(result == Failure(DonneesIncorectesException("Line 2 is not valid")))
  }
}
