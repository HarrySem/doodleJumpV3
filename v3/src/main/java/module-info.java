module doodlejump {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens doodlejump to javafx.fxml;
    exports doodlejump;
}