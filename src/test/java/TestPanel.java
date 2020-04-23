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

public class TestPanel extends ApplicationTest {


    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Override
    public void start(Stage stage) throws IOException {
        Pane a = FXMLLoader.load(getClass().getResource("gears.fxml"));

        final Scene scene = new Scene(a);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("gears.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }


    @Test
    public void when_button_is_clicked_text_changes() {
        // Get:
        TextField field = lookup("#timesClicked").query();

        // when:
        for(int i = 0; i < 10; i++) {
            clickOn("#clickButton");
        }

        // then:
        Assertions.assertThat(field).hasText("10");
    }
}