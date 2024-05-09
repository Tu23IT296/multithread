package Multithread;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Clock {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Đồng hồ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(timeLabel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JLabel zoneLabel = new JLabel();
        JTextField zoneField = new JTextField(10);
        JButton setButton = new JButton("OPEN");
        panel.add(zoneLabel);
        panel.add(zoneField);
        panel.add(setButton);
        frame.add(panel, BorderLayout.SOUTH);

        setButton.addActionListener(e -> {
            String zoneId = zoneField.getText().trim();
            TimeZone timeZone = TimeZone.getTimeZone(zoneId);
            if (timeZone.getID().equals("GMT")) {
                JOptionPane.showMessageDialog(frame, "Múi giờ không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                dateFormat.setTimeZone(timeZone);
                String time = dateFormat.format(new Date());
                timeLabel.setText(time);
            }
        });

        Timer timer = new Timer(1000, e -> {
            String zoneId = zoneField.getText().trim();
            TimeZone timeZone = TimeZone.getTimeZone(zoneId);
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(timeZone);
            String time = dateFormat.format(new Date());
            timeLabel.setText(time);
        });
        timer.start();

        frame.setVisible(true);
    }
}