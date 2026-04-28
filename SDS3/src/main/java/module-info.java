module org.example.sds3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.sds3 to javafx.fxml;
    exports org.example.sds3;
}