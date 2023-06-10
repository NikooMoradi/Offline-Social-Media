package view.chat;

import javax.swing.border.*;
import model.User;
import model.chat.Chat;
import model.chat.Message;
import repo.ChatRepo;
import util.ChatType;
import view.general.GeneralView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class OpenChat extends JPanel {

    private final ChatRepo chatRepo = new ChatRepo();
    private final User user;
    private final GeneralView generalView;
    private Chat chat;

    public OpenChat(Chat chat, User user, GeneralView generalView) {
        this.setVisible(true);
        this.chat = chat;
        this.user = user;
        this.generalView = generalView;
        initComponents();
        firstInit();
        messageScrollInit();
    }

    private void firstInit() {

        if (chat.getType() == ChatType.Group && !chat.getOwner().equals(user))
            deleteChat.setText("Leaving Group");

        editBtn.setVisible(false);

        if (chat.getType() == ChatType.Single) {
            showMembers.setVisible(false);
            String name = chatRepo.getNameOfSingleChat(chat);
            nameLabel.setText(name);
        } else {
            nameLabel.setText(chat.getChatName());
            editBtn.setVisible(chat.getOwner().equals(user));
        }

    }

    public void messageScrollInit() {
        messagesScroll.setViewportView(new MessageListView(chat));
    }

    private void backBtn(ActionEvent e) {
        generalView.directButton();
    }

    private void showMembers(ActionEvent e) {
        generalView.showMembers(chat);
    }

    private void sendBtn(ActionEvent e) {
        String msg = messageField.getText();
        chatRepo.sendMessage(chat, msg);
        messageField.setText("");
        messageScrollInit();
    }

    private void editBtn(ActionEvent e) {
        generalView.editChat(chat, user);
    }

    private void deleteChat(ActionEvent e) {
        if (chat.getType() == ChatType.Group && !chat.getOwner().equals(user)) {
            chatRepo.leavingChat(user, chat);
            generalView.directButton();
        } else {
            Long chatId = chat.getId();
            chat = null;
            chatRepo.deleteChat(chatId);
            generalView.directButton();
        }
    }

    private void forward(ActionEvent e) {
        new ForwardMessage(chat);
    }

    private void editMessage(ActionEvent e) {
        new EditMessage(chat , this);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        messageField = new JTextField();
        sendBtn = new JButton();
        messagesScroll = new JScrollPane();
        nameLabel = new JLabel();
        backBtn = new JButton();
        showMembers = new JButton();
        editBtn = new JButton();
        deleteChat = new JButton();
        editMessageButton = new JButton();
        forwardButton = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void
        propertyChange(java.beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException()
        ;}});
        setLayout(null);
        add(messageField);
        messageField.setBounds(10, 270, 310, 30);

        //---- sendBtn ----
        sendBtn.setText("Send");
        sendBtn.addActionListener(e -> sendBtn(e));
        add(sendBtn);
        sendBtn.setBounds(318, 270, sendBtn.getPreferredSize().width, 30);
        add(messagesScroll);
        messagesScroll.setBounds(10, 100, 380, 170);

        //---- nameLabel ----
        nameLabel.setText("text");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(LineBorder.createGrayLineBorder());
        add(nameLabel);
        nameLabel.setBounds(10, 65, 155, 30);

        //---- backBtn ----
        backBtn.setText("Back");
        backBtn.addActionListener(e -> backBtn(e));
        add(backBtn);
        backBtn.setBounds(10, 5, backBtn.getPreferredSize().width, 30);

        //---- showMembers ----
        showMembers.setText("Show Members");
        showMembers.addActionListener(e -> showMembers(e));
        add(showMembers);
        showMembers.setBounds(250, 34, 140, 30);

        //---- editBtn ----
        editBtn.setText("Edit");
        editBtn.addActionListener(e -> editBtn(e));
        add(editBtn);
        editBtn.setBounds(new Rectangle(new Point(175, 34), editBtn.getPreferredSize()));

        //---- deleteChat ----
        deleteChat.setText("Delete Chat");
        deleteChat.addActionListener(e -> deleteChat(e));
        add(deleteChat);
        deleteChat.setBounds(255, 5, 133, deleteChat.getPreferredSize().height);

        //---- editMessageButton ----
        editMessageButton.setText("edit message");
        editMessageButton.addActionListener(e -> editMessage(e));
        add(editMessageButton);
        editMessageButton.setBounds(265, 65, 125, 30);

        //---- forwardButton ----
        forwardButton.setText("forward");
        forwardButton.addActionListener(e -> forward(e));
        add(forwardButton);
        forwardButton.setBounds(175, 65, 90, 30);

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
    private JTextField messageField;
    private JButton sendBtn;
    private JScrollPane messagesScroll;
    private JLabel nameLabel;
    private JButton backBtn;
    private JButton showMembers;
    private JButton editBtn;
    private JButton deleteChat;
    private JButton editMessageButton;
    private JButton forwardButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
