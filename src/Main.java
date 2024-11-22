
import views.LoginPanel;
import controllers.DatabaseInitializer;


public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        new LoginPanel();
    }
}
