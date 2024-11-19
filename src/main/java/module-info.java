module com.example.notepad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.notepad to javafx.fxml;
    exports com.example.notepad;
}