package progfun.utils.parser

import progfun.application.{InitializeMower, MowerInitializationData}
import progfun.domain.Direction

import scala.io.Source
import scala.util.{Failure, Success}

class FileParser(parseValidator: ParseValidator, filePath: String) extends InitializeMower {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  override def validate(): Unit = {
    parseValidator.validate() match {
      case Success(_) =>
      case Failure(exception) => throw exception

    }
  }

  override def getLawnSize(): (Int, Int) = {
    val file = Source.fromFile(filePath)
    val lawnSize = file.getLines().next().mkString.split(" ")
    file.close()
    val x = lawnSize(0).toInt
    val y = lawnSize(1).toInt
    (x, y)
  }

  override def getMowersData(): List[MowerInitializationData] = {
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
