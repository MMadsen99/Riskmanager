package org.openjfx;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class PrimaryController {


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void switchToTableView() throws IOException {
        App.loadProject();
        App.setRoot("fxml_tableview");
    }
}
