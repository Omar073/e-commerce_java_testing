package Classes;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    CartItemTest.class,
    OrderTest.class,
    ProductTest.class,
    CustomerTest.class,
    AdminTest.class,
    ShopTest.class
    
})
public class Testsuite {
    
}
