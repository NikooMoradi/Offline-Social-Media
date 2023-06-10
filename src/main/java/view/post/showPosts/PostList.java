package view.post.showPosts;

import model.Post;
import model.User;
import repo.PostRepo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostList extends JPanel {
    private final PostRepo postRepo = new PostRepo();
    private final List<User> users;

    public PostList(List<User> users) {
        this.users = users;
        if (users.size() > 1)
            initforAll();
        else initforOne();
    }

    private void initforOne(){
        List<Post> posts = postRepo.getAllUserPostsOrderByCreateDate(users.get(0));
        this.setLayout(new GridLayout(posts.size(),1,1,1));

        for (Post post : posts) {
            JPanel aPanel = new PostEntity(post);
            aPanel.setPreferredSize(new Dimension(425, 395));
            this.add(aPanel);
        }
    }

    private void initforAll(){
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            List<Post> postList = postRepo.getAllUserPostsOrderByCreateDate(user);
            posts.addAll(postList);
        }
        this.setLayout(new GridLayout(posts.size(),1,1,1));

        for (Post post : posts) {
            JPanel aPanel = new PostEntity(post);
            aPanel.setPreferredSize(new Dimension(425, 395));
            this.add(aPanel);
        }
    }
}