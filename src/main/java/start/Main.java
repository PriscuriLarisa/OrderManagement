package start;

import presentation.controller.MainController;
import presentation.view.MainView;

public class Main {
        public static void main (String[] args){

                MainView mainView = new MainView("Menu");
                MainController controller = new MainController(mainView);


        }
}
