package view.post.showPosts;

import java.awt.event.*;
import javax.swing.border.*;
import model.Comment;
import model.Post;
import model.Reaction;
import repo.CommentRepo;
import repo.PostRepo;
import repo.ReactionRepo;
import repo.Repository;
import util.JImagePanel;
import util.ReactionType;
import util.Role;
import view.comment.CreateComment;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;

public class PostEntity extends JPanel {
    private final Post post;
    private final PostRepo postRepo = new PostRepo();
    private final CommentRepo commentRepo = new CommentRepo();
    private final ReactionRepo reactionRepo = new ReactionRepo();
    
    public PostEntity(Post post) {
        initComponents();
        this.post = post;
        setValues();
        checkLike();
        createView();
        if (!(post.getUser().equals(Repository.currentUser) && post.getUser().getRole() == Role.Business))
            statsButton.setVisible(false);
        
        if(!post.getUser().equals(Repository.currentUser)) {
            editBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }
    }

    private void createView(){
        List<Reaction> reactions = reactionRepo.findAllByUserAndPostAndType(Repository.currentUser , post , ReactionType.VIEW);
        if (reactions.size() == 0)
            reactionRepo.save(post.getId(), Repository.currentUser , ReactionType.VIEW);
    }
    
    private void checkLike(){
        List<Reaction> reactions = reactionRepo.getAllLikesOfPost(post.getId());
        boolean isLiked = false;
        for (Reaction reaction : reactions)
            if (reaction.getUser().equals(Repository.currentUser)) {
                isLiked = true;
                break;
            }
        likeButton.setVisible(!isLiked);
        likeButton.setEnabled(!isLiked);
        dislikeButton.setVisible(isLiked);
        dislikeButton.setEnabled(isLiked);
    }
    
    private void setValues(){
        List<Comment> comments = commentRepo.getAllCommentsOfPostWithoutParent(post.getId());
        for (Comment commentOfPost : comments) {
            comment.append(commentOfPost.getUser().getUsername() + ":" + reactionRepo.getAllLikesOfComment(commentOfPost).size() + "likes\n" + commentOfPost.getText() + "\n");
            List<Comment> firstLevelReplies = commentRepo.findAllByParent(commentOfPost);
                for (Comment firstLevelReply : firstLevelReplies) {
                    comment.append("\t" + firstLevelReply.getUser().getUsername() + ":" + reactionRepo.getAllLikesOfComment(commentOfPost).size() + "likes\n\t" + firstLevelReply.getText().replace("\n" , "\n\t") + "\n");
                    List<Comment> secondLevelReplies = commentRepo.findAllByParent(firstLevelReply);
                    for (Comment secondLevelReply : secondLevelReplies) {
                        comment.append("\t\t" + secondLevelReply.getUser().getUsername() + ":" + reactionRepo.getAllLikesOfComment(commentOfPost).size() + "likes\n\t\t" + secondLevelReply.getText().replace("\n" , "\n\t\t") + "\n");
                    }
                }
            comment.append("---------------\n");
        }
        text.append(post.getText());
        List<Reaction> likeList = reactionRepo.getAllLikesOfPost(post.getId());
        likes.setText(String.valueOf(likeList.size()));
        likes.setEnabled(false);
        comment.setEnabled(false);
        comment.setVisible(false);
        commentLabel.setVisible(false);
        scrollPane2.setVisible(false);
        commentButton.setVisible(false);

        BufferedImage pic = Repository.convertByteToImage(post.getPostImage());
        JPanel jPanel = new JImagePanel(pic);
        picScrollPane.setViewportView(jPanel);
    }

    private void comment(ActionEvent e) {
        new CreateComment(post);
    }

    private void like(ActionEvent e) {
        reactionRepo.save(post.getId(), Repository.currentUser , ReactionType.LIKE);
        likeButton.setVisible(false);
        likeButton.setEnabled(false);
        dislikeButton.setVisible(true);
        dislikeButton.setEnabled(true);
        List<Reaction> likeList = reactionRepo.getAllLikesOfPost(post.getId());
        likes.setText(String.valueOf(likeList.size()));
    }

