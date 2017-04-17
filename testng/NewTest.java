package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class NewTest {
  @Test
  public void test() {
	  String str = "TestNG is working fine";
      assertEquals("TestNG is working fine", str);
  }
  @BeforeMethod
  public void beforeMethod() {
  }

}
