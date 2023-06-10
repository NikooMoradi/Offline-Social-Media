package view.chat;

import model.Post;
import model.chat.Chat;
import model.chat.Message;
import repo.ChatRepo;
import view.post.showPosts.PostEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MessageListView extends JPanel {

    public MessageListView(Chat chat){
        init(chat);
    }

    private void init(Chat chat){
        List<Message> messages = new ChatRepo().getAllMessages(chat);

        this.setLayout(new GridLayout(messages.size(),1,1,1));

        for (Message message : messages) {
            JPanel aPanel = new MessageItem(message);
            aPanel.setPreferredSize(new Dimension(370, 80));
            this.add(aPanel);
        }
    }
}
