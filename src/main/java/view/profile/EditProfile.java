package view.profile;

import controller.LoginController;
import model.User;
import repo.Repository;
import repo.UserRepo;
import util.*;
import util.Role;
import view.general.GeneralView;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import javax.swing.*;

public class EditProfile extends JPanel {
    private final LoginController loginController = new LoginController();
    private final UserRepo userRepo = new UserRepo();
    private final GeneralView generalView;
    private Boolean isPassValid;

    public EditProfile(GeneralView generalView) {
        this.generalView = generalView;
        initComponents();
        this.isPassValid = false;
        firstInit();
    }

    private void firstInit(){

        roleComboBox.addItem(Role.Business);
        roleComboBox.addItem(Role.Normal);

        roleComboBox.setSelectedItem(Repository.currentUser.getRole());

        saveButton.setEnabled(false);
    }

    private void save(ActionEvent e) {
        User user = Repository.currentUser;
        String usernameEntered = username.getText();
        String passwordEntered = password.getText();
        String secAns = user.getSecurityAnswer();
        Role role = (Role) roleComboBox.getSelectedItem();

        byte[] userImage = Repository.convertImageToByte(filePicker.getFileChooser().getSelectedFile());

        if (Objects.isNull(userImage))
            user = userRepo.save(user.getId() , usernameEntered , passwordEntered , secAns , role);
        else
            user = userRepo.save(user.getId() , usernameEntered , passwordEntered , secAns , role , userImage);

        if (!Objects.isNull(user)) {
            Repository.currentUser = user;
            generalView.showProfile(user);
        }
    }

    private void checkPass(ActionEvent e) {
        if (loginController.checkPass(password.getText())) {
            this.isPassValid = true;
            password.setSelectedTextColor(Color.green);
            saveButton.setEnabled(true);
        }
        else {
            this.isPassValid = false;
            password.setSelectedTextColor(Color.red);
            saveButton.setEnabled(false);
        }
    }

    private void deleteBtn(ActionEvent e) {
        userRepo.deleteUser(Repository.currentUser.getId());
        generalView.logoutButton();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        usernameLabel = new JLabel();
        username = new JTextField();
        passLabel = new JLabel();
        password = new JTextField();
        saveButton = new JButton();
        role = new JLabel();
        roleComboBox = new JComboBox();
        filePicker =  new JFilePicker("Image", "Browse");
        checkPass = new JButton();
        deleteBtn = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
        EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing
        .border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),
        java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))
        throw new RuntimeException();}});
        setLayout(null);

        //---- usernameLabel ----
        usernameLabel.setText("new username:");
        add(usernameLabel);
        usernameLabel.setBounds(5, 10, 105, 30);
        add(username);
        username.setBounds(125, 10, 190, 30);

        //---- passLabel ----
        passLabel.setText("new password:");
        add(passLabel);
        passLabel.setBounds(5, 50, 105, 30);
        add(password);
        password.setBounds(125, 50, 190, 30);

        //---- saveButton ----
        saveButton.setText("Save");
        saveButton.addActionListener(e -> save(e));
        add(saveButton);
        saveButton.setBounds(295, 270, 120, 30);

        //---- role ----
        role.setText("Role");
        add(role);
        role.setBounds(5, 90, 105, 30);
        add(roleComboBox);
        roleComboBox.setBounds(125, 90, 190, 30);

        //---- filePicker ----
        filePicker.setMode(JFilePicker.MODE_OPEN);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".png", "PNG Images");
        fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));

        add(filePicker);
        filePicker.setBounds(new Rectangle(new Point(5, 150), filePicker.getPreferredSize()));

        //---- checkPass ----
        checkPass.setText("Check Pass");
        checkPass.addActionListener(e -> checkPass(e));
        add(checkPass);
        checkPass.setBounds(175, 270, 120, 30);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Account");
        deleteBtn.addActionListener(e -> deleteBtn(e));
        add(deleteBtn);
        deleteBtn.setBounds(5, 270, 145, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel passLabel;
    private JTextField password;
    private JButton saveButton;
    private JLabel role;
    private JComboBox roleComboBox;
    private JFilePicker filePicker;
    private JFileChooser fileChooser;
    private JButton checkPass;
    private JButton deleteBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
