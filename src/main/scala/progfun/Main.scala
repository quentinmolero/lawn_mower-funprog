package fr.esgi.al.funprog

import progfun.application.{MowerEngine, Parser}

object Main extends App {
  if (args.length != 1) {
    println("Usage: sbt \"runMain fr.esgi.al.funprog.Main <file>\"")
    System.exit(1)
  } else {
    val filePath = args(0)
    val parser = new Parser(filePath).validateFile()
    val mowerEngine = new MowerEngine(parser)
    val mowerResult = mowerEngine.calculateMowerResult
    println(mowerResult.toJSON)
    println(mowerResult.toCSV)
  }
}
