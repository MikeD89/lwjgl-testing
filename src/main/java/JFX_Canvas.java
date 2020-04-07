import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.w3c.dom.events.EventException;

import javax.management.RuntimeMBeanException;
import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

public class JFX_Canvas extends Application implements CanvasInterface {

    protected AtomicReference<Renderable> renderable = new AtomicReference<>(null);
    private FPSUpdater fps;

    public static AtomicReference<JFX_Canvas> canvas = new AtomicReference<>(null);

    public JFX_Canvas() {
        if(canvas.getAndSet(this) != null) {
            throw new RuntimeException("Already have a canvas object");
        }
    }


    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {


        Pane root = new Pane();

        Rectangle rect = new Rectangle(25, 25, 50, 50);
        rect.setFill(Color.CADETBLUE);

        Line line = new Line(90, 40, 230, 40);
        line.setStroke(Color.BLACK);

        Circle circle = new Circle(130, 130, 30);
        circle.setFill(Color.CHOCOLATE);

        root.getChildren().addAll(rect, line, circle);

        Scene scene = new Scene(root, 250, 220, Color.WHITESMOKE);

        stage.setTitle("Absolute layout");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void setRenderable(Renderable r) {
        renderable.set(r);
    }

    @Override
    public void renderLoop(String[] args) throws LWJGLException {

    }
}