package meter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

public class HMeter extends JPanel {
    private int progress = 0;
    private Color progressColor = Color.GREEN;
    private Color textColor = Color.BLACK;
    private Color backgroundColor = getBackgroundColor();
    private int padding = 10;
    private String text = "";
    private boolean isHollow = true;
    private Color boundaryColor = Color.GRAY;
    private int radius = 100; // default radius
    private boolean animate = false;
    private int animationSpeed = 50;
    private boolean enabled = true;


    public HMeter() {
        this(0, "");
    }

    public HMeter(int progress) {
        this(progress, "");
    }

    public HMeter(int progress, String text) {
        setPreferredSize(new Dimension(200, 200));
        setBackground(backgroundColor);
        setProgress(progress);
        setText(text);
    }

    public void setProgress(int progress) {
        if(progress>-500 && progress<501){

            this.progress = progress;}
        else{
            progress=0;
            this.progress=progress;
        }
        repaint();
    }

    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
        repaint();
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public void setPadding(int padding) {
        this.padding = padding;
        repaint();
    }

    public void setText(String text) {
        if (text.length() < 12) {
            this.text = text;
        }
        repaint();
    }

    public void setHollow(boolean isHollow) {
        this.isHollow = isHollow;
        repaint();
    }

    public void setBoundaryColor(Color boundaryColor) {
        this.boundaryColor = boundaryColor;
        repaint();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public void setAnimate(boolean animate) {
        this.animate = animate;
        if (animate) {
            new Thread(new AnimationThread()).start();
        }
    }

    public int getProgress() {
        return progress;
    }

    public Color getProgressColor() {
        return progressColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public int getPadding() {
        return padding;
    }

    public String getText() {
        return text;
    }

    public boolean isHollow() {
        return isHollow;
    }

    public Color getBoundaryColor() {
        return boundaryColor;
    }

    public int getRadius() {
        return radius;
    }


    public void setEnable(boolean enable) {
        this.enabled = enable;
        if (enable) {
            addMouseMotionListener(new MouseMotionAdapter() {
                int lastX = 0;
                int direction = 0; // 0: no direction, 1: right, -1: left
                int threshold = 5; // adjust this value to set the sensitivity

                @Override
                public void mouseDragged(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();

                    if (Math.abs(x - lastX) >= threshold) {
                        if (x > lastX) { // right direction
                            direction = 1;
                        } else if (x < lastX) { // left direction
                            direction = -1;
                        }

                        int progress = getProgress();
                        progress = progress + direction;
                        if (progress >= 0 && progress <= 100) {
                            setProgress(progress);
                        } else if (progress < 0) {
                            setProgress(0);
                        } else if (progress > 100) {
                            setProgress(100);
                        }
                    }

                    lastX = x;
                }
            });
        } else {
            removeMouseMotionListener(new MouseMotionAdapter() {
                // empty implementation
            });
        }
    }

    public void addMouseMotionListener(MouseMotionAdapter listener) {
        super.addMouseMotionListener(listener);
    }

    public boolean isEnabled() {
        return enabled;
    }


    public void setBounds(int x, int y, int width, int height) {
        if (height < 150) {
            height = 150;
        }
        super.setBounds(x, y, width, height);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background circle
        Ellipse2D circle = new Ellipse2D.Double(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2);
        g2d.setColor(backgroundColor);
        g2d.fill(circle);

        // Draw the boundary circle
        g2d.setColor(boundaryColor);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawArc(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2, 0, 360);

        // Draw the progress arc
        g2d.setColor(progressColor);
        if (isHollow) {
            g2d.setStroke(new BasicStroke(10));
            g2d.drawArc(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2, 0, (int) (360 * (progress / 100.0)));
        } else {
            g2d.fillArc(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2, 0, (int) (360 * (progress / 100.0)));
        }

        // Draw the text
        g2d.setColor(textColor);
        Font font = new Font("Tahoma", Font.BOLD, 18);
        g2d.setFont(font);
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getAscent();
        g2d.drawString(text, (getWidth() - textWidth) / 2, ((getHeight() + 50) + textHeight) / 2);

        // Draw the progress percentage
        String progressText = progress + "%";
        int progressTextWidth = g2d.getFontMetrics().stringWidth(progressText);
        int progressTextHeight = g2d.getFontMetrics().getAscent();
        g2d.drawString(progressText, (getWidth() - progressTextWidth) / 2, ((getHeight() - 50) + progressTextHeight) / 2);
    }

    private class AnimationThread implements Runnable {
        @Override
        public void run() {
            while (animate) {
                progress++;
                if (progress > 100) {
                    progress = 0;
                }
                repaint();
                try {
                    Thread.sleep(animationSpeed);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circular Progress Meter");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HMeter meter = new HMeter(0, "Hasnat");
        meter.setProgressColor(Color.RED);
        meter.setTextColor(Color.BLUE);
        meter.setBounds(50,50,200,200);
        meter.setPadding(20);
        meter.setHollow(true);
        meter.setBoundaryColor(Color.LIGHT_GRAY);
        meter.setRadius(100);
        meter.setEnable(true);
        meter.setProgress(75);
//        meter.setAnimate(true);
        frame.add(meter);
        frame.pack();
        frame.setSize(500,500);
//        frame.setResizable(false);
        frame.setVisible(true);
    }
}
