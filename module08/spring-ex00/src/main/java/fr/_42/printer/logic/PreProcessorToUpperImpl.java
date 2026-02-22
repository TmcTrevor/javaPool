package fr._42.printer.logic;

public class PreProcessorToUpperImpl implements PreProcessor {

    @Override
    public String process(String message) {
        return message.toUpperCase();
    }
}