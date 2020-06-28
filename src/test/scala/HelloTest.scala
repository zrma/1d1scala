import org.scalatest.flatspec.AnyFlatSpec;

class HelloTest extends AnyFlatSpec  {
  "Class Hello" should "displaySalutation returns 'Hello World'" in {
    assert(Hello.displaySalutation == "Hello World")
  }
}
