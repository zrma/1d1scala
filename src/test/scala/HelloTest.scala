import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import org.scalatest.propspec.AnyPropSpec
import org.scalatest.refspec.RefSpec
import org.scalatest.wordspec.AnyWordSpec;

class HelloFunSuite extends AnyFunSuite {
  test("Hello.displaySalutation returns 'Hello World'") {
    assert(Hello.displaySalutation == "Hello World")
  }
}

class HelloFlat extends AnyFlatSpec {
  "Hello.displaySalutation" should "returns 'Hello World'" in {
    assert(Hello.displaySalutation == "Hello World")
  }
}

class HelloFun extends AnyFunSpec {
  describe("Hello") {
    describe("displaySalutation") {
      it("returns 'Hello World'") {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}

class HelloWord extends AnyWordSpec {
  "Hello" when {
    "displaySalutation" should {
      "returns 'Hello World'" in {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}

class HelloFree extends AnyFreeSpec {
  "Hello" - {
    "displaySalutation" - {
      "returns 'Hello World'" in {
        assert(Hello.displaySalutation == "Hello World")
      }
    }
  }
}

class HelloSpec extends RefSpec {

  object `Hello object` {

    object `when displaySalutation` {
      def `should returns 'Hello World'`() {
        assert(Hello.displaySalutation == "Hello World")
      }

    }

  }

}

class HelloPropSpec extends AnyPropSpec with TableDrivenPropertyChecks with Matchers {
  val examples: TableFor1[String] =
    Table(
      {
        "expected"
      },
      {
        "Hello World"
      },
    )
  property("Hello.displaySalutation test") {
    forAll(examples) {
      (expected) =>
        (Hello.displaySalutation) should be(expected)
    }
  }
}

class HelloFeature extends AnyFeatureSpec with GivenWhenThen {
  info("Test example")
  info("with FeatureSpec")
  Feature("") {
    Scenario("Hello.displaySalutation") {
      Given("object Hello")
      val hello = Hello;
      When("method displaySalutation")
      var result = hello.displaySalutation
      Then("returns 'Hello World'")
      assert(Hello.displaySalutation == "Hello World")
    }
  }
}
