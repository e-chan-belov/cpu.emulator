module org.example.lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.desktop;

    opens org.example.lab4 to javafx.fxml;
    exports org.example.lab4;
}