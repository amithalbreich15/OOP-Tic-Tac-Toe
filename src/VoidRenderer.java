/**
 * @author Amit Halbreich, 208917393
 * a voidRenderer - Renderer type.
 * this render type prints nothing to the console.
 * Good for running long tournaments and get results faster without printing.
 */
public class VoidRenderer implements Renderer {

    /**
     * prints nothing to the console.
     * @param board represents the board to print.
     */
    @Override
    public void renderBoard(Board board) {
    }

}