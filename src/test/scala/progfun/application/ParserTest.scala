package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.Direction
import progfun.infrastructure.{FileParser, SimpleParseValidator}

import scala.util.{Failure, Success}

class ParserTest extends AnyFunSuite {
  val parseValidator = new SimpleParseValidator("./src/test/resources/testLawn.txt")
  val parser = new FileParser(parseValidator, "src/test/resources/testLawn.txt")

  test("testValidateFile") {
    parser.validate()
  }

  test("testValidateFileWithInvalidLawnDefinition") {
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInitialMowerTestLawn.txt")
    assertThrows[DonneesIncorectesException] {
      parser.validate()
    }
  }

  test("testValidateFileWithInvalidInitialMowerDefinition") {
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInitialMowerTestLawn.txt")
    assertThrows[DonneesIncorectesException] {
      parser.validate()
    }
  }

  test("testValidateFileWithInvalidInstructionsMowerDefinition") {
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInstructionsMowerTestLawn.txt")
    assertThrows[DonneesIncorectesException] {
      parser.validate()
    }
  }

  test("testValidateLawnDefinition") {
    val method = parser.getClass.getDeclaredMethod("validateLawnDefinition", classOf[String])
    method.setAccessible(true)
    val result = method.invoke(parser, "5 5")
    assert(result == Success(()))
  }

  test("testValidateLawnDefinitionWithInvalidLawnDefinition") {
    val method = parser.getClass.getDeclaredMethod("validateLawnDefinition", classOf[String])
    method.setAccessible(true)
    val result = method.invoke(parser, "A B")
    assert(result == Failure(DonneesIncorectesException("Expected first line to contain two integers separated by a space")))
  }

  test("testValidateMowersData") {
    val method = parser.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parser, List("1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"))
    assert(result == Success(()))
  }

  test("testValidateMowersDataWithInvalidMowersInitialData") {
    val method = parser.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parser, List("1 2 X", "GAGAGAGAA"))
    assert(result == Failure(DonneesIncorectesException("Line 0 is not valid")))
  }

  test("testValidateMowersDataWithInvalidMowersInstructionsData") {
    val method = parser.getClass.getDeclaredMethod("validateMowersData", classOf[List[String]])
    method.setAccessible(true)
    val result = method.invoke(parser, List("1 2 N", "ABCDEFGHI"))
    assert(result == Failure(DonneesIncorectesException("Line 1 is not valid")))
  }

  test("testGetLawnSize") {
    val lawnSize = parser.getLawnSize()
    assert(lawnSize._1 == 5)
    assert(lawnSize._2 == 5)
  }

  test("testGetMowersData") {
    val mowersData = parser.getMowersData()
    assert(mowersData.size == 2)
    assert(mowersData(0).x == 1)
    assert(mowersData(0).y == 2)
    assert(mowersData(0).direction == Direction.N)
    assert(mowersData(0).instructions == "GAGAGAGAA")
    assert(mowersData(1).x == 3)
    assert(mowersData(1).y == 3)
    assert(mowersData(1).direction == Direction.E)
    assert(mowersData(1).instructions == "AADAADADDA")
  }
}
