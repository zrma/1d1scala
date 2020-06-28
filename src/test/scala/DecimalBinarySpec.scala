import org.scalatest.flatspec.AnyFlatSpec

class DecimalBinarySpec extends AnyFlatSpec {
  "base conversion utility" should "convert a number 99 into a binary number 1100011" in {
    var binary: Binary = BaseConversion.decimalToBinary(Decimal("99"))
    assert(binary.number == "1100011")
  }
  it should "convert a number 245 into a binary number 11110101" in pending
  it should "convert a number 3141 into a binary number 110001000101" in pending
}
