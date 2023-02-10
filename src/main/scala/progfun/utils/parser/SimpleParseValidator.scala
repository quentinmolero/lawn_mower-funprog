package progfun.utils.parser

import progfun.application.DonneesIncorectesException

import scala.io.Source
import scala.util.{Failure, Success, Try}

class SimpleParseValidator(filePath: String) extends ParseValidator {

  override def validate(): Try[Unit] = {
    val file = Source.fromFile(filePath)
    val result = validateLawnDefinition(file.getLines().next()) match {
      case Success(_) =>
        validateMowersData(file.getLines().toList) match {
          case Success(_) => Success(())
          case Failure(exception) => Failure(exception)
        }
      case Failure(exception) => Failure(exception)
    }
    file.close()
    if(result.isFailure) {
    }
    result
  }

  private def validateLawnDefinition(line: String): Try[Unit] = {
    val parts = line.split(" ")
    if (parts.length != 2 || !parts.forall(
      part => part.forall(char => char.isDigit)
    )) {
      Failure(
        DonneesIncorectesException(
          "Expected first line to contain two integers separated by a space"
        )
      )
    } else {
      Success(())
    }
  }

  private def validateMowersData(lines: List[String]): Try[Unit] = {
    val results = lines.zipWithIndex.map {
      case (line, index) =>
        val lineIsValid = index % 2 match {
          case 0 =>
            val parts = line.split(" ")
            parts.length == 3 && parts(0).forall(_.isDigit) && parts(1).forall(
              _.isDigit
            ) && List("N", "E", "S", "W").contains(parts(2))
          case 1 =>
            line.forall(c => List('A', 'D', 'G').contains(c))
          case _ => false
        }
        if (lineIsValid) Success(())
        else {
          Failure(
             DonneesIncorectesException(
              s"Line ${(index + 1).toString} is not valid"
            )
          )
        }
    }
    if (results.forall(_.isSuccess)) Success(())
    else results.find(_.isFailure).getOrElse(Success(()))
  }
}
