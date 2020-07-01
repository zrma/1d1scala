import org.scalatest.flatspec.AnyFlatSpec

class BeanSpec extends AnyFlatSpec {
  "Decimal" should "throw error when initialised with a non numeric string" in {
    try {
      Decimal("XYZ")
    } catch {
      case e: IllegalArgumentException =>
        assert(
          e.getMessage == "requirement failed: Unable to convert string to number"
        )
      case _ => fail
    }
  }
  "Binary" should "throw error when initialised with a non numeric string" in {
    intercept[IllegalArgumentException] {
      Binary("XYZ")
    }
  }
}
