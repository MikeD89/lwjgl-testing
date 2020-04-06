import org.lwjgl.LWJGLException;

public interface CanvasInterface {
    void setRenderable(Renderable renderable);
    void renderLoop() throws LWJGLException;
}
