package fr.esgi.al.funprog

import progfun.application.MowerEngine
import progfun.infrastructure.responses.MowerResultAdapter
import progfun.infrastructure.{FileParser, ParseValidator, SimpleParseValidator}

object Main extends App {
  if (args.length != 1) {
    println("Usage: sbt \"runMain fr.esgi.al.funprog.Main <file>\"")
    System.exit(1)
  } else {
    val filePath = args(0)
    val parserValidator = new SimpleParseValidator(filePath)
    val parser = new FileParser(parserValidator, filePath)
    parser.validate()
    val mowerEngine = new MowerEngine(parser)
    val mowerResult = mowerEngine.calculateMowerResult
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)
    println(mowerResultAdapter.toJSON)
    println(mowerResultAdapter.toCSV)
  }


}
