package org.openjfx.inputvalidation;

import javafx.scene.control.TextField;

public class InputValidation {
    public static boolean isEmail(TextField input, String message) {
        if(input.getText().matches("^(.+)@(.+)$")) {
            return true;
        } else return false;
    }

    public static boolean isInt(TextField input, String message) {
        try {
            Integer.parseInt(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(TextField input, String message) {
        try {
            Double.parseDouble(input.getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
