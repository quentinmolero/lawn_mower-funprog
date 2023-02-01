package progfun.application

import progfun.domain
import progfun.domain.{Direction, MowerInitializationData}

import scala.io.Source
import scala.util.{Failure, Success, Try}

class Parser(filePath: String) {

  def validateFile(): Parser = {
    val file = Source.fromFile(filePath)
    validateLawnDefinition(file.getLines().next()) match {
      case Success(_) =>
        validateMowersData(file.getLines().toList) match {
          case Success(_) => //do something
          case Failure(_) =>
        }
      case Failure(_) => //handle exception
    }
    file.close()
    this
  }

  private def validateLawnDefinition(line: String): Try[Unit] = {
    val firstLineRegex = "^[0-9]+ [0-9]+$".r
    if (firstLineRegex.findFirstIn(line).isEmpty) {
      Failure(new DonneesIncorectesException(s"Excepted first line to respect the following format ${firstLineRegex.toString()}"))
    } else {
      Success(())
    }
  }

  private def validateMowersData(lines: List[String]): Try[Unit] = {
    val mowerInitializationRegex = "^[0-9]+ [0-9]+ [NESW]$".r
    val mowerInstructionsRegex = "^[ADG]+$".r
    val results = lines.zipWithIndex.map {
      case (line, index) =>
        val regex = index % 2 match {
          case 0 => mowerInitializationRegex
          case 1 => mowerInstructionsRegex
        }
        if (regex.findFirstIn(line).isEmpty) {
          Failure(new DonneesIncorectesException(s"Excepted line ${index.toString} to respect the following format ${regex.toString()}"))
        } else {
          Success(())
        }
    }
    if (results.forall(_.isSuccess)) Success(()) else results.find(_.isFailure).getOrElse(Success(()))
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
    val datas = lines.toList.grouped(2).map {
      case List(initializationData, instructionsData) =>
        val initData = readMowerInitializationData(initializationData)
        val instructions = instructionsData
        domain.MowerInitializationData(initData._1, initData._2, Direction.getDirection(initData._3), instructions)
    }.toList
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
