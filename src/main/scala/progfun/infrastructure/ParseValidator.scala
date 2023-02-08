package progfun.infrastructure

import scala.util.Try

trait ParseValidator {
  def validate(): Try[Any];
}
