package fr.esgi.al.funprog

import progfun.application.InitializeMower
import progfun.application.usecases.mowerengine.{MowerEngine, MowerEngineProps}
import progfun.domain.Lawn
import progfun.infrastructure.adapters.MowerResultAdapter
import progfun.utils.parser.{FileParser, ParseValidator, SimpleParseValidator}
import progfun.utils.printer.{ConsoleOutputPrinter, CreateOutputPrinterFactory, OutputPrinter}

object Main extends App {
  if (args.length != 1) {
    println("Usage: sbt \"runMain fr.esgi.al.funprog.Main <file>\"")
    System.exit(1)
  } else {
    val filePath = args(0)
    val parserValidator: ParseValidator = new SimpleParseValidator(filePath)
    val parser: InitializeMower = new FileParser(parserValidator, filePath, new ConsoleOutputPrinter(), "Vous pouvez par exemple utilisez ce genre de contenu:\n  5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA")
    parser.validate()

    val outputPrinter: OutputPrinter = new CreateOutputPrinterFactory().createOutputPrinter("console")

    val mowerEngineProps = MowerEngineProps(new Lawn(parser.getLawnSize()), parser.getMowersData())

    print(mowerEngineProps)
    val mowerResult = new MowerEngine(mowerEngineProps).calculateMowerResult
    val mowerResultAdapter = new MowerResultAdapter(mowerResult)

    outputPrinter.print(mowerResultAdapter.toJSON)
    outputPrinter.print(mowerResultAdapter.toCSV)
  }


}
