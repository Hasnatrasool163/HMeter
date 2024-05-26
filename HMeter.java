package meter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;

/**
 * A highly customizable horizontal progress meter widget.
 */
public class HMeter extends JPanel {
    // Fields
    private int progress = 0; // Current progress value
    private Color progressColor = Color.GREEN; // Color of the progress arc
    private Color textColor = Color.BLACK; // Color of the text
    private Color backgroundColor = getBackgroundColor(); // Background color of the meter
    private int padding = 10; // Padding around the meter
    private String text = ""; // Text displayed in the meter
    private boolean isHollow = true; // Whether the progress arc is hollow or filled
    private Color boundaryColor = Color.GRAY; // Color of the boundary circle
    private int radius = 100; // Radius of the meter
    private boolean animate = false; // Whether the progress should be animated
    private int animationSpeed = 50; // Speed of the animation
    private boolean enabled = true; // Whether the meter is enabled or not

    /**
     * Constructs a new HMeter with default progress and text.
     */
    public HMeter() {
        this(0, "");
    }

    /**
     * Constructs a new HMeter with specified progress and default text.
     *
     * @param progress Initial progress value (0-100)
     */
    public HMeter(int progress) {
        this(progress, "");
    }

    /**
     * Constructs a new HMeter with specified progress and text.
     *
     * @param progress Initial progress value (0-100)
     * @param text     Text to be displayed in the meter
     */
    public HMeter(int progress, String text) {
        setPreferredSize(new Dimension(200, 200));
        setBackground(backgroundColor);
        setProgress(progress);
        setText(text);
    }

    // Setter and Getter methods

    /**
     * Sets the progress value of the meter.
     *
     * @param progress The progress value (0-100)
     */
    public void setProgress(int progress) {
        if (progress > -500 && progress < 501) {
            this.progress = progress;
        } else {
            progress = 0;
            this.progress = progress;
        }
        repaint();
    }

    /**
     * Sets the color of the progress arc.
     *
     * @param progressColor The color of the progress arc
     */
    public void setProgressColor(Color progressColor) {
        this.progressColor = progressColor;
        repaint();
    }

    /**
     * Sets the color of the text.
     *
     * @param textColor The color of the text
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        repaint();
    }

    /**
     * Sets the background color of the meter.
     *
     * @param backgroundColor The background color of the meter
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    /**
     * Sets the padding around the meter.
     *
     * @param padding The padding around the meter
     */
    public void setPadding(int padding) {
        this.padding = padding;
        repaint();
    }

    /**
     * Sets the text to be displayed in the meter.
     *
     * @param text The text to be displayed
     */
    public void setText(String text) {
        if (text.length() < 12) {
            this.text = text;
        }
        repaint();
    }

    /**
     * Sets whether the progress arc is hollow or filled.
     *
     * @param isHollow True for a hollow arc, false for a filled arc
     */
    public void setHollow(boolean isHollow) {
        this.isHollow = isHollow;
        repaint();
    }

    /**
     * Sets the color of the boundary circle.
     *
     * @param boundaryColor The color of the boundary circle
     */
    public void setBoundaryColor(Color boundaryColor) {
        this.boundaryColor = boundaryColor;
        repaint();
    }

    /**
     * Sets the radius of the meter.
     *
     * @param radius The radius of the meter
     */
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    /**
     * Sets whether the progress should be animated.
     *
     * @param animate True to enable animation, false otherwise
     */
    public void setAnimate(boolean animate) {
        this.animate = animate;
        if (animate) {
            new Thread(new AnimationThread()).start();
        }
    }

    /**
     * Gets the current progress value of the meter.
     *
     * @return The current progress value
     */
    public int getProgress() {
        return progress;
    }

    /**
     * Gets the color of the progress arc.
     *
     * @return The color of the progress arc
     */
    public Color getProgressColor() {
        return progressColor;
    }

    /**
     * Gets the color of the text.
     *
     * @return The color of the text
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Gets the background color of the meter.
     *
     * @return The background color of the meter
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets the padding around the meter.
     *
     * @return The padding around the meter
     */
    public int getPadding() {
        return padding;
    }

    /**
     * Gets the text displayed in the meter.
     *
     * @return The text displayed in the meter
     */
    public String getText() {
        return text;
    }

    /**
     * Checks if the progress arc is hollow or filled.
     *
     * @return True if the arc is hollow, false otherwise
     */
    public boolean isHollow() {
        return isHollow;
    }

    /**
     * Gets the color of the boundary circle.
     *
     * @return The color of the boundary circle
     */
    public Color getBoundaryColor() {
        return boundaryColor;
    }

    /**
     * Gets the radius of the meter.
     *
     * @return The radius of the meter
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets whether the meter is enabled or not.
     *
     * @param enable True to enable the meter, false to disable
     */
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

    /**
     * Adds a mouse motion listener to the meter.
     *
     * @param listener The mouse motion listener to be added
     */
    public void addMouseMotionListener(MouseMotionAdapter listener) {
        super.addMouseMotionListener(listener);
    }

    /**
     * Checks if the meter is enabled.
     *
     * @return True if the meter is enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the bounds of the meter, ensuring a minimum height.
     *
     * @param x      The x-coordinate of the upper-left corner
     * @param y      The y-coordinate of the upper-left corner
     * @param width  The width of the meter
     * @param height The height of the meter
     */
    public void setBounds(int x, int y, int width, int height) {
        if (height < 150) {
            height = 150;
        }
        super.setBounds(x, y, width, height);
    }

    /**
     * Custom painting method for drawing the meter components.
     *
     * @param g The graphics context
     */
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

    // Inner class for handling animation
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
}
