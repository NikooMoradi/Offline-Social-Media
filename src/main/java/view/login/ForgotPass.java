/*
 * Created by JFormDesigner on Tue Aug 02 14:27:16 IRDT 2022
 */

package view.login;

import controller.LoginController;
import model.User;
import repo.Repository;
import repo.UserRepo;
import util.Role;
import view.general.GeneralView;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

/**
 * @author unknown
 */
public class ForgotPass extends JFrame {
    private final UserRepo userRepo = new UserRepo();
    private final LoginController loginController = new LoginController();
    private Boolean isPassValid;
    private User user;

    public ForgotPass() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.isPassValid = false;
        saveButton.setEnabled(false);
    }

    private void checkAnswer(ActionEvent e) {
        User user = userRepo.checkSecAnswer(username.getText(),secAns.getText());
        if (Objects.isNull(user)){
            error.setText("no user found");
            error.setForeground(Color.red);
        }else {
            this.user = user;
            error.setText("user found");
            error.setForeground(Color.GREEN);
        }
        if (this.isPassValid && !Objects.isNull(this.user))
            saveButton.setEnabled(true);
    }

    private void checkpass(ActionEvent e) {
        if (loginController.checkPass(password.getText()) && repass.getText().equals(password.getText())) {
            this.isPassValid = true;
            error.setForeground(Color.GREEN);
            error.setText("true");
            saveButton.setEnabled(true);
        }
        else {
            this.isPassValid = false;
            label2.setForeground(Color.red);
            label3.setForeground(Color.red);
            error.setForeground(Color.red);
            error.setText("false");
            saveButton.setEnabled(false);
        }

        if (this.isPassValid && !Objects.isNull(this.user))
            saveButton.setEnabled(true);
    }

    private void save(ActionEvent e) {
        long id = user.getId();
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        String secAns = user.getSecurityAnswer();
        Role role = user.getRole();

        User user = userRepo.save(id , usernameEntered , passwordEntered , secAns , role);
        if (!Objects.isNull(user)) {
            Repository.currentUser = user;
            this.dispose();
            new GeneralView();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        secAns = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        username = new JTextField();
        checkAnswerButton = new JButton();
        error = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        passwordLabel = new JLabel();
        password = new JTextField();
        repassLabel = new JLabel();
        repass = new JTextField();
        checkpassButton = new JButton();
        saveButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font.BOLD,12),java.awt.Color.
            red),panel1. getBorder()));panel1. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062or\u0064er".equals(e.getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(null);
            panel1.add(secAns);
            secAns.setBounds(20, 70, 200, 30);

            //---- label2 ----
            label2.setText("answer security question:");
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(25, 10, 200, 35);

            //---- label3 ----
            label3.setText("what was your first school name?");
            label3.setForeground(Color.white);
            panel1.add(label3);
            label3.setBounds(25, 35, 200, 35);

            //---- label4 ----
            label4.setText("username:");
            label4.setForeground(Color.white);
            panel1.add(label4);
            label4.setBounds(235, 15, 100, 35);
            panel1.add(username);
            username.setBounds(295, 20, 90, 30);

            //---- checkAnswerButton ----
            checkAnswerButton.setText("check");
            checkAnswerButton.addActionListener(e -> checkAnswer(e));
            panel1.add(checkAnswerButton);
            checkAnswerButton.setBounds(255, 70, 125, checkAnswerButton.getPreferredSize().height);

            //---- error ----
            error.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(error);
            error.setBounds(25, 105, 350, 21);

            //---- label5 ----
            label5.setText("password should be at least 8 characters ");
            label5.setForeground(Color.blue);
            panel1.add(label5);
            label5.setBounds(25, 125, 255, 35);

            //---- label6 ----
            label6.setText("and should contain numbers and characters");
            label6.setForeground(Color.blue);
            panel1.add(label6);
            label6.setBounds(25, 150, 255, 35);

            //---- passwordLabel ----
            passwordLabel.setText("password:");
            panel1.add(passwordLabel);
            passwordLabel.setBounds(25, 185, 70, 30);
            panel1.add(password);
            password.setBounds(90, 185, 90, 30);

            //---- repassLabel ----
            repassLabel.setText("re-pass:");
            panel1.add(repassLabel);
            repassLabel.setBounds(25, 220, 70, 30);
            panel1.add(repass);
            repass.setBounds(90, 220, 90, 30);

            //---- checkpassButton ----
            checkpassButton.setText("check pass");
            checkpassButton.addActionListener(e -> checkpass(e));
            panel1.add(checkpassButton);
            checkpassButton.setBounds(190, 205, 125, 30);

            //---- saveButton ----
            saveButton.setText("save");
            saveButton.addActionListener(e -> save(e));
            panel1.add(saveButton);
            saveButton.setBounds(150, 260, 93, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 400, 325);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JTextField secAns;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField username;
    private JButton checkAnswerButton;
    private JLabel error;
    private JLabel label5;
    private JLabel label6;
    private JLabel passwordLabel;
    private JTextField password;
    private JLabel repassLabel;
    private JTextField repass;
    private JButton checkpassButton;
    private JButton saveButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
