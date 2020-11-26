package org.openjfx.inputvalidation;

import javafx.scene.control.TextField;
import org.openjfx.RiskViewController;
import org.openjfx.model.Risk;

public class InputValidation {

    public static boolean checkFields(RiskViewController riskViewController) {
        String alertMessage = "";
        int throwAlarm = 0;
        //filled name, but empty fields for cost and probability
        if (isEmpty(riskViewController.getRiskCostField()) &&
                isEmpty(riskViewController.getRiskProbabilityField())) {

            if (!isOnlyLetters(riskViewController.getRiskNameField())) {
                alertMessage += "Risk name field can only contain letters\n";
                throwAlarm = 1;
            }
            if (!nameIsUnique(riskViewController)) {
                alertMessage += "Risk name isn't unique\n";
                throwAlarm = 1;
            }

        //all fields filled
        } else {

            if (!nameIsUnique(riskViewController)) {
                alertMessage += "Risk name isn't unique\n";
                throwAlarm = 1;
            }

            if (!isOnlyLetters(riskViewController.getRiskNameField())) {
                alertMessage += "Risk name field can only contain letters\n";
                throwAlarm = 1;
            }


            if (!isDouble(riskViewController.getRiskCostField())) {
                alertMessage += "Risk cost field can only contain numbers\n";
                throwAlarm = 1;
            }

            if (!isDouble(riskViewController.getRiskProbabilityField())) {
                alertMessage += "Probability field can only contain numbers\n";
                throwAlarm = 1;
            }



        }
        // checks if alarm needs to pop up
        if (throwAlarm == 1) {
            riskViewController.getAlert().setHeaderText("Error with input(s)..");
            riskViewController.getAlert().setContentText(alertMessage);
            riskViewController.getAlert().show();
        }
        return throwAlarm != 1;
    }

    private static boolean nameIsUnique(RiskViewController controller) {
        for (Risk r:controller.getTableView().getItems()
        ) {
            if (r.getName().equals(controller.getRiskNameField().getText())) {
                return false;
            }
        }
        return true;
    }
    public static boolean isEmpty(TextField input) {return input.getText().matches("");
    }
    public static boolean isOnlyLetters(TextField input) {
        return input.getText().matches("[a-zA-Z]+");
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
