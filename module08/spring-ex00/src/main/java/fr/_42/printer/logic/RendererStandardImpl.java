package fr._42.printer.logic;

public class RendererStandardImpl implements Renderer {
    private PreProcessor preProcessor;

    // Constructor injection - Spring will call this
    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String message) {
        // Process message first, then render
        String processed = preProcessor.process(message);
        System.out.println(processed);
    }
}
