/*
 * Created by JFormDesigner on Mon Aug 01 13:19:17 IRDT 2022
 */

package view.profile;

import java.awt.event.*;

import controller.ChatController;
import controller.FollowController;
import controller.Logger;
import controller.generalController;
import model.User;
import model.chat.Chat;
import repo.FollowRepo;
import repo.Repository;
import util.JImagePanel;
import view.general.GeneralView;
import view.post.showPosts.PostList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class Profile extends JPanel {
    private final FollowRepo followRepo = new FollowRepo();
    private final FollowController followController = new FollowController();
    private final ChatController chatController = new ChatController();
    private final User user;
    private final GeneralView generalView;

    public Profile(User user , GeneralView generalView) {
        this.user = user;
        this.generalView = generalView;
        initComponents();
        setValues();
        setPosts();
    }

    private void setValues(){
        List<User> followers = followRepo.getFollowers(user);
        username.setText(user.getUsername());
        followingCount.setText(String.valueOf(followRepo.getFollowings(user).size()));
        followerCount.setText(String.valueOf(followers.size()));

        BufferedImage pic = Repository.convertByteToImage(user.getUserImage());
        
        JPanel jPanel = new JImagePanel(pic);
        picScrollPane.setViewportView(jPanel);
        
        if (!user.equals(Repository.currentUser)) {
            editProfileButton.setVisible(false);
            editProfileButton.setEnabled(false);
            boolean isFollowing = false;
            for(User follower : followers)
                if (Repository.currentUser.equals(follower)){
                    isFollowing = true;
                    break;
                }
            if (isFollowing) {
                followButton.setVisible(false);
                followButton.setEnabled(false);
            } else {
                unfollowButton.setVisible(false);
                unfollowButton.setEnabled(false);
            }
        } else {
            messageButton.setVisible(false);
            messageButton.setEnabled(false);
            unfollowButton.setVisible(false);
            unfollowButton.setEnabled(false);
            followButton.setVisible(false);
            followButton.setEnabled(false);
        }
    }

    private void follow(ActionEvent e) {
        followController.follow(user.getId());
        unfollowButton.setVisible(true);
        unfollowButton.setEnabled(true);
        followButton.setVisible(true);
        followButton.setEnabled(true);
        setValues();
    }

    private void unfollow(ActionEvent e) {
        followController.unfollow(user.getId());
        unfollowButton.setVisible(true);
        unfollowButton.setEnabled(true);
        followButton.setVisible(true);
        followButton.setEnabled(true);
        setValues();
    }

    private void message(ActionEvent e) {
        Chat chat = chatController.createSingleChat(user);
        generalView.openChat(chat , user);
    }

    private void editProfile(ActionEvent e) {
        generalView.editProfile();
    }

    private void setPosts(){
        List<User> users = new ArrayList<>(Arrays.asList(user));
        scrollPane1.setViewportView(new PostList(users));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        usernameLabel = new JLabel();
        username = new JLabel();
        followerLabel = new JLabel();
        followerCount = new JLabel();
        followingLabel = new JLabel();
        followingCount = new JLabel();
        followButton = new JButton();
        unfollowButton = new JButton();
        messageButton = new JButton();
        editProfileButton = new JButton();
        scrollPane1 = new JScrollPane();
        picScrollPane = new JScrollPane();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
        .swing.border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing
        .border.TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.
        Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.red
        ), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override
        public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName(
        )))throw new RuntimeException();}});
        setLayout(null);

        //---- usernameLabel ----
        usernameLabel.setText("username:");
        add(usernameLabel);
        usernameLabel.setBounds(35, 15, 80, 25);
        add(username);
        username.setBounds(105, 15, 115, 25);

        //---- followerLabel ----
        followerLabel.setText("followers:");
        add(followerLabel);
        followerLabel.setBounds(35, 45, 80, 30);
        add(followerCount);
        followerCount.setBounds(105, 45, 55, 30);

        //---- followingLabel ----
        followingLabel.setText("followings:");
        add(followingLabel);
        followingLabel.setBounds(195, 45, 80, 30);
        add(followingCount);
        followingCount.setBounds(260, 45, 55, 30);

        //---- followButton ----
        followButton.setText("follow");
        followButton.addActionListener(e -> follow(e));
        add(followButton);
        followButton.setBounds(90, 80, 90, 30);

        //---- unfollowButton ----
        unfollowButton.setText("unfollow");
        unfollowButton.addActionListener(e -> unfollow(e));
        add(unfollowButton);
        unfollowButton.setBounds(90, 80, 90, 30);

        //---- messageButton ----
        messageButton.setText("message");
        messageButton.addActionListener(e -> message(e));
        add(messageButton);
        messageButton.setBounds(235, 80, 95, 30);

        //---- editProfileButton ----
        editProfileButton.setText("edit profile");
        editProfileButton.addActionListener(e -> editProfile(e));
        add(editProfileButton);
        editProfileButton.setBounds(90, 110, 240, editProfileButton.getPreferredSize().height);
        add(scrollPane1);
        scrollPane1.setBounds(0, 150, 425, 165);
        add(picScrollPane);
        picScrollPane.setBounds(325, 5, 95, 75);

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
    private JLabel username;
    private JLabel followerLabel;
    private JLabel followerCount;
    private JLabel followingLabel;
    private JLabel followingCount;
    private JButton followButton;
    private JButton unfollowButton;
    private JButton messageButton;
    private JButton editProfileButton;
    private JScrollPane scrollPane1;
    private JScrollPane picScrollPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
