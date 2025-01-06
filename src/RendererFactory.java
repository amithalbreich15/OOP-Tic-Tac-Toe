/**
 * @author Amit Halbreich, 208917393
 * a rendererFactory.
 * a design pattern that builds a render {console, none}
 *
 */
public class RendererFactory {
    private static final String NONE = "none";
    private static final String CONSOLE = "console";

    /**
     * buildRenderer function - a function that builds renderer type according to the given rendererType string.
     *
     * @param rendererType represents a given render type that need to be constructed.
     * @return return a render if renderType is one of (console, none), null otherwise.
     */
    Renderer buildRenderer(String rendererType, int size) {
        Renderer renderer = null;
        switch (rendererType) {
            case CONSOLE:
                renderer = new ConsoleRenderer(size);
                break;
            case NONE:
                renderer = new VoidRenderer();
                break;
            default:
                break;
        }
        return renderer;
    }
}

