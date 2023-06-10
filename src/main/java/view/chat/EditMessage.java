/*
 * Created by JFormDesigner on Mon Aug 08 17:55:14 IRDT 2022
 */

package view.chat;

import java.awt.event.*;
import model.chat.Chat;
import model.chat.Message;
import repo.ChatRepo;
import repo.Repository;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * @author unknown
 */
public class EditMessage extends JFrame {
    private final ChatRepo chatRepo = new ChatRepo();
    private final Chat chat;
    private final OpenChat openChat;
    private Message message;
    
    public EditMessage(Chat chat , OpenChat openChat) {
        this.openChat = openChat;
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
    }

    private void messageComboBoxItemStateChanged(ItemEvent e) {
        message = (Message) messageComboBox.getModel().getSelectedItem();
        messageText.setText("");
        messageText.append(message.getMessage());
    }

    private void save(ActionEvent e) {
        chatRepo.updateMessage(message , messageText.getText());
        openChat.messageScrollInit();
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        messageComboBox = new JComboBox();
        messageLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        messageText = new JTextArea();
        textLabel = new JLabel();
        saveButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
            border.EmptyBorder(0,0,0,0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",javax.swing.border.TitledBorder.CENTER
            ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069al\u006fg",java.awt.Font
            .BOLD,12),java.awt.Color.red),panel1. getBorder()));panel1. addPropertyChangeListener(
            new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062or\u0064er"
            .equals(e.getPropertyName()))throw new RuntimeException();}});
            panel1.setLayout(null);

            //---- messageComboBox ----
            messageComboBox.addItemListener(e -> messageComboBoxItemStateChanged(e));
            panel1.add(messageComboBox);
            messageComboBox.setBounds(95, 20, 290, messageComboBox.getPreferredSize().height);

            //---- messageLabel ----
            messageLabel.setText("message:");
            panel1.add(messageLabel);
            messageLabel.setBounds(15, 20, 80, 30);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(messageText);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(100, 70, 280, 125);

            //---- textLabel ----
            textLabel.setText("text:");
            panel1.add(textLabel);
            textLabel.setBounds(20, 65, 40, 30);

            //---- saveButton ----
            saveButton.setText("save");
            saveButton.addActionListener(e -> save(e));
            panel1.add(saveButton);
            saveButton.setBounds(165, 220, 83, 30);

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
    private JComboBox messageComboBox;
    private JLabel messageLabel;
    private JScrollPane scrollPane1;
    private JTextArea messageText;
    private JLabel textLabel;
    private JButton saveButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
