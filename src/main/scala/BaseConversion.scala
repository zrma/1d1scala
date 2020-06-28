import scala.annotation.tailrec

object BaseConversion {
  def decimalToBinary(decimal: Decimal): Binary = {
    Binary(toBinary(BigInt(decimal.number), ""))
  }

  @tailrec private def toBinary(num: BigInt, acc: String): String = {
    if (num < 2) {
      num.toString + acc
    }
    else {
      toBinary(num / 2, (num mod 2).toString ++ acc)
    }
  }
}
