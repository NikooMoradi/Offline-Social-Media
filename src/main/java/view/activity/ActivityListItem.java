package view.activity;

import model.Comment;
import model.Post;
import model.Reaction;
import repo.CommentRepo;
import repo.PostRepo;
import repo.ReactionRepo;
import repo.Repository;
import view.post.showPosts.PostEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class ActivityListItem extends JPanel {
    private final PostRepo postRepo = new PostRepo();
    private final CommentRepo commentRepo = new CommentRepo();
    private final ReactionRepo reactionRepo = new ReactionRepo();

    public ActivityListItem(Date fromDate , Date toDate){
        init(fromDate , toDate);
    }

    private void init(Date fromDate , Date toDate){

        List<Post> posts = postRepo.findAllPostsByUserAndBetween(Repository.currentUser , fromDate , toDate);
        List<Comment> comments = commentRepo.findAllByUserAndDateBetween(Repository.currentUser , fromDate , toDate);
        List<Reaction> reactions = reactionRepo.findAllByUserAndBetween(Repository.currentUser , fromDate , toDate);

        this.setLayout(new GridLayout(posts.size() + comments.size() + reactions.size(),1,1,1));

        for (Post post : posts) {
            JPanel aPanel = new PostEntity(post);
            aPanel.setPreferredSize(new Dimension(420, 395));
            this.add(aPanel);
        }

        for (Comment c : comments){
            JPanel aPanel = new CommentItem(c);
            aPanel.setPreferredSize(new Dimension(420, 125));
            this.add(aPanel);
        }

        for (Reaction reaction : reactions){
            JPanel aPanel = new ReactionItem(reaction);
            aPanel.setPreferredSize(new Dimension(420, 125));
            this.add(aPanel);
        }
    }
}
