package Decorator;

import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD flow: each method has a failing test first (disabled for now), then a passing test.
 * Enable the failing ones to see red, then confirm green with the passing version.
 */
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

    // ========= Burger.description() =========

    //    @Disabled("Intentional red test: wrong description")
    @Test void burger_description_failing() {
        FoodItem b = new Burger();
        assertEquals("Cheeseburger", b.description()); // wrong on purpose
    }

    @Test void burger_description_passing() {
        FoodItem b = new Burger();
        assertEquals("Burger", b.description());
    }

    // ========= Burger.cost() =========

    //    @Disabled("Intentional red test: wrong price")
    @Test void burger_cost_failing() {
        FoodItem b = new Burger();
        assertEquals(6.00, b.cost(), 1e-6); // wrong on purpose
    }

    @Test void burger_cost_passing() {
        FoodItem b = new Burger();
        assertEquals(5.00, b.cost(), 1e-6);
    }

    // ========= Fries.description() =========

    //    @Disabled("Intentional red test")
    @Test void fries_description_failing() {
        FoodItem f = new Fries();
        assertEquals("French Fries", f.description()); // wrong on purpose
    }

    @Test void fries_description_passing() {
        FoodItem f = new Fries();
        assertEquals("Fries", f.description());
    }

    // ========= Fries.cost() =========

    //    @Disabled("Intentional red test")
    @Test void fries_cost_failing() {
        FoodItem f = new Fries();
        assertEquals(3.00, f.cost(), 1e-6); // wrong on purpose
    }

    @Test void fries_cost_passing() {
        FoodItem f = new Fries();
        assertEquals(2.50, f.cost(), 1e-6);
    }

    // ========= HotDog.description() =========

    //    @Disabled("Intentional red test")
    @Test void hotdog_description_failing() {
        FoodItem h = new HotDog();
        assertEquals("Hotdog", h.description()); // wrong case/spelling on purpose
    }

    @Test void hotdog_description_passing() {
        FoodItem h = new HotDog();
        assertEquals("Hot Dog", h.description());
    }

    // ========= HotDog.cost() =========

    //    @Disabled("Intentional red test")
    @Test void hotdog_cost_failing() {
        FoodItem h = new HotDog();
        assertEquals(3.75, h.cost(), 1e-6); // wrong on purpose
    }

    @Test void hotdog_cost_passing() {
        FoodItem h = new HotDog();
        assertEquals(3.50, h.cost(), 1e-6);
    }

    // ========= Ketchup.description() =========

    //    @Disabled("Intentional red test")
    @Test void ketchup_description_failing() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals("Ketchup + Burger", item.description()); // wrong order on purpose
    }

    @Test void ketchup_description_passing() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals("Burger + Ketchup", item.description());
    }

    // ========= Ketchup.cost() =========

    //    @Disabled("Intentional red test")
    @Test void ketchup_cost_failing() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals(5.10, item.cost(), 1e-6); // wrong (should be 5.20)
    }

    @Test void ketchup_cost_passing() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals(5.00 + 0.20, item.cost(), 1e-6);
    }

    // ========= Cheese.description() =========

    //    @Disabled("Intentional red test")
    @Test void cheese_description_failing() {
        FoodItem item = new Cheese(new Fries());
        assertEquals("Cheese + Fries", item.description()); // wrong order
    }

    @Test void cheese_description_passing() {
        FoodItem item = new Cheese(new Fries());
        assertEquals("Fries + Cheese", item.description());
    }

    // ========= Cheese.cost() =========

    //    @Disabled("Intentional red test")
    @Test void cheese_cost_failing() {
        FoodItem item = new Cheese(new Fries());
        assertEquals(2.80, item.cost(), 1e-6); // wrong (should be 3.00)
    }

    @Test void cheese_cost_passing() {
        FoodItem item = new Cheese(new Fries());
        assertEquals(2.50 + 0.50, item.cost(), 1e-6);
    }

    // ========= Onions.description() =========

    //    @Disabled("Intentional red test")
    @Test void onions_description_failing() {
        FoodItem item = new Onions(new HotDog());
        assertEquals("Onions + Hot Dog", item.description()); // wrong order
    }

    @Test void onions_description_passing() {
        FoodItem item = new Onions(new HotDog());
        assertEquals("Hot Dog + Onions", item.description());
    }

    // ========= Onions.cost() =========

    //    @Disabled("Intentional red test")
    @Test void onions_cost_failing() {
        FoodItem item = new Onions(new HotDog());
        assertEquals(3.90, item.cost(), 1e-6); // wrong (should be 3.80)
    }

    @Test void onions_cost_passing() {
        FoodItem item = new Onions(new HotDog());
        assertEquals(3.50 + 0.30, item.cost(), 1e-6);
    }

    // ========= Order.add() + Order.subtotal() =========

    //    @Disabled("Intentional red test")
    @Test void order_subtotal_failing() {
        Order order = new Order();
        order.add(new Cheese(new Ketchup(new Burger()))); // 5.00 + .20 + .50 = 5.70
        order.add(new Onions(new Cheese(new HotDog())));  // 3.50 + .50 + .30 = 4.30
        order.add(new Ketchup(new Fries()));              // 2.50 + .20 = 2.70
        assertEquals(12.50, order.subtotal(), 1e-6); // wrong (should be 12.70)
    }

    @Test void order_subtotal_passing() {
        Order order = new Order();
        order.add(new Cheese(new Ketchup(new Burger()))); // 5.70
        order.add(new Onions(new Cheese(new HotDog())));  // 4.30
        order.add(new Ketchup(new Fries()));              // 2.70
        assertEquals(12.70, order.subtotal(), 1e-6);
    }

    // ========= Order.printItems() =========

    //    @Disabled("Intentional red test")
    @Test void order_printItems_failing() {
        Order order = new Order();
        FoodItem item = new Cheese(new Ketchup(new Burger())); // "Burger + Ketchup + Cheese"
        order.add(item);
        order.printItems();
        String out = capture.toString();
        assertTrue(out.contains("Burger + Cheese + Ketchup"), "wrong order on purpose");
    }

    @Test void order_printItems_passing() {
        Order order = new Order();
        FoodItem item = new Cheese(new Ketchup(new Burger())); // "Burger + Ketchup + Cheese"
        order.add(item);
        order.printItems();
        String out = capture.toString();
        assertTrue(out.contains("Burger + Ketchup + Cheese"));
        assertTrue(out.contains("$5.70"));
    }

    // ========= LoyaltyStatus.applyDiscount() =========

    //    @Disabled("Intentional red test")
    @Test void loyalty_applyDiscount_failing() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.GOLD); // 10%
        assertEquals(100.0 * 0.80, status.applyDiscount(100.0), 1e-6); // wrong (should be 0.90)
    }

    @Test void loyalty_applyDiscount_passing() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.GOLD);
        assertEquals(100.0 * 0.90, status.applyDiscount(100.0), 1e-6);
    }

    // ========= LoyaltyStatus.tier() =========

    //    @Disabled("Intentional red test")
    @Test void loyalty_tierGetter_failing() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.SILVER);
        assertEquals(LoyaltyTier.GOLD, status.tier()); // wrong on purpose
    }

    @Test void loyalty_tierGetter_passing() {
        LoyaltyStatus status = new LoyaltyStatus(LoyaltyTier.SILVER);
        assertEquals(LoyaltyTier.SILVER, status.tier());
    }

    // ========= Stacked decorator sanity (integration) =========

    //    @Disabled("Intentional red test")
    @Test void stacked_decorators_failing() {
        FoodItem item = new Onions(new Cheese(new Ketchup(new Burger())));
        // wrong total on purpose: 5 + .2 + .5 + .3 = 6.00 (correct), we assert 6.10 to fail
        assertEquals(6.10, item.cost(), 1e-6);
    }

    @Test void stacked_decorators_passing() {
        FoodItem item = new Onions(new Cheese(new Ketchup(new Burger())));
        assertEquals(6.00, item.cost(), 1e-6);
        assertEquals("Burger + Ketchup + Cheese + Onions", item.description());
    }
}

