package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;
import org.openjfx.persistence.LoaderSaver;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void switchToTableView() throws IOException {
        App.setRoot("fxml_tableview");
    }
}
