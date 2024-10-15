# Mockito Cheat Sheet

## 1. Creating Mocks

```java
// Using annotation
@Mock
private MyClass mockObject;

// Or using Mockito.mock()
MyClass mockObject = Mockito.mock(MyClass.class);
```

## 2. Stubbing Method Calls

```java
// Return a value
when(mockObject.someMethod()).thenReturn(value);

// Throw an exception
when(mockObject.someMethod()).thenThrow(new Exception());

// Return different values on consecutive calls
when(mockObject.someMethod()).thenReturn(value1, value2, value3);

// Return based on the input argument
when(mockObject.someMethod(anyString())).thenAnswer(invocation -> {
    String arg = invocation.getArgument(0);
    return "Result for " + arg;
});
```

## 3. Argument Matchers

```java
// Any value of a given type
when(mockObject.someMethod(any(String.class))).thenReturn(value);

// Specific matchers
when(mockObject.someMethod(anyInt(), eq("specific string"))).thenReturn(value);

// Custom matcher
when(mockObject.someMethod(argThat(str -> str.length() > 5))).thenReturn(value);
```

## 4. Verifying Method Calls

```java
// Verify a method was called
verify(mockObject).someMethod();

// Verify with specific arguments
verify(mockObject).someMethod("expected arg");

// Verify number of invocations
verify(mockObject, times(3)).someMethod();
verify(mockObject, atLeastOnce()).someMethod();
verify(mockObject, atMost(2)).someMethod();
verify(mockObject, never()).someMethod();

// Verify no more interactions
verifyNoMoreInteractions(mockObject);
```

## 5. Capturing Arguments

```java
ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
verify(mockObject).someMethod(captor.capture());
String capturedArgument = captor.getValue();
```

## 6. Spying on Real Objects

```java
// Create a spy
MyClass spyObject = spy(new MyClass());

// Stub a method on the spy
doReturn(value).when(spyObject).someMethod();
```

## 7. Mocking Void Methods

```java
// Stubbing void method to do nothing
doNothing().when(mockObject).voidMethod();

// Stubbing void method to throw exception
doThrow(new Exception()).when(mockObject).voidMethod();
```

## 8. Verifying Order of Method Calls

```java
InOrder inOrder = inOrder(mockObject1, mockObject2);
inOrder.verify(mockObject1).someMethod();
inOrder.verify(mockObject2).anotherMethod();
```

## 9. Resetting Mocks

```java
// Reset all invocations and stubbing
reset(mockObject);
```

## 10. Strict Stubbing

```java
// Fail if stubbed method is not used
strictness(Strictness.STRICT_STUBS);
```

## 11. Mocking Static Methods (Mockito 3.4.0+)

```java
try (MockedStatic<UtilityClass> mockedStatic = mockStatic(UtilityClass.class)) {
    mockedStatic.when(() -> UtilityClass.staticMethod()).thenReturn(value);
}
```

## 12. BDD Style Mocking

```java
// Given
given(mockObject.someMethod()).willReturn(value);

// When
result = classUnderTest.methodUnderTest();

// Then
then(mockObject).should().someMethod();
```

## Common Scenarios

1. Mocking dependencies in a unit test
2. Verifying that a method was called with specific arguments
3. Stubbing method calls to return specific values or throw exceptions
4. Capturing and asserting on method arguments
5. Testing error handling by making mocks throw exceptions
6. Verifying the order of method calls
7. Spying on real objects to partially mock their behavior
8. Using argument matchers for flexible stubbing and verification
9. Mocking void methods to verify they were called or to make them throw exceptions
10. Resetting mocks between tests or within a single test
