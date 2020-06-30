import scala.annotation.tailrec

object BaseConversion {
  def decimalToBinary(decimal: Decimal): Binary = {
    Binary(toBinary(BigInt(decimal.number), ""))
  }

  def binaryToDecimal(binary: Binary): Decimal = {
    Decimal(toDecimal(binary.number))
  }

  @tailrec private def toBinary(num: BigInt, acc: String): String = {
    if (num < 2) num.toString + acc
    else toBinary(num / 2, (num mod 2).toString ++ acc)
  }

  private def toDecimal(num: String): String = {
    num.map(_.asDigit).reduceLeft(_ * 2 + _).toString
  }
}
