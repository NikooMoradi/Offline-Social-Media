/*
 * Created by JFormDesigner on Wed Aug 03 10:02:30 IRDT 2022
 */

package view.comment;

import java.awt.event.*;
import java.beans.*;
import javax.swing.event.*;
import model.Comment;
import model.Post;
import repo.CommentRepo;
import repo.Repository;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class CreateComment extends JFrame {
    private final CommentRepo commentRepo = new CommentRepo();
    private final Post post;
    private Boolean isReply;

    public CreateComment(Post post) {
        this.setVisible(true);
        this.isReply = false;
        this.post = post;
        initComponents();
        init();
    }

    private void init(){
        List<Comment> comments = commentRepo.getAllCommentsOfPost(post.getId());
        for (Comment comment : comments)
            replyToComboBox.addItem(comment);
        replyToComboBox.setEnabled(false);
    }

    private void save(ActionEvent e) {
        boolean isSaved = false;
        if (isReply) {
            Comment replyToComment = (Comment) replyToComboBox.getModel().getSelectedItem();
            isSaved = commentRepo.saveOnComment(text.getText(), Repository.currentUser, post.getId() , replyToComment.getId());
        }else
            isSaved = commentRepo.save(text.getText() , Repository.currentUser , post.getId());
        if (isSaved)
            this.dispose();
    }

    private void isReplyCheckBoxItemStateChanged(ItemEvent e) {
        isReply = isReplyCheckBox.isSelected();
        replyToComboBox.setEnabled(isReply);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        isReplyCheckBox = new JCheckBox();
        replyToLabel = new JLabel();
        replyToComboBox = new JComboBox();
        scrollPane1 = new JScrollPane();
        text = new JTextArea();
        textLabel = new JLabel();
        saveButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
            ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
            .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
            propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );
            panel1.setLayout(null);

            //---- isReplyCheckBox ----
            isReplyCheckBox.setText("isReply");
            isReplyCheckBox.addItemListener(e -> isReplyCheckBoxItemStateChanged(e));
            panel1.add(isReplyCheckBox);
            isReplyCheckBox.setBounds(30, 25, 75, 30);

            //---- replyToLabel ----
            replyToLabel.setText("replyTo:");
            panel1.add(replyToLabel);
            replyToLabel.setBounds(110, 25, 85, 30);
            panel1.add(replyToComboBox);
            replyToComboBox.setBounds(170, 25, 165, replyToComboBox.getPreferredSize().height);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(text);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(90, 70, 240, 120);

            //---- textLabel ----
            textLabel.setText("text:");
            panel1.add(textLabel);
            textLabel.setBounds(35, 60, 65, 30);

            //---- saveButton ----
            saveButton.setText("save");
            saveButton.addActionListener(e -> save(e));
            panel1.add(saveButton);
            saveButton.setBounds(160, 210, 80, 30);

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
        panel1.setBounds(0, 0, 400, 270);

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
    private JCheckBox isReplyCheckBox;
    private JLabel replyToLabel;
    private JComboBox replyToComboBox;
    private JScrollPane scrollPane1;
    private JTextArea text;
    private JLabel textLabel;
    private JButton saveButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
