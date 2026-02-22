package app;

import classes.Enchanter;
import classes.Fighter;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


//    public static Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
//        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
//        return new HashSet<>(reflections.getSubTypesOf(Object.class));
//    }

    private static Class<?>[] getTypes(Field[] fields) {
        Class<?>[] types = new Class<?>[fields.length];
        for (int i = 0; i < fields.length; i++) {
            types[i] = fields[i].getType();
        }
        return types;
    }

    private static Class<?>[] getTypes(Method[] methods, String methodName) {
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method.getParameterTypes();
            }
        }
        return null;
    }

    private static Object convert(String input, Class<?> type) {
        input = input.trim();
        if (type.equals(String.class)) {
            return input;
        } else if (type.equals(Integer.class) || type.equals(int.class)) {
            return Integer.parseInt(input);
        } else if (type.equals(Double.class) || type.equals(double.class)) {
            return Double.parseDouble(input);
        } else if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return Boolean.parseBoolean(input);
        } else if (type.equals(Long.class) || type.equals(long.class)) {
            return Long.parseLong(input);
        } else {
            return null;
        }
    }
public static HashMap<String, Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
    // Scan the package and get all subtypes of Object (all classes)
    Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
    Set<Class<?>> classSet = reflections.getSubTypesOf(Object.class);

    // Create a HashMap to store class names as keys and Class<?> objects as values
    HashMap<String, Class<?>> classMap = new HashMap<>();

    // Populate the map with class names and corresponding Class<?> objects
    for (Class<?> clazz : classSet) {
        classMap.put(clazz.getSimpleName(), clazz);
    }

    return classMap;
}

    public static void displayClassInfo(Class<?> selectedClass) throws Exception {
        // Display class name
//        System.out.println("Selected class: " + selectedClass.getSimpleName());

        // Display fields (attributes)
        System.out.println("\nFields (Attributes):");
        Field[] fields = selectedClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
        }

        // Display methods
        System.out.println("\nMethods:");
        Method[] methods = selectedClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName());
            if (method.getParameterTypes().length == 0) {
                System.out.println("()");
            }
            else {
                System.out.print("(");
                for (int i = 0; i < method.getParameterTypes().length; i++) {
                    System.out.print(method.getParameterTypes()[i].getSimpleName());
                    if (i != method.getParameterTypes().length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
            //                   + Arrays.toString(method.getParameterTypes()) + ")"));
        }

        Object object = createInstance(selectedClass, fields);
        UpdateField(selectedClass, fields, object);
        methodCall(selectedClass, methods, object);
    }



    static Object createInstance(Class<?> clazz, Field[] fields) throws Exception
    {
        try {
            System.out.println("---------------------");
            System.err.println("Let's create an object.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("---------------------555555");
            Constructor<?> constructor = clazz.getConstructor(getTypes(fields));
            System.out.println("--------------------- 3333");
            Object[] values = new Object[fields.length];
            System.out.println("---------------------44444");
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getName() + ": ");
                System.out.print("-> ");
                String input = scanner.nextLine();
                values[i] = convert(input, fields[i].getType());
            }
            Object object = constructor.newInstance(values);
            System.out.println("Object created: " + object);
//            scanner.close();
            return object;
        } catch (Exception e) {
//
//            e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
        }
        return null;

    }

    static void UpdateField(Class<?> clazz, Field[] fields, Object object) throws Exception
    {
        try {

            Scanner scanner = new Scanner(System.in);
            System.err.println("Enter name of the field for changing: ");
            System.out.print("-> ");
            String fieldName = scanner.nextLine();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            System.err.println("Enter " + field.getType().getSimpleName() + " value:");
            System.out.print("-> ");
            String input = scanner.nextLine();
            Object value = convert(input, field.getType());
            field.set(object, value);
            System.err.println("Object updated: " + object);
            System.out.println("---------------------");
//            scanner.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void methodCall(Class<?> clazz, Method[] methods, Object object) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.err.println("Enter name of the method for call: ");
        System.out.print("-> ");
        String methodName = scanner.nextLine();
        String input;
        // removing () from method name
        methodName = methodName.substring(0, methodName.indexOf("("));
        Method method = clazz.getDeclaredMethod(methodName, getTypes(methods, methodName));
        method.setAccessible(true);
        Object[] parObject = new Object[method.getParameterCount()];
        for (int i = 0; i < parObject.length; i++) {
            System.out.println("Enter " + method.getParameterTypes()[i].getSimpleName() + " value:");
            System.out.print("-> ");
            input = scanner.nextLine();
            parObject[i] = convert(input, method.getParameterTypes()[i]);
        }
        Object result = method.invoke(object, parObject);
        if (!method.getReturnType().equals(Void.TYPE)) {
            System.out.println("Method returned:");
            System.out.println(result);
        }
        else
            System.out.println("New object is " + object);
//        scanner.close();
    }


    public static void main(String[] args) {

        Enchanter enchanter = new Enchanter();
        Fighter fighter = new Fighter();

        Enchanter enchanter1 = new Enchanter("Test", 32);
        Fighter fighter1 = new Fighter("Test", 32);
        Scanner scanner = new Scanner(System.in);

        enchanter.heal(fighter);
        enchanter1.heal(fighter1);
        System.out.println(fighter1);
        System.out.println(fighter);
        fighter1.attack(fighter);
        System.out.println(fighter);
        enchanter.heal(fighter);
        System.out.println(fighter);
        try {
            System.out.println("Classes: ");
          HashMap<String, Class<?>> classes =  findAllClassesUsingReflectionsLibrary("classes");
            for (Map.Entry<String, Class<?>> entry : classes.entrySet()) {
//                System.out.println(entry.toString().substring( entry.toString().lastIndexOf(".") + 1));
                System.out.println(entry.getKey());
            }
            System.out.println("Enter class name: ");
            String className = scanner.nextLine();
            Class<?> clazz = classes.get(className);
            displayClassInfo(clazz);
//            Constructor<?>[] constructors = clazz.getConstructors();
//            for (Constructor<?> constructor : constructors) {
//                System.out.println(constructor.getDeclaringClass().getSimpleName());
//                System.out.println(constructor.getParameterTypes().length);
//            }





        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
