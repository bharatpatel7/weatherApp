import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class weatherAppGUI extends JFrame {

    private JSONObject weatherData;

    public weatherAppGUI() {

        super("Weather App");

        // config
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 500);

        setLocationRelativeTo(null);

        setLayout(null);

        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {

        JTextArea searchTextField = new JTextArea();

        searchTextField.setBounds(10,15,351,45);

        searchTextField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchTextField);

        JLabel weatherConditionImage = new JLabel(loadImage("assest/icons8-clouds-96.png"));
        weatherConditionImage.setBounds(0, 125, 450, 217);
        add(weatherConditionImage);


        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0, 350, 450, 54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));

        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        JLabel weatherConditionDesc = new JLabel("Cloudy");
        weatherConditionDesc.setBounds(0, 405, 450, 36);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 32));
        weatherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherConditionDesc);

        //Humidity
        JLabel humidityImage = new JLabel(loadImage("assest/icons8-humidity-100.png"));
        humidityImage.setBounds(15, 500, 74, 66);
        add(humidityImage);

        //Humidity Text
        JLabel humidityText = new JLabel("<html><b>Humidity</b> 100% </html>");
        humidityText.setBounds(90, 500, 85, 55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        //windspeed image
        JLabel windspeedImage = new JLabel(loadImage("assest/icons8-wind-64.png"));
        windspeedImage.setBounds(220, 500, 74, 66);
        add(windspeedImage);

        //windspeed Text
        JLabel windspeedText = new JLabel("<html><b>Windspeed</b> 100% </html>");
        windspeedText.setBounds(310, 500, 85, 55);
        windspeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windspeedText);


        JButton searchButton = new JButton(loadImage("assest/icons8-search-80.png"));

        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375,13,47,45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTextField.getText();

                if(userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                weatherData = weatherApp.getWeather(userInput);

                String weatherCondition = (String) weatherData.get("weather_condition");

                switch(weatherCondition) {
                    case "Clear":
                        weatherConditionImage.setIcon(loadImage("assest/icons8-sun-240.png"));
                        break;
                    case "Cloudy":
                        weatherConditionImage.setIcon(loadImage("assest/icons8-clouds-96.png"));
                        break;
                    case "Rain":
                        weatherConditionImage.setIcon(loadImage("assest/icons8-rain-64.png"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("assest/icons8-snow-100.png"));
                        break;
                }

                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                weatherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Humidity</b>" + humidity + "%</html>");

                double Windspeed = (double) weatherData.get("Windspeed");
                windspeedText.setText("<html><b>Windspeed</b>" + Windspeed + "km/h</html>");

            }
        });
        add(searchButton);





    }


    private ImageIcon loadImage(String resourcePath) {

        try {
            BufferedImage image = ImageIO.read(new File(resourcePath));

            return new ImageIcon(image);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
