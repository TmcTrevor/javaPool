package fr._42.printer.logic;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix;

    // Constructor injection for Renderer
    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    // Setter injection for prefix (optional property)
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        // Add prefix to message
        String fullMessage = prefix != null ? prefix + " " + message : message;
        renderer.render(fullMessage);
    }
}