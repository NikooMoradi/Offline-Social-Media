package view.general;

import model.Post;
import model.User;
import model.chat.Chat;
import repo.Repository;
import view.activity.ActivityList;
import view.chat.ChatView;
import view.chat.CreateChat;
import view.chat.OpenChat;
import view.chat.ShowMembers;
import view.followingPost.FollowingPost;
import view.login.Login;
import view.post.CreatePost;
import view.profile.EditProfile;
import view.profile.Profile;
import view.search.Search;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralView extends JFrame {

    public GeneralView() {
        Repository.generalView = this;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        mainMenu();
        splitPane1.setDividerLocation(195);
    }

    private void mainMenu() {
        String[] tabs = {"Following Posts", "Create Post", "Activity", "Search", "Direct", "Profile", "logout", "Exit"};

        int y = 0;

        for (String tabName : tabs) {
            JButton button = new JButton(tabName);
            button.setBounds(0, y, 195, 42);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tabName.equals("Following Posts"))
                        followingPostsButton();
                    else if (tabName.equals("Create Post"))
                        createPostButton();
                    else if (tabName.equals("Activity"))
                        activityButton();
                    else if (tabName.equals("Search"))
                        searchButton();
                    else if (tabName.equals("Direct"))
                        directButton();
                    else if (tabName.equals("Profile"))
                        profileButton();
                    else if (tabName.equals("logout"))
                        logoutButton();
                    else if (tabName.equals("Exit"))
                        exitButton();
                }
            });
            immutable.add(button);
            y += 42;
        }
    }

    private void exitButton() {
        System.exit(0);
    }

    private void followingPostsButton() {
        splitPane1.setRightComponent(new FollowingPost());
        splitPane1.setDividerLocation(195);
    }

    private void createPostButton() {
        splitPane1.setRightComponent(new CreatePost(this));
        splitPane1.setDividerLocation(195);
    }

    public void editPost(Post post) {
        splitPane1.setRightComponent(new CreatePost(this , post));
        splitPane1.setDividerLocation(195);
    }


    private void activityButton() {
        splitPane1.setRightComponent(new ActivityList());
        splitPane1.setDividerLocation(195);
    }

    private void searchButton() {
        splitPane1.setRightComponent(new Search(this));
        splitPane1.setDividerLocation(195);
    }

    public void directButton() {
        splitPane1.setRightComponent(new ChatView(Repository.currentUser, this));
        splitPane1.setDividerLocation(195);
    }

    private void profileButton() {
        splitPane1.setRightComponent(new Profile(Repository.currentUser, this));
        splitPane1.setDividerLocation(195);
    }

    public void logoutButton() {
        Repository.currentUser = null;
        this.dispose();
        new Login();
    }

    public void showProfile(User user) {
        splitPane1.setRightComponent(new Profile(user, this));
        splitPane1.setDividerLocation(195);
    }

    public void editProfile() {
        splitPane1.setRightComponent(new EditProfile(this));
        splitPane1.setDividerLocation(195);
    }

    public void createChat(User user) {
        splitPane1.setRightComponent(new CreateChat(user, this));
        splitPane1.setDividerLocation(195);
    }

    public void editChat(Chat chat, User user) {
        splitPane1.setRightComponent(new CreateChat(user, this, chat));
        splitPane1.setDividerLocation(195);
    }

    public void openChat(Chat chat, User user) {
        splitPane1.setRightComponent(new OpenChat(chat, user, this));
        splitPane1.setDividerLocation(195);
    }

    public void showMembers(Chat chat) {
        splitPane1.setRightComponent(new ShowMembers(chat, this));
        splitPane1.setDividerLocation(195);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        splitPane1 = new JSplitPane();
        immutable = new JPanel();
        mutable = new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                    swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border
                    .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog"
                    , java.awt.Font.BOLD, 12), java.awt.Color.red), panel1.getBorder
                    ()));
            panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java
                                                   .beans.PropertyChangeEvent e) {
                    if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException
                            ();
                }
            });
            panel1.setLayout(null);

            //======== splitPane1 ========
            {

                //======== immutable ========
                {
                    immutable.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for (int i = 0; i < immutable.getComponentCount(); i++) {
                            Rectangle bounds = immutable.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = immutable.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        immutable.setMinimumSize(preferredSize);
                        immutable.setPreferredSize(preferredSize);
                    }
                }
                splitPane1.setLeftComponent(immutable);

                //======== mutable ========
                {
                    mutable.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for (int i = 0; i < mutable.getComponentCount(); i++) {
                            Rectangle bounds = mutable.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = mutable.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        mutable.setMinimumSize(preferredSize);
                        mutable.setPreferredSize(preferredSize);
                    }
                }
                splitPane1.setRightComponent(mutable);
            }
            panel1.add(splitPane1);
            splitPane1.setBounds(0, 0, 620, 335);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel1.getComponentCount(); i++) {
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
        panel1.setBounds(0, 0, 620, 335);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
    private JSplitPane splitPane1;
    private JPanel immutable;
    private JPanel mutable;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
