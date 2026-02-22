package fr._42.printer.app;

import fr._42.printer.logic.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Load Spring context from XML file
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

        // Get bean by name and type
        Printer printer = context.getBean("printerWithPrefix", Printer.class);

        // Use the printer
        printer.print("Hello!");

        System.out.println("---");

        // Get another printer
        Printer dateTimePrinter = context.getBean("printerWithDateTime", Printer.class);
        dateTimePrinter.print("Hello!");
    }
}