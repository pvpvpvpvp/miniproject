package controller.server;

import java.io.FileNotFoundException;

class ListSend implements Runnable {
    ActionController actionController;

    ListSend(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void run() {
        try {
            actionController.loadDataWithIndex();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        actionController.sendData();
    }
}
