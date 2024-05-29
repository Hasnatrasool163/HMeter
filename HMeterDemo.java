package Hasnat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


public class HMeterDemo extends JFrame {

    private HMeter meter1, meter2, meter3;
    private JProgressBar progressBar;
    private JSlider slider;

    public HMeterDemo() {

        meter1 = new HMeter(50, "HMeter 1");
        meter1.setPadding(20);
        meter1.setRadius(150);
        meter1.setMaximumValue(200);
        meter1.setProgressColor(Color.BLUE);
        meter1.setTextColor(Color.WHITE);
        meter1.setBoundaryColor(Color.GRAY);

        meter2 = new HMeter(75, "HMeter 2");
        meter2.setPadding(20);
        meter2.setRadius(150);
        meter2.setMaximumValue(300);
        meter2.setProgressColor(Color.GREEN);
        meter2.setTextColor(Color.WHITE);
        meter2.setBoundaryColor(Color.GRAY);

        meter3 = new HMeter(25, "HMeter 3");
        meter3.setPadding(20);
        meter3.setRadius(150);
        meter3.setMaximumValue(500);
        meter3.setProgressColor(Color.RED);
        meter3.setTextColor(Color.WHITE);
        meter3.setBoundaryColor(Color.GRAY);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(50);

        slider = new JSlider(0, 100, 50);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                meter1.setProgress(value*2);
                meter2.setProgress(value*3);
                meter3.setProgress(value*5);
                progressBar.setValue(value);
            }
        });

        JPanel panel = new JPanel(null);

        meter1.setBounds(50, 50, 200, 200);
        meter2.setBounds(300, 50, 200, 200);
        meter3.setBounds(550, 50, 200, 200);

        progressBar.setBounds(50, 300, 600, 30);
        slider.setBounds(50, 350, 600, 30);

        panel.add(meter1);
        panel.add(meter2);
        panel.add(meter3);
        panel.add(progressBar);
        panel.add(slider);

        add(panel);

        setSize(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HMeterDemo();
    }
}
