
package crud.system;
import javax.swing.*;

public class LoginForm extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;

    public LoginForm() {

        setTitle("Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 150, 30);
        add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 30);
        add(passwordField);

        // Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(120, 120, 100, 30);
        add(loginBtn);

        // Action
        loginBtn.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (LoginDAO.login(username, password)) {

                JOptionPane.showMessageDialog(null, "Login Success ✔");

                new StudentForm(); // فتح البرنامج
                dispose(); // قفل شاشة اللوجين

            } else {
                JOptionPane.showMessageDialog(null, "Wrong Data ❌");
            }
        });

        setVisible(true);
    }
}