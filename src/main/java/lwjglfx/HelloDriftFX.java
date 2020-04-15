/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package lwjglfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.eclipse.fx.drift.DriftFXSurface;

import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HelloDriftFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Window");

        stage.setMinWidth(640);
        stage.setMinHeight(480);

        stage.getIcons().add(new Image("lwjgl_32x32.png"));

        final Screen screen = Screen.getPrimary();
        final Rectangle2D screenBounds = screen.getVisualBounds();

        if ( screenBounds.getWidth() < stage.getWidth() || screenBounds.getHeight() < stage.getHeight() ) {
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());

            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
        }

        final URL fxmlURL = getClass().getClassLoader().getResource("gears.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

        Pane content;
        try {
            content = (Pane)fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            return;
        }

        Scene scene = new Scene(content);
        scene.setFill(Color.BLACK);
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ESCAPE),
                () -> stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST))
        );

        stage.setScene(scene);
        stage.show();
    }



}