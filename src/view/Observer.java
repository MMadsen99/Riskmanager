package view;

import control.RiskManagerController;
import exceptions.NoProjectException;

public interface Observer {

    public void Update(RiskManagerController controller) throws NoProjectException;
}
