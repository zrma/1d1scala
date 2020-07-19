import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.{BeMatcher, MatchResult}
import org.scalatest.matchers.should.Matchers

class Matcher extends AnyFlatSpec with Matchers {
  val message = "Hello World"

  it should "Matchers for equality" in {
    message should be("Hello World")
    message shouldBe "Hello World"
    message should equal("Hello World")
    message should ===("Hello World")
    message shouldEqual "Hello World"
  }

  val array: Array[Int] = Array(1, 2, 3)

  it should "Matchers for instance and identity checks of objects" in {
    message shouldBe a[String]
    12.34 should not be an[Integer]
    List(1, 2, 3) shouldBe a[Seq[_]]

    array should be theSameInstanceAs array
  }

  it should "Matchers for size and length" in {
    message should have length 11
    array should have size 3
  }

  it should "Matching strings" in {
    message should startWith("Hello")
    message should endWith("rld")
    message should not include ("earth")
    message should fullyMatch regex ("[A-Za-z]+\\s[A-Za-z]+")

    "123zyx321" should startWith regex ("([0-9]+)" withGroups ("123"))
  }

  it should "Matching greater and less than" in {
    6 should be < 7
    7 should be <= 7
    7 should be >= 7
    8 should be > 7
  }

  it should "Matching number within ranges" in {
    var voltage = 11.6
    voltage should equal(12.0 +- 0.5)

    voltage = 12.1
    voltage should be(12.0 +- 0.5)

    voltage = 243
    voltage shouldBe 240.0 +- 10
  }

  it should "Matching emptiness" in {
    None shouldBe empty
    Some(1) should not be empty
    "" shouldBe empty
    new java.util.HashMap[Int, Int] shouldBe empty
    new { def isEmpty = true } shouldBe empty
    Array(1, 2, 3) should not be empty
  }

  it should "Custom BeMatchers" in {
    class LowerCase extends BeMatcher[String] {
      override def apply(left: String): MatchResult =
        MatchResult(
          left.forall(_.isLower),
          left + " is lowercase",
          left + "is not lowercase"
        )
    }

    val lowercase = new LowerCase
    "message" shouldBe lowercase
    "Message" should not be lowercase
  }
}
