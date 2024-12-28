import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomColorGeneratorSwing {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Random Color Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("Generate Random Color");
        buttonPanel.add(button);

        // Create the label
        JLabel label = new JLabel("Color Code: #FFFFFF", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size

        // Add action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate random RGB values
                Random random = new Random();
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);

                // Create the new color using the RGB values
                Color newColor = new Color(red, green, blue);

                // Display the RGB value as hex code in the label
                String hexCode = String.format("#%02X%02X%02X", red, green, blue);
                label.setText("Color Code: " + hexCode);

                // Animate the color change
                animateColorChange(mainPanel, mainPanel.getBackground(), newColor);
            }
        });

        // Add the button panel and label to the main panel
        mainPanel.add(buttonPanel, BorderLayout.NORTH);  // Button is at the top
        mainPanel.add(label, BorderLayout.CENTER);       // Label is in the center

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Make the frame visible
        frame.setVisible(true);
    }

    private static void animateColorChange(JPanel panel, Color startColor, Color endColor) {
        Timer timer = new Timer(10, null);
        final int[] step = {0};
        final int steps = 100;

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float ratio = (float) step[0] / steps;
                int red = (int) (startColor.getRed() * (1 - ratio) + endColor.getRed() * ratio);
                int green = (int) (startColor.getGreen() * (1 - ratio) + endColor.getGreen() * ratio);
                int blue = (int) (startColor.getBlue() * (1 - ratio) + endColor.getBlue() * ratio);
                Color interpolatedColor = new Color(red, green, blue);
                panel.setBackground(interpolatedColor);
                panel.repaint();

                step[0]++;
                if (step[0] > steps) {
                    timer.stop();
                }
            }
        });

        timer.start();
    }
}