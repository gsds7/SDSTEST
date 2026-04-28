
package org.example.sds3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button leave;

    @FXML
    void initialize() {
        assert leave != null : "fx:id=\"leave\" was not injected: check your FXML file 'Main.fxml'.";


        leave.setOnAction(event -> {
            Stage stage = (Stage) leave.getScene().getWindow();
            stage.setIconified(true);
        });
    }
}