package view;

import control.RiskManagerController;
import exceptions.NoProjectException;

public interface Observer {

    void Update(RiskManagerController controller) throws NoProjectException;
}
