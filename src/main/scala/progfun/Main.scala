package fr.esgi.al.funprog

import progfun.application.InitializeMower
import progfun.application.usecases.mowerengine.{MowerEngine, MowerEngineProps}
import progfun.domain.Lawn
import progfun.infrastructure.responses.MowerResultAdapter
import progfun.infrastructure.{FileParser, ParseValidator, SimpleParseValidator}

object Main extends App {
  if (args.length != 1) {
    println("Usage: sbt \"runMain fr.esgi.al.funprog.Main <file>\"")
    System.exit(1)
  } else {
    val filePath = args(0)
    val parserValidator: ParseValidator = new SimpleParseValidator(filePath)
    val parser: InitializeMower = new FileParser(parserValidator, filePath)
    parser.validate()

    val mowerEngineProps = MowerEngineProps(new Lawn(parser.getLawnSize()), parser.getMowersData())

    print(mowerEngineProps)
    val mowerResult = new MowerEngine(mowerEngineProps).calculateMowerResult
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)
    println(mowerResultAdapter.toJSON)
    println(mowerResultAdapter.toCSV)
  }


}
