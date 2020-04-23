import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lwjglfx.GUIController;
import org.junit.Test;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class TestPanel extends ApplicationTest {

    private GUIController controller;


    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Override
    public void start(Stage stage) throws IOException {
        final URL fxmlURL = getClass().getClassLoader().getResource("gears.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

        Pane content = (Pane) fxmlLoader.load();
        controller = fxmlLoader.getController();

        final Scene scene = new Scene(content);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("gears.css").toExternalForm());

        stage.setScene(scene);
        stage.show();

    }


    @Test
    public void when_button_is_clicked_text_changes() {
        // Get:
        TextField field = lookup("#timesClicked").query();

        Assertions.assertThat(field).hasText("0");

        // when:
        for (int i = 0; i < 10; i++) {
            clickOn("#clickButton");
        }

        // then:
        Assertions.assertThat(field).hasText("10");

        controller.resetCount();

        Assertions.assertThat(field).hasText("0");

        // when:
        for (int i = 0; i < 10; i++) {
            clickOn("#clickButton");
        }

        // then:
        Assertions.assertThat(field).hasText("10");
    }
}