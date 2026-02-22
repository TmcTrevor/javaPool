package fr._42.printer.logic;


public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String process(String message) {
        return message.toLowerCase();
    }
}