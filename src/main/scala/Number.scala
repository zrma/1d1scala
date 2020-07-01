trait Number {
  def number: String
  require(number.forall(_.isDigit), "Unable to convert string to number")
}

case class Decimal(number: String) extends Number

case class Binary(number: String) extends Number
