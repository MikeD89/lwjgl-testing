import org.lwjgl.LWJGLException;

public interface CanvasInterface {
    void setRenderable(Renderable renderable);
    void renderLoop(String[] args) throws LWJGLException;
}
