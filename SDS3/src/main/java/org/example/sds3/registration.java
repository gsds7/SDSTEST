package org.example.sds3;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registration {

    @FXML
    private Button M;
    @FXML
    private Button N; // Это кнопка "Ж"
    @FXML
    private Button delet; // Кнопка "Отмена"
    @FXML
    private TextField famili;
    @FXML
    private TextField login;
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Button register;


    private String gender = "";
    DBconnector dbConnector = new DBconnector();

    @FXML
    void initialize() {

        M.setOnAction(event -> {
            gender = "Мужской";
            System.out.println("Выбран пол: " + gender);
        });


        N.setOnAction(event -> {
            gender = "Женский";
            System.out.println("Выбран пол: " + gender);
        });


        register.setOnAction(event -> {
            String log = login.getText().trim();
            String pass = password.getText().trim();
            String n = name.getText().trim();
            String fam = famili.getText().trim();


            if (!log.isEmpty() && !pass.isEmpty() && !n.isEmpty() && !fam.isEmpty() && !gender.isEmpty()) {

                // Записываем в базу данных
                if (dbConnector.RegUser(log, pass, gender, n, fam)) {
                    // Если успешно - возвращаемся на окно логина
                    openScene("hello-view.fxml");
                } else {
                    showAlert("Ошибка", "Вы ввели неправильные данные или такой логин уже существует!");
                }
            } else {
                showAlert("Внимание", "Заполните все поля и выберите пол!");
            }
        });


        delet.setOnAction(event -> {
            openScene("hello-view.fxml");
        });
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void openScene(String fxml) {
        register.getScene().getWindow().hide();
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