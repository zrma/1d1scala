import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Matcher extends AnyFlatSpec with Matchers {
  "Hello World" should be("Hello World")
  "Hello World" shouldBe "Hello World"
  "Hello World" should equal("Hello World")
  "Hello World" should ===("Hello World")
  "Hello World" shouldEqual "Hello World"

  "Hello World" shouldBe a[String]
  12.34 should not be an[Integer]
  List(1, 2, 3) shouldBe a[Seq[_]]

  val array: Array[Int] = Array(1, 2, 3)
  array should be theSameInstanceAs array
}
