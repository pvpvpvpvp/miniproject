package controller.server;

class TextSave implements Runnable {
    ActionController actionController;

    TextSave(ActionController actionController) {
        this.actionController = actionController;
    }

    @Override
    public void run() {
        actionController.textSave();
    }
}
