package progfun.application

import org.scalatest.funsuite.AnyFunSuite
import progfun.domain.Direction
import progfun.infrastructure.{FileParser, SimpleParseValidator}

class ParserTest extends AnyFunSuite {
  val parseValidator = new SimpleParseValidator("./src/test/resources/testLawn.txt")
  val parser = new FileParser(parseValidator, "src/test/resources/testLawn.txt")

  test("testValidateFile") {
    parser.validate()
  }

  test("testValidateFileWithInvalidLawnDefinition") {
    val parseValidator = new SimpleParseValidator("src/test/resources/invalidInitialMowerTestLawn.txt")
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInitialMowerTestLawn.txt")
    val thrown = intercept[DonneesIncorectesException] {
      parser.validate()
    }
    assert(thrown.getMessage == "Données incorrectes: Line 1 is not valid")
  }

  test("testValidateFileWithInvalidInitialMowerDefinition") {
    val parseValidator = new SimpleParseValidator("src/test/resources/invalidInitialMowerTestLawn.txt")
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInitialMowerTestLawn.txt")
    val thrown = intercept[DonneesIncorectesException] {
      parser.validate()
    }
    assert(thrown.getMessage == "Données incorrectes: Line 1 is not valid")
  }

  test("testValidateFileWithInvalidInstructionsMowerDefinition") {
    val parseValidator = new SimpleParseValidator("src/test/resources/invalidInstructionsMowerTestLawn.txt")
    val parser = new FileParser(parseValidator, "src/test/resources/invalidInstructionsMowerTestLawn.txt")
    val thrown = intercept[DonneesIncorectesException] {
      parser.validate()
    }
    assert(thrown.getMessage == "Données incorrectes: Line 2 is not valid")
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
