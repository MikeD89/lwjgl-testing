import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

import static org.lwjgl.Sys.getTime;
import static org.lwjgl.opengl.GL11.*;

public class LW2_AWT_Canvas implements CanvasInterface {

    private static boolean closeRequested = false;
    private final static AtomicReference<Dimension> newCanvasSize = new AtomicReference<Dimension>();
    private final FPSUpdater fps;
    private Color clearColour = Color.CYAN;
    private AtomicReference<Renderable> renderable = new AtomicReference<>(null);

    Frame frame = new Frame();

    @Override
    public void setRenderable(Renderable r) {
        renderable.set(r);
    }

    public LW2_AWT_Canvas(FPSUpdater fps) throws LWJGLException {
        this.fps = fps;

        frame.setLayout(new BorderLayout());
        final Canvas canvas = new Canvas();

        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                newCanvasSize.set(canvas.getSize());
            }
        });

        frame.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                canvas.requestFocusInWindow();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeRequested = true;
            }
        });

        frame.add(canvas, BorderLayout.CENTER);

        try {
            Display.setParent(canvas);
            Display.setVSyncEnabled(false);

            frame.setPreferredSize(new Dimension(1024, 786));
            frame.setMinimumSize(new Dimension(800, 600));
            frame.pack();
            frame.setVisible(true);
            Display.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void renderLoop() {

        // Init
        glClearColor(clearColour.getRed() / 255.0f, clearColour.getGreen() / 255.0f, clearColour.getBlue() / 255.0f, 1.0f);

        while (!Display.isCloseRequested() && !closeRequested) {
            Renderable r = renderable.get();
            Dimension newDim = newCanvasSize.getAndSet(null);

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Check size
            if (newDim != null) {
                glViewport(0, 0, newDim.width, newDim.height);

                // Projection
                glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glOrtho(0.0f, (float) newDim.width, 0.0f, (float) newDim.height, -1000, 10000);
            }

            // Render a component
            if(r != null) {
                r.render();
            }

            // Update FPS
            if(fps.updateFPS()) {
               frame.setTitle(fps.getTitle());
            };

            Display.update();

        }

        Display.destroy();
        System.exit(0);
    }


}