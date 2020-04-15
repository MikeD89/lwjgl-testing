/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package lwjglfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.eclipse.fx.drift.DriftFXSurface;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class HelloDriftFX extends Application {

    private final AtomicBoolean aliveFlag = new AtomicBoolean(true);
    private final CountDownLatch exitLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        DriftFXSurface.initialize();

        BorderPane root = new BorderPane();

        DriftFXSurface surface = new DriftFXSurface();
        root.setCenter(surface);

        Scene scene = new Scene(root);
        scene.setFill(Color.BLUE);
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ESCAPE),
                () -> primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST))
        );

        primaryStage.setScene(scene);

        primaryStage.setWidth(300);
        primaryStage.setHeight(300);
        primaryStage.show();
        primaryStage.setTitle("DriftFX Gears");
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
            // notify render thread that we're exiting
            aliveFlag.set(false);
            try {
                // block until render thread has cleaned up
                exitLatch.await();
            } catch (InterruptedException ignored) {
            }
        });

        new DriftFXRenderThread(aliveFlag, exitLatch, surface.getNativeSurfaceHandle()).start();
    }



}