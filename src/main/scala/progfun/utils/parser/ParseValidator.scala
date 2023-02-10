package progfun.utils.parser

import scala.util.Try

trait ParseValidator {
  def validate(): Try[Any];
}
