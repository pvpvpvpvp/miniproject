package controller.server;

import java.io.FileNotFoundException;

class UpdateSet implements Runnable {
    ActionController actionController;

    UpdateSet(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void run() {
        try {
            actionController.loadDataWithIndex();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        actionController.updateAuthCheck();
    }
}