    private void dislike(ActionEvent e) {
        List<Reaction> reactions = reactionRepo.getAllLikesOfPost(post.getId());
        Reaction like = null;
        for (Reaction reaction : reactions)
            if (reaction.getUser().equals(Repository.currentUser))
                like = reaction;
        reactionRepo.delete(like.getId());
        likeButton.setVisible(true);
        likeButton.setEnabled(true);
        dislikeButton.setVisible(false);
        dislikeButton.setEnabled(false);
        List<Reaction> likeList = reactionRepo.getAllLikesOfPost(post.getId());
        likes.setText(String.valueOf(likeList.size()));
    }

    private void showComment(ActionEvent e) {
        comment.setVisible(true);
        commentLabel.setVisible(true);
        scrollPane2.setVisible(true);
        commentButton.setVisible(true);
    }

    private void stats(ActionEvent e) {
        new Stat(post);
    }

    private void editBtn(ActionEvent e) {
        Repository.generalView.editPost(post);
    }

    private void deleteBtn(ActionEvent e) {
        postRepo.delete(post.getId());
        Repository.generalView.showProfile(Repository.currentUser);
    }

    private void likeComment(ActionEvent e) {
        new LikeComment(post);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        text = new JTextArea();
        likeButton = new JButton();
        commentButton = new JButton();
        label1 = new JLabel();
        commentLabel = new JLabel();
        scrollPane2 = new JScrollPane();
        comment = new JTextArea();
        showCommentButton = new JButton();
        statsButton = new JButton();
        dislikeButton = new JButton();
        likes = new JTextField();
        editBtn = new JButton();
        deleteBtn = new JButton();
        picScrollPane = new JScrollPane();
        likeComment = new JButton();

        //======== this ========
        setBorder(LineBorder.createBlackLineBorder());
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
        0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
        . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
        red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
        beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(text);
        }
        add(scrollPane1);
        scrollPane1.setBounds(100, 130, 300, 80);

        //---- likeButton ----
        likeButton.setText("like");
        likeButton.addActionListener(e -> like(e));
        add(likeButton);
        likeButton.setBounds(10, 195, 85, 30);

        //---- commentButton ----
        commentButton.setText("comment");
        commentButton.addActionListener(e -> comment(e));
        add(commentButton);
        commentButton.setBounds(180, 325, 120, 30);

        //---- label1 ----
        label1.setText("text:");
        add(label1);
        label1.setBounds(30, 130, 45, 30);

        //---- commentLabel ----
        commentLabel.setText("comments:");
        add(commentLabel);
        commentLabel.setBounds(10, 235, 80, 30);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(comment);
        }
        add(scrollPane2);
        scrollPane2.setBounds(100, 235, 300, 80);

        //---- showCommentButton ----
        showCommentButton.setText("show comments");
        showCommentButton.addActionListener(e -> showComment(e));
        add(showCommentButton);
        showCommentButton.setBounds(10, 325, 165, 30);

        //---- statsButton ----
        statsButton.setText("stats");
        statsButton.addActionListener(e -> stats(e));
        add(statsButton);
        statsButton.setBounds(305, 325, 85, 30);

        //---- dislikeButton ----
        dislikeButton.setText("dislike");
        dislikeButton.addActionListener(e -> dislike(e));
        add(dislikeButton);
        dislikeButton.setBounds(10, 195, 85, 30);

        //---- likes ----
        likes.setHorizontalAlignment(SwingConstants.CENTER);
        add(likes);
        likes.setBounds(25, 160, 55, 35);

        //---- editBtn ----
        editBtn.setText("Edit");
        editBtn.addActionListener(e -> editBtn(e));
        add(editBtn);
        editBtn.setBounds(10, 10, 80, 30);

        //---- deleteBtn ----
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(e -> deleteBtn(e));
        add(deleteBtn);
        deleteBtn.setBounds(10, 65, 80, 30);
        add(picScrollPane);
        picScrollPane.setBounds(100, 10, 300, 115);

        //---- likeComment ----
        likeComment.setText("like");
        likeComment.addActionListener(e -> likeComment(e));
        add(likeComment);
        likeComment.setBounds(10, 265, 85, likeComment.getPreferredSize().height);

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
    private JScrollPane scrollPane1;
    private JTextArea text;
    private JButton likeButton;
    private JButton commentButton;
    private JLabel label1;
    private JLabel commentLabel;
    private JScrollPane scrollPane2;
    private JTextArea comment;
    private JButton showCommentButton;
    private JButton statsButton;
    private JButton dislikeButton;
    private JTextField likes;
    private JButton editBtn;
    private JButton deleteBtn;
    private JScrollPane picScrollPane;
    private JButton likeComment;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
