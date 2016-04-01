package Lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Саня on 01.04.2016.
 */
public class ClockPanel extends JPanel {

    private Clock clock;

    public ClockPanel(Clock сlock) {
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(300, 200));

        setClock(сlock);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (clock != null) {
            drawClock(g, clock);
        }
    }

    /**
     * Метод рисует на канве круг с заданным центром, радиусом и цветом
     * @param g канва
     * @param color цвет круга
     * @param center центр круга
     * @param radius радиус круга
     */
    private void drawCircle(Graphics g, Point center, int radius) {
        g.setColor(Color.GREEN);
        g.fillOval(center.x - radius / 2, center.y - radius / 2, radius, radius);
    }

    public Point convertCoordinatesToPanel(Point point) {
        Point O = new Point(getSize().width / 2, getSize().height / 2);
        return new Point(O.x + point.x, O.y - point.y);
    }

    private Point getEndPoint(double angle, int radius) {
        Point O = new Point(getSize().width / 2, getSize().height / 2);
        int x = (int) (O.x + radius * Math.cos(angle));
        int y = (int) (O.y - radius * Math.sin(angle));
        return new Point(x, y);
    }

    private void drawClock(Graphics g, Clock сlock) {
        Point O = new Point(getSize().width / 2, getSize().height / 2);
        int radiusClock = Math.min(O.x, O.y) - 20;
        int radiusMinute = radiusClock - 10;
        int radiusHour = radiusMinute - 20;
        double angle;
        for (int i = 1; i < 13; i++) {
            angle = Math.PI / 2 - i * Math.PI / 6;
            Point point = getEndPoint(angle, radiusClock);
            drawCircle(g, point, 8);
        }
        angle = Math.PI / 2 - (clock.getHours() + clock.getMinutes() / 60.0) * Math.PI / 6;
        Point point = getEndPoint(angle, radiusHour);
        g.setColor(Color.GREEN);
        g.drawLine(O.x, O.y, point.x, point.y);

        angle = Math.PI / 2 - clock.getMinutes() * Math.PI / 30;
        point = getEndPoint(angle, radiusMinute);
        g.setColor(Color.GRAY);
        g.drawLine(O.x, O.y, point.x, point.y);
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("Часики");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        final ClockPanel clockPanel = new ClockPanel(new Clock(0, 0));
        clockPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        f.add(clockPanel, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("Настройки"));
        controlPanel.setLayout(new GridLayout(2, 2, 6, 6));

        JButton but = (JButton) controlPanel.add(new JButton("Часы +1"));
        but.putClientProperty("plus", true);
        ActionListener alHours = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                boolean plus = ((Boolean) b.getClientProperty("plus")).booleanValue();
                clockPanel.getClock().setHours(clockPanel.getClock().getHours() + (plus ? 1 : -1));
                clockPanel.repaint();
            }

        };
        but.addActionListener(alHours);

        but = (JButton) controlPanel.add(new JButton("Часы -1"));
        but.putClientProperty("plus", false);
        but.addActionListener(alHours);

        but = (JButton) controlPanel.add(new JButton("Минуты +1"));
        but.putClientProperty("plus", true);
        ActionListener alMinutes = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                boolean plus = ((Boolean) b.getClientProperty("plus")).booleanValue();
                clockPanel.getClock().setMinutes(clockPanel.getClock().getMinutes() + (plus ? 1 : -1));
                clockPanel.repaint();
            }

        };
        but.addActionListener(alMinutes);

        but = (JButton) controlPanel.add(new JButton("Минуты -1"));
        but.putClientProperty("plus", false);
        but.addActionListener(alMinutes);

        f.add(controlPanel, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }

}
