/*
 * Created by JFormDesigner on Mon Aug 01 16:11:53 IRDT 2022
 */

package view.login;

import controller.generalController;
import model.User;
import repo.Repository;
import repo.UserRepo;
import view.general.GeneralView;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;

/**
 * @author unknown
 */
public class Login extends JFrame {
    private final UserRepo userRepo = new UserRepo();
    
    public Login() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void login(ActionEvent e) {
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        User current = userRepo.auth(usernameEntered,passwordEntered);
        if(!Objects.isNull(current)) {
            Repository.currentUser = current;
            this.dispose();
            new GeneralView();
        }
    }

    private void signup(ActionEvent e) {
        this.dispose();
        new SignUp();
    }

    private void forgotPass(ActionEvent e) {
        this.dispose();
        new ForgotPass();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        usernameLabel = new JLabel();
        username = new JTextField();
        passwordLabel = new JLabel();
        password = new JTextField();
        loginButton = new JButton();
        signupButton = new JButton();
        forgotPassButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder
            () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );
            panel1.setLayout(null);

            //---- usernameLabel ----
            usernameLabel.setText("username:");
            panel1.add(usernameLabel);
            usernameLabel.setBounds(65, 50, 80, 25);
            panel1.add(username);
            username.setBounds(155, 45, 175, 30);

            //---- passwordLabel ----
            passwordLabel.setText("password:");
            panel1.add(passwordLabel);
            passwordLabel.setBounds(65, 80, 80, 25);
            panel1.add(password);
            password.setBounds(155, 80, 175, 30);

            //---- loginButton ----
            loginButton.setText("login");
            loginButton.addActionListener(e -> login(e));
            panel1.add(loginButton);
            loginButton.setBounds(75, 135, 120, 30);

            //---- signupButton ----
            signupButton.setText("sign up");
            signupButton.addActionListener(e -> signup(e));
            panel1.add(signupButton);
            signupButton.setBounds(215, 135, 120, 30);

            //---- forgotPassButton ----
            forgotPassButton.setText("forgot pass");
            forgotPassButton.addActionListener(e -> forgotPass(e));
            panel1.add(forgotPassButton);
            forgotPassButton.setBounds(145, 180, 120, 30);

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
        panel1.setBounds(0, 0, 400, 230);

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
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passwordLabel;
    private JTextField password;
    private JButton loginButton;
    private JButton signupButton;
    private JButton forgotPassButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
