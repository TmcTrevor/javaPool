package fr._42.printer.logic;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String message) {
        String processed = preProcessor.process(message);
        System.err.println(processed);
    }
}
