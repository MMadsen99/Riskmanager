module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    opens org.openjfx to javafx.fxml;
    opens org.openjfx.model to javafx.base;
    exports org.openjfx;
}