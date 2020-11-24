package org.openjfx.inputvalidation;

import javafx.scene.control.TextField;
import org.openjfx.RiskViewController;

public class InputValidation {
    public static boolean isEmail(TextField input, String message) {
        if(input.getText().matches("^(.+)@(.+)$")) {
            return true;
        } else return false;
    }

    public static boolean isOnlyLetters(TextField input) {
        if (input.getText().matches("[a-zA-Z]+")) {
            return true;
        }
        return false;
    }

    public static boolean isDouble(TextField input) {
        try {
            Double.parseDouble(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
