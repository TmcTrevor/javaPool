# JUnit 5 Cheat Sheet

## 1. Basic Test Structure

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyClassTest {
    @Test
    void testMethod() {
        // Test code
        assertEquals(expected, actual);
    }
}
```

## 2. Assertions

```java
assertEquals(expected, actual);
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertThrows(ExpectedException.class, () -> { /* code */ });
assertAll("heading",
    () -> assertEquals(expected1, actual1),
    () -> assertEquals(expected2, actual2)
);
```

## 3. Lifecycle Methods

```java
@BeforeAll
static void setUpAll() { /* Runs once before all tests */ }

@AfterAll
static void tearDownAll() { /* Runs once after all tests */ }

@BeforeEach
void setUp() { /* Runs before each test */ }

@AfterEach
void tearDown() { /* Runs after each test */ }
```

## 4. Parameterized Tests

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testWithInts(int arg) {
    assertTrue(arg > 0);
}

@ParameterizedTest
@CsvSource({"1,One", "2,Two", "3,Three"})
void testWithCsv(int arg1, String arg2) {
    assertNotNull(arg2);
}
```

## 5. Assumptions

```java
assumeTrue(condition);
assumeFalse(condition);
assumingThat(condition, () -> {
    // Test code
});
```

## 6. Tagging and Filtering

```java
@Tag("integration")
@Test
void testIntegration() {
    // Integration test code
}
```

## 7. Nested Tests

```java
@Nested
class NestedTestClass {
    @Test
    void nestedTest() {
        // Nested test code
    }
}
```

## 8. Repeated Tests

```java
@RepeatedTest(3)
void repeatedTest() {
    // Test code to be repeated
}
```

## 9. Timeout Tests

```java
@Test
@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
void timeoutTest() {
    // Test code that should complete within 500ms
}
```

## 10. Disabling Tests

```java
@Disabled("Reason for disabling")
@Test
void disabledTest() {
    // This test will not run
}
```

## 11. Test Execution Order

```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTestsClass {
    @Test
    @Order(1)
    void firstTest() {}

    @Test
    @Order(2)
    void secondTest() {}
}
```

## 12. Custom Failure Messages

```java
assertEquals(expected, actual, "Custom failure message");
assertTrue(condition, () -> "Failure message with " + "expensive computation");
```

## Common Scenarios

1. Writing basic unit tests for methods
2. Setting up test environments with @BeforeEach and @AfterEach
3. Grouping related tests using nested test classes
4. Parameterizing tests to run with multiple inputs
5. Testing exception throwing with assertThrows
6. Using assumptions to conditionally run tests
7. Tagging tests for selective execution
8. Timing out long-running tests
9. Disabling tests that are not ready or are broken
10. Ordering test execution when necessary

