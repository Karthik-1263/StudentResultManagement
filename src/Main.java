import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import gui.AdminLoginScreen;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AdminLoginScreen(); // Start with Admin Login
    }
}
