/**
 * @author Amit Halbreich, 208917393
 * Renderer interface.
 * An interface that represents a general renderer type
 * the renderers that implements this interface, have to implement a unique renderBoard function.
 */
public interface Renderer {
    /**
     * Each class that implements Renderer interface should implement renderBoard function.
     * Each renderer type will determine and implement his own printing strategy.
     *
     * @param board Board to print.
     */
    void renderBoard(Board board);
}