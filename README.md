# HMeter - Customizable Horizontal Progress Meter Widget

HMeter is a highly customizable Java Swing widget designed to display horizontal progress visually. It provides developers with a simple yet versatile tool to showcase progress in graphical form. With HMeter, you can easily integrate progress meters into your Java Swing applications, allowing for a more intuitive user experience.

## Features

- **Customizable Progress**: Set the progress value dynamically from 0 to 100.
- **Color Customization**: Customize the colors of the progress arc, text, background, and boundary.
- **Text Display**: Display custom text within the progress meter.
- **Hollow or Filled Arc**: Choose between a hollow or filled arc for the progress indicator.
- **Animation**: Optionally animate the progress to provide a more engaging user experience.
- **Mouse Interaction**: Enable interactive control of the progress through mouse dragging.
- **Responsive Design**: Ensures a minimum height for the meter to maintain proper visibility.
- **Set Background**: Ensure a consistent design and personalization.

## Getting Started

To integrate HMeter into your Java Swing application, follow these steps:

1. Download the `HMeter.java` file.
2. Include `HMeter.java` in your project's source directory.
3. Instantiate an `HMeter` object and add it to your Swing component hierarchy.
4. Customize the meter's appearance and behavior using the provided setter methods.
5. Monitor and update the progress as needed in your application logic.

## ScreenShot

![Screenshot (1135)](https://github.com/Hasnatrasool163/H-Meter/assets/153990457/9f8f88fd-7cb1-4e0c-af28-3960bbe58da2)

![Screenshot (1140)](https://github.com/Hasnatrasool163/H-Meter/assets/153990457/090a2fb4-64cc-4860-bbd7-7c14610d8f7b)



## Usage

Here's a simple example demonstrating how to use HMeter:

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("HMeter Demo");

        // Create an HMeter with initial progress and text
        HMeter hMeter = new HMeter(50, "Processing...");

        // Customize HMeter appearance
        hMeter.setProgressColor(Color.BLUE);
        hMeter.setTextColor(Color.WHITE);
        hMeter.setBackgroundColor(Color.BLACK);

        // Add HMeter to the frame
        getContentPane().add(hMeter);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

## Contributions

Contributions to HMeter are welcome! If you find any issues or have suggestions for improvements, please feel free to submit a pull request or open an issue on GitHub.

## License

HMeter is licensed under the MIT License. See the [LICENSE](https://github.com/your-username/your-repository/blob/master/LICENSE) file for details.

---

Enjoy using HMeter in your Java Swing projects and let us know how it enhances your user interfaces!
