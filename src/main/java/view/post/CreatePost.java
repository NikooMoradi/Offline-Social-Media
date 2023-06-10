package view.post;

import model.Post;
import model.User;
import repo.PostRepo;
import repo.Repository;
import util.*;
import util.PostType;
import util.Role;
import view.general.GeneralView;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Objects;
import javax.swing.*;

public class CreatePost extends JPanel {
    private final PostRepo postRepo = new PostRepo();
    private final GeneralView generalView;
    private Post post;
    private Boolean edit = false;

    public CreatePost(GeneralView generalView) {
        initComponents();
        this.generalView = generalView;
        setPostTypeValues();
    }

    public CreatePost(GeneralView generalView , Post post){
        initComponents();
        this.generalView = generalView;
        this.post = post;
        this.edit = true;
        setEditPostValues();
    }

    private void setEditPostValues(){
        User current = Repository.currentUser;
        if (current.getRole() == Role.Normal){
            postTypeComboBox.addItem(PostType.NORMAL);
            postTypeComboBox.getModel().setSelectedItem(PostType.NORMAL);
            postTypeComboBox.setEnabled(false);
        }else {
            PostType[] postTypes = PostType.values();
            for (PostType postType : postTypes)
                postTypeComboBox.addItem(postType);
        }

        postTypeComboBox.setSelectedItem(post.getType());
        text.setText(post.getText());
    }

    private void setPostTypeValues(){
        User current = Repository.currentUser;
        if (current.getRole() == Role.Normal){
            postTypeComboBox.addItem(PostType.NORMAL);
            postTypeComboBox.getModel().setSelectedItem(PostType.NORMAL);
            postTypeComboBox.setEnabled(false);
        }else {
            PostType[] postTypes = PostType.values();
            for (PostType postType : postTypes)
                postTypeComboBox.addItem(postType);
        }
    }

    private void save(ActionEvent e) {
        PostType postType = (PostType) postTypeComboBox.getSelectedItem();
        String textEntered = text.getText();

        byte[] postImage = Repository.convertImageToByte(filePicker.getFileChooser().getSelectedFile());

        if(edit) {
            if (Objects.isNull(postImage))
                postImage = post.getPostImage();
        }
        else {
            if (Objects.isNull(postImage))
                postImage = Repository.convertImageToByte(new File("D:/Projects/socialMedia/src/main/java/util/unkown.png")); //Change To Your Directory
        }

        postRepo.save(textEntered , Repository.currentUser , postType , postImage);
        generalView.showProfile(Repository.currentUser);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        postTypeComboBox = new JComboBox();
        postTypeLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        text = new JTextArea();
        textLabel = new JLabel();
        saveButton = new JButton();
        filePicker = new JFilePicker("Image", "Browse");

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new
        javax.swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax
        .swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java
        .awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.
        PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".
        equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(null);
        add(postTypeComboBox);
        postTypeComboBox.setBounds(110, 25, 170, 30);

        //---- postTypeLabel ----
        postTypeLabel.setText("post type:");
        add(postTypeLabel);
        postTypeLabel.setBounds(10, 25, 100, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(text);
        }
        add(scrollPane1);
        scrollPane1.setBounds(110, 70, 270, 120);

        //---- textLabel ----
        textLabel.setText("text:");
        add(textLabel);
        textLabel.setBounds(10, 70, 100, 30);

        //---- saveButton ----
        saveButton.setText("save");
        saveButton.addActionListener(e -> save(e));
        add(saveButton);
        saveButton.setBounds(new Rectangle(new Point(170, 270), saveButton.getPreferredSize()));

        //---- filePicker ----

        filePicker.setMode(JFilePicker.MODE_OPEN);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".png", "PNG Images");
        fileChooser = filePicker.getFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/"));

        add(filePicker);
        filePicker.setBounds(new Rectangle(new Point(0, 240), filePicker.getPreferredSize()));

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
    private JComboBox postTypeComboBox;
    private JLabel postTypeLabel;
    private JScrollPane scrollPane1;
    private JTextArea text;
    private JLabel textLabel;
    private JButton saveButton;
    private JFilePicker filePicker;
    private JFileChooser fileChooser;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
