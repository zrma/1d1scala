import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.matchers.{BeMatcher, MatchResult}

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

  it should "Matchers for containers" in {
    List("Alice", "Bob") should contain("Bob")

    import org.scalactic._ // Can provide a custom Equality[L] explicitly
    import StringNormalizations._

    (List("Hi", "Di", "Ho") should contain("ho"))(after being lowerCased)

    List(1, 2, 3, 4, 5) should contain oneOf (5, 7, 9)
    List(1, 2, 3, 4, 5) should contain noneOf (7, 8, 9)
    Some(0) should contain noneOf (7, 8, 9)
    (Array("Doe", "Ray", "Me") should contain oneOf ("X", "RAY", "BEAM"))(
      after being lowerCased
    )

    List(1, 2, 2, 3, 3, 3) should contain inOrderOnly (1, 2, 3)
    List(0, 1, 2, 2, 99, 3, 3, 3, 5) should contain inOrder (1, 2, 3)
    List(1, 2, 3) should contain theSameElementsInOrderAs collection.mutable
      .TreeSet(3, 2, 1)

    List(1, 2, 3) shouldBe sorted

    import org.scalatest.Inspectors._

    val xs = List(1, 2, 3)
    forAll(xs) { x =>
      x should be < 10
    }

    all(xs) should be < 10

    all(xs) should be > 0
    atMost(2, xs) should be >= 4
    atLeast(3, xs) should be < 5
    between(2, 3, xs) should (be > 1 and be < 5)
    exactly(2, xs) should be <= 2
    every(xs) should be < 10
  }

  it should "Combining Matchers with logical expressions" in {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3, "ouch" -> 999)
    val traversable = List(1, 2, 3, 4, 5, 6, 7)

    map should (contain key ("two") and not contain value(7))
    traversable should (contain(7) or (contain(8) and have size (9)))
    map should (not be (null) and contain key ("ouch"))
  }

  it should "Matching options" in {
    val map = Map("one" -> 1, "two" -> 2, "three" -> 3, "ouch" -> 999)

    {
      val option = map.get("four")

      option shouldEqual None
      option shouldBe None
      option should ===(None)
      option shouldBe empty
    }

    {
      val option = map.get("three")

      option shouldEqual Some(3)
      option shouldBe defined
    }
  }
}
