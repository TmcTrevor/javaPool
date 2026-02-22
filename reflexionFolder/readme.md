# Java Reflection Project

## Description
This project demonstrates the practical application of Java Reflection API to dynamically inspect and manipulate classes, fields, and methods at runtime. The program allows users to:
1. Discover classes in a specified package
2. Inspect class fields and methods
3. Create class instances dynamically
4. Modify field values at runtime
5. Invoke methods dynamically

## Project Structure
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── app/
│   │   │   │   └── Main.java
│   │   │   └── classes/
│   │   │       ├── Fighter.java
│   │   │       └── Enchanter.java
│   └── pom.xml
```

## Key Features
- Dynamic class discovery using Reflections library
- Runtime object creation with parameter input
- Field modification through reflection
- Dynamic method invocation
- Type conversion support for primitive and wrapper types

## Technical Overview

### Class Discovery
The project uses the `org.reflections` library to scan packages and discover available classes:
```java
public static HashMap<String, Class<?>> findAllClassesUsingReflectionsLibrary(String packageName)
```

### Field & Method Inspection
Methods are provided to inspect class members:
- Field inspection with type information
- Method inspection including return types and parameters
- Support for various data types (String, Integer, Double, Boolean, Long)

### Dynamic Object Creation
The program supports:
- Constructor parameter type matching
- Value type conversion
- Runtime instance creation
- Error handling for invalid inputs

## How to Use

1. Run the program
2. View available classes in the package
3. Select a class to inspect
4. Create an instance by providing field values
5. Modify fields of the created instance
6. Call methods on the instance

## Example Usage
```
Classes:
Fighter
Enchanter

Enter class name:
-> Fighter

Fields (Attributes):
    String name
    Integer attackDmg
    Integer health

Methods:
    void attack(Fighter)
    String toString()
```

---

# Java Reflection Guide

## What is Reflection?
Java Reflection is a feature that enables you to inspect and manipulate classes, interfaces, constructors, methods, and fields at runtime.

## Key Components

### 1. Class<?> Object
- Entry point for reflection operations
- Obtained using `Class.forName()` or `.getClass()`
```java
Class<?> clazz = Class.forName("com.example.MyClass");
```

### 2. Fields
- Represent class attributes
- Accessed using `getDeclaredFields()`
```java
Field[] fields = class.getDeclaredFields();
field.setAccessible(true); // For private fields
```

### 3. Methods
- Represent class behaviors
- Accessed using `getDeclaredMethods()`
```java
Method[] methods = class.getDeclaredMethods();
method.invoke(object, parameters);
```

### 4. Constructors
- Create new instances
- Accessed using `getConstructor()`
```java
Constructor<?> constructor = class.getConstructor(parameterTypes);
Object instance = constructor.newInstance(arguments);
```

## Common Use Cases

1. **Dynamic Class Loading**
    - Load classes based on runtime conditions
    - Create instances without compile-time dependencies

2. **Framework Development**
    - Dependency injection
    - Plugin architectures
    - ORM implementations

3. **Testing and Debugging**
    - Accessing private members
    - Runtime behavior inspection

## Best Practices

1. **Performance Considerations**
    - Cache reflection objects when possible
    - Use reflection sparingly in performance-critical code

2. **Security**
    - Be cautious with `setAccessible(true)`
    - Consider security implications in production code

3. **Error Handling**
    - Always handle reflection exceptions
    - Validate inputs before reflection operations

4. **Maintainability**
    - Document reflection usage clearly
    - Consider alternatives when simple direct access is possible

## Common Reflection Operations

### Getting Class Object
```java
// Using class literal
Class<?> clazz1 = String.class;

// Using getClass()
String str = "Hello";
Class<?> clazz2 = str.getClass();

// Using Class.forName()
Class<?> clazz3 = Class.forName("java.lang.String");
```

### Accessing Fields
```java
// Get all declared fields
Field[] fields = clazz.getDeclaredFields();

// Get specific field
Field field = clazz.getDeclaredField("fieldName");

// Get/Set field value
field.setAccessible(true);
Object value = field.get(object);
field.set(object, newValue);
```

### Working with Methods
```java
// Get all declared methods
Method[] methods = clazz.getDeclaredMethods();

// Get specific method
Method method = clazz.getDeclaredMethod("methodName", parameterTypes);

// Invoke method
method.setAccessible(true);
Object result = method.invoke(object, parameters);
```

## Common Pitfalls

1. **Forgetting Access Control**
    - Remember to use `setAccessible(true)` for private members

2. **Type Safety**
    - Always verify parameter types match method signatures
    - Handle type conversion appropriately

3. **Performance Impact**
    - Reflection operations are slower than direct access
    - Cache reflection objects when possible

4. **Exception Handling**
    - Multiple checked exceptions must be handled
    - Consider wrapping in runtime exceptions for cleaner code

