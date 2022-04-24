package controller.server;

import java.io.FileNotFoundException;

class UpdateUser implements Runnable {
    ActionController actionController;

    UpdateUser(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void run() {
        try {
            actionController.updateUser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
