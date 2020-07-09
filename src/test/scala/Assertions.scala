import org.scalatest.funsuite.AnyFunSuite

class Assertions extends AnyFunSuite {
  test("one plus one") {
    val two = 2
    val three = 3
    assert(1 + 1 == two)
    assert(1 + 1 != three)
  }

  test("one plus one with result") {
    val two = 2
    assertResult(two) { 1 + 1 }
  }

  test("intercept1") {
    try {
      val res = 3 / 0
      fail("Shouldn't be here")
    } catch {
      case _: ArithmeticException =>
        println("success")
      case _ => fail("Unexpected exception thrown")
    }
  }

  test("intercept2") {
    intercept[ArithmeticException] {
      val res = 3 / 0
      fail("Shouldn't be here")
    }
  }

  test("assumption") {
    val two = 2
    val three = 3
    assume(1 + 1 == two, "Wow!")
    assume(1 + 1 == three, "Oh!!")
  }

  test("cancellation") {
    cancel("I cancelled it deliberately")
    fail("Shouldn't be here")
  }
}
