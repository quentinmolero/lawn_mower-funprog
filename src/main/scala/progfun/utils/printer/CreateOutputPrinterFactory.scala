package progfun.utils.printer

class CreateOutputPrinterFactory {
  def createOutputPrinter(printerType: String): OutputPrinter = {
    printerType match {
      case "console" => new ConsoleOutputPrinter()
      case _         => new ConsoleOutputPrinter()
    }
  }
}
