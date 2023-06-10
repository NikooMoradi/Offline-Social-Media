/*
 * Created by JFormDesigner on Mon Aug 08 17:32:07 IRDT 2022
 */

package view.chat;

import java.awt.event.*;
import model.chat.Chat;
import model.chat.Message;
import repo.ChatRepo;
import repo.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class ForwardMessage extends JFrame {
    private final ChatRepo chatRepo = new ChatRepo();
    private final Chat chat;

    public ForwardMessage(Chat chat) {
        this.chat = chat;
        this.setVisible(true);
        initComponents();
        init();
    }

    private void init(){
        List<Message> messageList = chatRepo.getAllMessages(chat);
        messageList.removeIf(m -> !m.getSender().equals(Repository.currentUser));
        for (Message message : messageList)
            messageComboBox.addItem(message);
        List<Chat> chatList = chatRepo.getAllUserChat(Repository.currentUser);
        for (Chat eachChat : chatList)
            chatComboBox.addItem(eachChat);
    }

    private void forward(ActionEvent e) {
        Message message = (Message) messageComboBox.getModel().getSelectedItem();
        String generated = String.join(": " , "forwarded from" , message.getSender().getUsername() , message.getMessage());
        Chat newChat = (Chat) chatComboBox.getModel().getSelectedItem();
        chatRepo.sendMessage(newChat, generated);
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        messageComboBox = new JComboBox();
        chatComboBox = new JComboBox();
        messageLabel = new JLabel();
        chatLabel = new JLabel();
        forwardButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,panel1. getBorder( )) ); panel1. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            panel1.setLayout(null);
            panel1.add(messageComboBox);
            messageComboBox.setBounds(110, 25, 275, messageComboBox.getPreferredSize().height);
            panel1.add(chatComboBox);
            chatComboBox.setBounds(110, 60, 275, 30);

            //---- messageLabel ----
            messageLabel.setText("message:");
            panel1.add(messageLabel);
            messageLabel.setBounds(25, 25, 80, 30);

            //---- chatLabel ----
            chatLabel.setText("chat:");
            panel1.add(chatLabel);
            chatLabel.setBounds(25, 60, 80, 30);

            //---- forwardButton ----
            forwardButton.setText("forward");
            forwardButton.addActionListener(e -> forward(e));
            panel1.add(forwardButton);
            forwardButton.setBounds(140, 120, 135, 30);

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
        panel1.setBounds(0, 0, 400, 165);

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
    private JComboBox messageComboBox;
    private JComboBox chatComboBox;
    private JLabel messageLabel;
    private JLabel chatLabel;
    private JButton forwardButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
