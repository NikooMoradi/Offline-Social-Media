/*
 * Created by JFormDesigner on Sun Aug 07 13:02:51 IRDT 2022
 */

package view.post.showPosts;

import model.Comment;
import model.Post;
import model.Reaction;
import repo.CommentRepo;
import repo.ReactionRepo;
import repo.Repository;
import util.ReactionType;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class LikeComment extends JFrame {
    private final ReactionRepo reactionRepo = new ReactionRepo();
    private final CommentRepo commentRepo = new CommentRepo();
    private final Post post;

    public LikeComment(Post post) {
        this.post = post;
        this.setVisible(true);
        initComponents();
        init();
    }

    private void init(){
        List<Comment> comments = commentRepo.getAllCommentsOfPost(post.getId());
        for (Comment comment : comments)
            commentComboBox.addItem(comment);
    }

    private void like(ActionEvent e) {
        List<Reaction> likes = reactionRepo.findAllByUserAndCommentAndType(Repository.currentUser , (Comment) commentComboBox.getModel().getSelectedItem() , ReactionType.LIKE);
        if (likes.size() == 0)
            reactionRepo.saveByComment((Comment) commentComboBox.getModel().getSelectedItem() , Repository.currentUser , ReactionType.LIKE);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        commentComboBox = new JComboBox();
        likeButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );
            panel1.setLayout(null);
            panel1.add(commentComboBox);
            commentComboBox.setBounds(30, 20, 245, commentComboBox.getPreferredSize().height);

            //---- likeButton ----
            likeButton.setText("like");
            likeButton.addActionListener(e -> like(e));
            panel1.add(likeButton);
            likeButton.setBounds(new Rectangle(new Point(295, 20), likeButton.getPreferredSize()));

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
        panel1.setBounds(0, 0, 400, 80);

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
    private JComboBox commentComboBox;
    private JButton likeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
