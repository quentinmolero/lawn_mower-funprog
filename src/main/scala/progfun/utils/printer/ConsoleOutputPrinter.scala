package progfun.utils.printer

class ConsoleOutputPrinter extends OutputPrinter {
  override def print(message: String): Unit = {
    println(message)
  }
}
