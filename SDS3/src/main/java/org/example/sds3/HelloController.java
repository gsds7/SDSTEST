
package org.example.sds3;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Button Next;
    @FXML
    private TextField login;
    @FXML
    private TextField password;

    DBconnector dBconnector = new DBconnector();

    @FXML
    void initialize() {
        if (dBconnector != null) {
            dBconnector.DBConn();
        }


        Next.setOnAction(event -> {
            String logText = login.getText().trim();
            String passText = password.getText().trim();


            if (dBconnector.TableConn(logText, passText)) {
                openNewScene("Main.fxml"); // Если всё верно - идем в Main
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка авторизации");
                alert.setHeaderText("Неверный логин или пароль.");
                alert.setContentText("Пожалуйста, проверьте введенные данные.\nХотите попробовать зарегистрироваться?");

                ButtonType registerButton = new ButtonType("Зарегистрироваться");
                ButtonType cancelButton = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(registerButton, cancelButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == registerButton) {
                    openNewScene("registration.fxml"); // Переход на регистрацию
                }
            }
        });
    }

    private void openNewScene(String fxml) {
        Next.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        try {
            loader.load();
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}