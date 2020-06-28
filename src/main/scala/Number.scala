trait Number {
  def number: String
}

case class Decimal(number: String) extends Number

case class Binary(number: String) extends Number