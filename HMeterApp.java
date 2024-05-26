package meter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HMeterApp extends JFrame {
    private HMeter meter1;
    private HMeter meter2;
    private HMeter meter3;

    public HMeterApp() {
        super("HMeter App");
        setLocation(300,150);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        meter1 = new HMeter(50, "Meter 1");
        meter1.setProgressColor(Color.GREEN);
        meter1.setHollow(true);
        meter1.setBoundaryColor(Color.GRAY);
        meter1.setRadius(100);
        add(meter1);

        meter2 = new HMeter(75, "Meter 2");
        meter2.setProgressColor(Color.BLUE);
        meter2.setHollow(false);
        meter2.setBoundaryColor(Color.RED);
        meter2.setRadius(120);
        add(meter2);

        meter3 = new HMeter(25, "Meter 3");
        meter3.setProgressColor(Color.YELLOW);
        meter3.setHollow(true);
        meter3.setBoundaryColor(Color.BLACK);
        meter3.setRadius(90);
        add(meter3);

        JButton animateButton = new JButton("Animate");
        animateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meter1.setAnimate(true);
                meter2.setAnimate(true);
                meter3.setAnimate(true);
            }
        });

//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (!meter1.isAnimate()) {
//                meter1.setAnimate(true);
//                meter2.setAnimate(true);
//                meter3.setAnimate(true);
//            }
//        }
        add(animateButton);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new HMeterApp();
    }
}
