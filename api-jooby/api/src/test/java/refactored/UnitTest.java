package refactored;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
  @Test
  public void welcome() {
    Init controller = new Init();
    assertEquals("Welcome to Jooby!", controller.sayHi());
  }
}
