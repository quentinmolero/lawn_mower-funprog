package progfun.infrastructure

import progfun.application.{DonneesIncorectesException, MowerInitializationData}
import progfun.domain
import progfun.domain.Direction

import scala.io.Source
import scala.util.{Failure, Success, Try}

class Parser(filePath: String) {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def validateFile(): Parser = {
    val file = Source.fromFile(filePath)
    validateLawnDefinition(file.getLines().next()) match {
      case Success(_) =>
        validateMowersData(file.getLines().toList) match {
          case Success(_) =>
          case Failure(_) => throw new DonneesIncorectesException("Mower data is invalid")
        }
      case Failure(_) => throw new DonneesIncorectesException("Lawn definition is invalid")
    }
    file.close()
    this
  }

  private def validateLawnDefinition(line: String): Try[Unit] = {
    val parts = line.split(" ")
    if (parts.length != 2 || !parts.forall(
          part => part.forall(char => char.isDigit)
        )) {
      Failure(
        new DonneesIncorectesException(
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
        else
          Failure(
            new DonneesIncorectesException(
              s"Line ${index.toString} is not valid"
            )
          )
    }
    if (results.forall(_.isSuccess)) Success(())
    else results.find(_.isFailure).getOrElse(Success(()))
  }

  def getLawnSize(): (Int, Int) = {
    val file = Source.fromFile(filePath)
    val lawnSize = file.getLines().next().mkString.split(" ")
    file.close()
    val x = lawnSize(0).toInt
    val y = lawnSize(1).toInt
    (x, y)
  }

  def getMowersData(): List[MowerInitializationData] = {
    val file = Source.fromFile(filePath)
    val lines = file.getLines().drop(1)
    val datas = lines.toList
      .grouped(2)
      .map {
        case List(initializationData, instructionsData) =>
          val initData = readMowerInitializationData(initializationData)
          val instructions = instructionsData
          MowerInitializationData(
            initData._1,
            initData._2,
            Direction.getDirection(initData._3),
            instructions
          )
      }
      .toList
    file.close()
    datas
  }

  private def readMowerInitializationData(line: String): (Int, Int, Char) = {
    val split = line.split(" ")
    val x = split(0).toInt
    val y = split(1).toInt
    val direction = split(2).charAt(0)
    (x, y, direction)
  }
}