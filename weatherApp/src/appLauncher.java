import javax.swing.*;

public class appLauncher {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                //System.out.println(weatherApp.getLocationData("Tokyo"));

             //System.out.println(weatherApp.getCurrentTime());
                new weatherAppGUI().setVisible(true);
            }
        });
    }
}
