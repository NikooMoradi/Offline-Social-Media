package view.login;

import controller.LoginController;
import model.User;
import repo.Repository;
import repo.UserRepo;
import util.JFilePicker;
import util.Role;
import view.general.GeneralView;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import javax.swing.*;

public class SignUp extends JFrame {
    private final LoginController loginController = new LoginController();
    private final UserRepo userRepo = new UserRepo();
    private Boolean isPassValid;

    public SignUp() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setOptions();
        this.isPassValid = false;
        saveButton.setEnabled(false);
    }

    private void setOptions(){
        roleComboBox.addItem(Role.Business);
        roleComboBox.addItem(Role.Normal);
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
    }

    private void save(ActionEvent e) {
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        String secAns = secAnswer.getText();
        Role role = (Role) roleComboBox.getSelectedItem();

        byte[] userImage = Repository.convertImageToByte(filePicker.getFileChooser().getSelectedFile());

        //Change To Your Directory
        if(Objects.isNull(userImage))
            userImage = Repository.convertImageToByte(new File("D:/Projects/socialMedia/src/main/java/util/unkown.png"));

        User user = userRepo.save(0l , usernameEntered , passwordEntered , secAns , role , userImage);
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
        label1 = new JLabel();
        username = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        password = new JTextField();
        passwordLabel = new JLabel();
        repass = new JTextField();
        repassLabel = new JLabel();
        checkpassButton = new JButton();
        roleComboBox = new JComboBox();
        roleLabel = new JLabel();
        saveButton = new JButton();
        error = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        secAnswer = new JTextField();
        filePicker = new JFilePicker("Image", "Browse");

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
            javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax
            . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
            . awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .
            PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .
            equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("username:");
            panel1.add(label1);
            label1.setBounds(10, 5, 70, 30);
            panel1.add(username);
            username.setBounds(80, 5, 120, 30);

            //---- label2 ----
            label2.setText("password should be at least 8 characters ");
            label2.setForeground(Color.blue);
            panel1.add(label2);
            label2.setBounds(205, 45, 255, 35);

            //---- label3 ----
            label3.setText("and should contain numbers and characters");
            label3.setForeground(Color.blue);
            panel1.add(label3);
            label3.setBounds(205, 70, 255, 35);
            panel1.add(password);
            password.setBounds(80, 50, 120, 30);

            //---- passwordLabel ----
            passwordLabel.setText("password:");
            panel1.add(passwordLabel);
            passwordLabel.setBounds(10, 50, 70, 30);
            panel1.add(repass);
            repass.setBounds(80, 85, 120, 30);

            //---- repassLabel ----
            repassLabel.setText("re-pass:");
            panel1.add(repassLabel);
            repassLabel.setBounds(10, 85, 70, 30);

            //---- checkpassButton ----
            checkpassButton.setText("check pass");
            checkpassButton.addActionListener(e -> checkpass(e));
            panel1.add(checkpassButton);
            checkpassButton.setBounds(10, 270, 125, 30);
            panel1.add(roleComboBox);
            roleComboBox.setBounds(80, 125, 120, roleComboBox.getPreferredSize().height);

            //---- roleLabel ----
            roleLabel.setText("role:");
            panel1.add(roleLabel);
            roleLabel.setBounds(10, 125, 45, 30);

            //---- saveButton ----
            saveButton.setText("save");
            saveButton.addActionListener(e -> save(e));
            panel1.add(saveButton);
            saveButton.setBounds(145, 270, 93, 30);
            panel1.add(error);
            error.setBounds(325, 140, 60, 21);

            //---- label4 ----
            label4.setText("Security Question:");
            panel1.add(label4);
            label4.setBounds(10, 160, 125, 30);

            //---- label5 ----
            label5.setText("first school name?");
            panel1.add(label5);
            label5.setBounds(10, 180, 125, 30);
            panel1.add(secAnswer);
            secAnswer.setBounds(120, 180, 120, 30);

            //---- filePicker ----

            filePicker.setMode(JFilePicker.MODE_OPEN);
            filePicker.addFileTypeFilter(".jpg", "JPEG Images");
            filePicker.addFileTypeFilter(".png", "PNG Images");
            fileChooser = filePicker.getFileChooser();
            fileChooser.setCurrentDirectory(new File("C:/"));

            panel1.add(filePicker);
            filePicker.setBounds(new Rectangle(new Point(0, 225), filePicker.getPreferredSize()));

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
        panel1.setBounds(0, 0, 500, 320);

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
    private JLabel label1;
    private JTextField username;
    private JLabel label2;
    private JLabel label3;
    private JTextField password;
    private JLabel passwordLabel;
    private JTextField repass;
    private JLabel repassLabel;
    private JButton checkpassButton;
    private JComboBox roleComboBox;
    private JLabel roleLabel;
    private JButton saveButton;
    private JLabel error;
    private JLabel label4;
    private JLabel label5;
    private JTextField secAnswer;
    private JFilePicker filePicker;
    private JFileChooser fileChooser;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
