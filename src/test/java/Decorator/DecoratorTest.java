package Decorator;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capture;

    @BeforeEach
    void setUp() {
        capture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capture));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test void burgerDescription() {
        FoodItem b = new Burger();
        assertEquals("Burger", b.description());
    }

    @Test void burgerCost() {
        FoodItem b = new Burger();
        assertEquals(5.00, b.cost(), 1e-2);
    }

    @Test void friesDescription() {
        FoodItem f = new Fries();
        assertEquals("Fries", f.description());
    }

    @Test void friesCost() {
        FoodItem f = new Fries();
        assertEquals(2.50, f.cost(), 1e-2);
    }

    @Test void hotDogDescription() {
        FoodItem h = new HotDog();
        assertEquals("Hot Dog", h.description());
    }

    @Test void hotDogCost() {
        FoodItem h = new HotDog();
        assertEquals(3.50, h.cost(), 1e-2);
    }

    @Test void ketchupDescription() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals("Burger + Ketchup", item.description());
    }

    @Test void ketchupCost() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals(5.00 + 0.20, item.cost(), 1e-2);
    }

    @Test void cheeseDescription() {
        FoodItem item = new Cheese(new Fries());
        assertEquals("Fries + Cheese", item.description());
    }

    @Test void cheeseCost() {
        FoodItem item = new Cheese(new Fries());
        assertEquals(2.50 + 0.50, item.cost(), 1e-2);
    }

    @Test void onionsDescription() {
        FoodItem item = new Onions(new HotDog());
        assertEquals("Hot Dog + Onions", item.description());
    }

    @Test void onionsCost() {
        FoodItem item = new Onions(new HotDog());
        assertEquals(3.50 + 0.30, item.cost(), 1e-2);
    }

    @Test void orderSubtotal() {
        Order order = new Order();
        order.add(new Cheese(new Ketchup(new Burger()))); // 5.70
        order.add(new Onions(new Cheese(new HotDog())));  // 4.30
        order.add(new Ketchup(new Fries()));              // 2.70
        assertEquals(12.70, order.subtotal(), 1e-2);
    }

    @Test void orderPrintItems() {
        Order order = new Order();
        FoodItem item = new Cheese(new Ketchup(new Burger())); // "Burger + Ketchup + Cheese"
        order.add(item);
        order.printItems();
        String out = capture.toString();
        assertTrue(out.contains("Burger + Ketchup + Cheese"));
        assertTrue(out.contains("$5.70"));
    }

    @Test void loyaltyApplyDiscount() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.GOLD);
        assertEquals(100.0 * 0.90, status.applyDiscount(100.0), 1e-2);
    }

    @Test void loyaltyTierGetter() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.SILVER);
        assertEquals(LoyaltyTier.SILVER, status.tier());
    }

    @Test void stackedDecorators() {
        FoodItem item = new Onions(new Cheese(new Ketchup(new Burger())));
        assertEquals(6.00, item.cost(), 1e-2);
        assertEquals("Burger + Ketchup + Cheese + Onions", item.description());
    }
}

