package fr._42.reflexions.app;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String PACKAGE_NAME = "fr._42.reflexions.classes";
    private static final Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        try {
            List<Class<?>> classes = getClassesInPackage(PACKAGE_NAME);

            System.out.println("Classes:");
            for (Class<?> clazz : classes) {
                System.out.println(clazz.getSimpleName());
            }

            // Step 2: Select class
            System.out.println("---------------------");
            System.out.println("Enter class name:");
            System.out.print("-> ");
            String className = scanner.nextLine();

            Class<?> selectedClass = Class.forName(PACKAGE_NAME + "." + className);

            // Step 3: Display class info
            System.out.println("---------------------");
            displayClassInfo(selectedClass);

            // Step 4: Create object
            System.out.println("---------------------");
            System.out.println("Let's create an object.");
            Object instance = createObject(selectedClass);
            System.out.println("Object created: " + instance);

            // Step 5: Modify field
            System.out.println("---------------------");
            System.out.println("Enter name of the field for changing:");
            System.out.print("-> ");
            String fieldName = scanner.nextLine();
            modifyField(instance, fieldName);
            System.out.println("Object updated: " + instance);

            // Step 6: Call method
            System.out.println("---------------------");
            System.out.println("Enter name of the method for call:");
            System.out.print("-> ");
            String methodSignature = scanner.nextLine();
            callMethod(instance, methodSignature);


        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    // Get all classes in package
    private static List<Class<?>> getClassesInPackage(String packageName) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);

        System.out.println("resource = " + resource);
        System.out.println("resource protocol = " + resource.getProtocol());


        if (resource == null) {
            throw new IllegalArgumentException("Package not found: " + packageName);
        }

        File directory = new File(resource.getFile());
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".class")) {
                        String className = packageName + '.' +
                                file.getName().substring(0, file.getName().length() - 6);
                        classes.add(Class.forName(className));
                    }
                }
            }
        }

        return classes;
    }


    private static void displayClassInfo(Class<?> clazz) {
        System.out.println("fields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.getName().equals("toString")) {
                System.out.print(method.getReturnType().getSimpleName() + " " +
                        method.getName() + "(");

                Parameter[] params = method.getParameters();
                for (int i = 0; i < params.length; i++) {
                    System.out.print(params[i].getType().getSimpleName());
                    if (i < params.length - 1) System.out.print(", ");
                }
                System.out.println(")");
            }
        }
    }
    // Create object with user input
    private static Object createObject(Class<?> clazz) throws Exception {
        Constructor<?> constructor = null;
        Field[] fields = clazz.getDeclaredFields();

        // Try to find constructor with all fields
        for (Constructor<?> c : clazz.getConstructors()) {
            if (c.getParameterCount() == fields.length) {
                constructor = c;
                break;
            }
        }

        if (constructor == null) {
            // Use empty constructor
            return clazz.getDeclaredConstructor().newInstance();
        }

        // Get values for each field
        Object[] args = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName() + ":");
            System.out.print("-> ");
            String input = scanner.nextLine();
            args[i] = convertValue(input, fields[i].getType());
        }

        return constructor.newInstance(args);
    }

    // Convert string to appropriate type
    private static Object convertValue(String input, Class<?> type) {
        if (type == String.class) return input;
        if (type == int.class || type == Integer.class) return Integer.parseInt(input);
        if (type == double.class || type == Double.class) return Double.parseDouble(input);
        if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(input);
        if (type == long.class || type == Long.class) return Long.parseLong(input);
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
    // Modify field value
    private static void modifyField(Object instance, String fieldName) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        System.out.println("Enter " + field.getType().getSimpleName() + " value:");
        System.out.print("-> ");
        String input = scanner.nextLine();

        Object value = convertValue(input, field.getType());
        field.set(instance, value);
    }

    // Call method
    private static void callMethod(Object instance, String methodSignature) throws Exception {
        // Parse method signature: "grow(int)"
        String methodName = methodSignature.substring(0, methodSignature.indexOf('('));
        String paramsStr = methodSignature.substring(
                methodSignature.indexOf('(') + 1,
                methodSignature.indexOf(')')
        );

        // Get parameter types
        String[] paramTypeNames = paramsStr.isEmpty() ? new String[0] : paramsStr.split(",\\s*");
        Class<?>[] paramTypes = new Class[paramTypeNames.length];
        Object[] paramValues = new Object[paramTypeNames.length];

        for (int i = 0; i < paramTypeNames.length; i++) {
            paramTypes[i] = getClassForName(paramTypeNames[i].trim());

            System.out.println("Enter " + paramTypeNames[i] + " value:");
            System.out.print("-> ");
            String input = scanner.nextLine();
            paramValues[i] = convertValue(input, paramTypes[i]);
        }

        // Get method
        Method method = instance.getClass().getMethod(methodName, paramTypes);

        // Invoke method
        Object result = method.invoke(instance, paramValues);

        // Display result if not void
        if (!method.getReturnType().equals(void.class)) {
            System.out.println("Method returned:");
            System.out.println(result);
        }
    }

    // Get Class object from type name
    private static Class<?> getClassForName(String typeName) throws ClassNotFoundException {
        return switch (typeName) {
            case "int" -> int.class;
            case "double" -> double.class;
            case "boolean" -> boolean.class;
            case "long" -> long.class;
            case "String" -> String.class;
            case "Integer" -> Integer.class;
            case "Double" -> Double.class;
            case "Boolean" -> Boolean.class;
            case "Long" -> Long.class;
            default -> Class.forName(typeName);
        };
    }


}
