package controller.server;

import java.io.FileNotFoundException;

class DeleteUser implements Runnable {
    ActionController actionController;

    DeleteUser(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void run() {
        try {
            actionController.loadDataWithIndex();
            actionController.deleteUser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
