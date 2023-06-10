/*
 * Created by JFormDesigner on Wed Aug 03 13:17:44 IRDT 2022
 */

package view.followingPost;

import model.User;
import repo.FollowRepo;
import repo.Repository;
import view.post.showPosts.PostList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class FollowingPost extends JPanel {
    private final FollowRepo followRepo = new FollowRepo();

    public FollowingPost() {
        initComponents();
        init();
    }

    private void init(){
        List<User> followings = followRepo.getFollowings(Repository.currentUser);
        scrollPane1.setViewportView(new PostList(followings));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(null);

        //---- label1 ----
        label1.setText("happened recently");
        add(label1);
        label1.setBounds(25, 20, 190, 30);
        add(scrollPane1);
        scrollPane1.setBounds(0, 65, 425, 250);

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
    private JLabel label1;
    private JScrollPane scrollPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
