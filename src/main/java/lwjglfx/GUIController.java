package lwjglfx;/*
 * Copyright (c) 2002-2012 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import org.eclipse.fx.drift.DriftFXSurface;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.beans.binding.Bindings.createStringBinding;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import static org.lwjgl.opengl.GL11.*;

/**
 * The JavaFX application GUI controller.
 */
public class GUIController implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TextField websiteText;

    @FXML
    private BorderPane glPanel;

    private final AtomicBoolean aliveFlag = new AtomicBoolean(true);
    private final CountDownLatch exitLatch = new CountDownLatch(1);

    public GUIController() {
    }

    public void selectWebsite(){
        System.out.println(websiteText.getCharacters());
        webView.getEngine().load(websiteText.getCharacters().toString());

    }

    public void initialize(final URL url, final ResourceBundle resourceBundle) {


//        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
//            // notify render thread that we're exiting
//            aliveFlag.set(false);
//            try {
//                // block until render thread has cleaned up
//                exitLatch.await();
//            } catch (InterruptedException ignored) {
//            }
//        });

//        DriftFXSurface.initialize();
//        DriftFXSurface surface = new DriftFXSurface();
//        new DriftFXRenderThread(aliveFlag, exitLatch, surface.getNativeSurfaceHandle()).start();

    }
/**


    // This method will run in the background rendering thread
    void runGears(final CountDownLatch runningLatch) {
        try {
            gears = new Gears(getReadHandler());
        } catch (Throwable t) {
            t.printStackTrace();
            return;
        }


        final String vendor = glGetString(GL_VENDOR);
        final String version = glGetString(GL_VERSION);

        Platform.runLater(new Runnable() {
            public void run() {
                // Listen for FPS changes and update the fps label
                final ReadOnlyIntegerProperty fps = gears.fpsProperty();

                fpsLabel.textProperty().bind(createStringBinding(new Callable<String>() {
                    public String call() throws Exception {
                        return "FPS: " + fps.get();
                    }
                }, fps));
                glInfoLabel.setText(vendor + " OpenGL " + version);

                renderChoice.setItems(observableList(renderStreamFactories));
                for (int i = 0; i < renderStreamFactories.size(); i++) {
                    if (renderStreamFactories.get(i) == gears.getRenderStreamFactory()) {
                        renderChoice.getSelectionModel().select(i);
                        break;
                    }
                }
                renderChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RenderStreamFactory>() {
                    public void changed(final ObservableValue<? extends RenderStreamFactory> observableValue, final RenderStreamFactory oldValue, final RenderStreamFactory newValue) {
                        gears.setRenderStreamFactory(newValue);
                    }
                });

                bufferingChoice.getSelectionModel().select(gears.getTransfersToBuffer() - 1);
                bufferingChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BufferingChoice>() {
                    public void changed(final ObservableValue<? extends BufferingChoice> observableValue, final BufferingChoice oldValue, final BufferingChoice newValue) {
                        gears.setTransfersToBuffer(newValue.getTransfersToBuffer());
                    }
                });

                vsync.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(final ObservableValue<? extends Boolean> observableValue, final Boolean oldValue, final Boolean newValue) {
                        gears.setVsync(newValue);
                    }
                });

                final int maxSamples = gears.getMaxSamples();
                if (maxSamples == 1)
                    msaaSamples.setDisable(true);
                else {
                    msaaSamples.setMax(Integer.numberOfTrailingZeros(maxSamples));
                    msaaSamples.valueProperty().addListener(new ChangeListener<Number>() {
                        public void changed(final ObservableValue<? extends Number> observableValue, final Number oldValue, final Number newValue) {
                            gears.setSamples(1 << newValue.intValue());
                        }
                    });
                }

                webView.getEngine().load("http://www.stackoverflow.com/");
            }
        });

        gears.execute(runningLatch);
    }
 **/
    private enum BufferingChoice {
        SINGLE(1, "No buffering"),
        DOUBLE(2, "Double buffering"),
        TRIPLE(3, "Triple buffering");

        private final int transfersToBuffer;
        private final String description;

        private BufferingChoice(final int transfersToBuffer, final String description) {
            this.transfersToBuffer = transfersToBuffer;
            this.description = transfersToBuffer + "x - " + description;
        }

        public int getTransfersToBuffer() {
            return transfersToBuffer;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }


}