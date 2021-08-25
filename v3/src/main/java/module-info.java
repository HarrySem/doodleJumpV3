module doodlejump {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.graphics;
    opens doodlejump to javafx.fxml;
    opens doodlejump.Boundary to javafx.fxml;
    exports doodlejump;
}