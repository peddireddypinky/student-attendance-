import java.awt.*;
import javax.swing.*;

public class LoginUI {

    JFrame frame;

    public LoginUI() {

        frame = new JFrame("Student Attendance Login");
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main background panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(new Color(230,240,255));
        bgPanel.setLayout(new GridBagLayout());

        // Login card panel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350,250));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));

        // Title
        JLabel title = new JLabel("Student Attendance Login", JLabel.CENTER);
        title.setBounds(20,20,300,30);
        title.setFont(new Font("Segoe UI",Font.BOLD,18));

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(40,80,100,25);

        JTextField userField = new JTextField();
        userField.setBounds(140,80,150,25);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(40,120,100,25);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(140,120,150,25);

        // Login button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(110,170,120,35);
        loginBtn.setBackground(new Color(0,102,204));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);

        // Add components
        panel.add(title);
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginBtn);

        bgPanel.add(panel);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        bgPanel.add(footer, new GridBagConstraints()); // Simple way to add it to bottom if possible, or another way

        frame.add(bgPanel);
        
        JLabel frameFooter = new JLabel("Developed by Pinky", JLabel.CENTER);
        frame.add(frameFooter, BorderLayout.SOUTH);

        frame.setVisible(true);

        // Login Action
        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            if(username.equals("admin") && password.equals("1234")){

                JOptionPane.showMessageDialog(frame,"Login Successful");

                frame.dispose();
                new DashboardUI();

            }else{

                JOptionPane.showMessageDialog(frame,"Invalid Username or Password");

            }

        });
    }

    public static void main(String[] args) {
        new LoginUI();
    }
}