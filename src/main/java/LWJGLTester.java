import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;

import java.util.function.Supplier;

import static org.lwjgl.Sys.getTime;


public class LWJGLTester {



    public static void main(String[] args) throws Exception {
        System.setProperty("java.library.path", System.getProperty("user.dir") + "\\libs");

        FPSUpdater fps = new FPSUpdater();
//        CanvasInterface c = new LW2_AWT_Canvas(fps);
        CanvasInterface c = new LW2_AWT_Canvas_Buttons(fps);

        c.setRenderable(new LW2_Gears());
        c.renderLoop();
    }
}