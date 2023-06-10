package view.chat;

import java.awt.event.*;
import model.User;
import model.chat.Chat;
import repo.ChatRepo;
import repo.Repository;
import util.ChatType;
import view.general.GeneralView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class ChatView extends JPanel {

    private final ChatRepo chatRepo = new ChatRepo();
    private final User user;
    private final GeneralView generalView;

    public ChatView(User user, GeneralView generalView) {
        this.setVisible(true);
        this.user = user;
        this.generalView = generalView;
        initComponents();
        chatComboBox.addItem("All");
        chatComboBox.addItem("Only Group");
        chatComboBox.addItem("Only Single");
        fillChatGrid();
    }

    private void fillChatGrid() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(0);
        }

        String[] header = new String[]{"Chat Name", "Unread Messages", " "};

        dtm.setColumnIdentifiers(header);
        chatGrid.setModel(dtm);
        chatGrid.setRowSorter(null);

        List<Chat> chats ;
        
        if (chatComboBox.getSelectedItem().equals("Only Group")){
            chats = chatRepo.getAllUserChatByType(user , ChatType.Group);
        } else if (chatComboBox.getSelectedItem().equals("Only Single")){
            chats = chatRepo.getAllUserChatByType(user , ChatType.Single);
        } else {
            chats = chatRepo.getAllUserChat(user);
        }

        for (Chat chat : chats) {
            long id = (chat.getId() != null) ? chat.getId() : 0L;
            String username = chat.getChatName();
            int countUnread = chatRepo.getUnreadMessagesCount(chat, user);

            dtm.addRow(new Object[]{username, countUnread, id});
        }

        chatGrid.getColumnModel().getColumn(2).setMinWidth(0);
        chatGrid.getColumnModel().getColumn(2).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        chatGrid.setRowSorter(sorter);
    }

    private void search(ActionEvent e) {
        String text = searchField.getText();
        final TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) chatGrid.getRowSorter();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch (PatternSyntaxException pse) {
                System.out.println("Bad regex pattern");
            }
        }
    }

    private void openChat(ActionEvent e) {
        Chat chat = chatRepo.getById((Long) Repository.map.get("userId"));
        generalView.openChat(chat, user);
    }

    private void createChat(ActionEvent e) {
        generalView.createChat(user);
    }

    private void chatGridMouseClicked(MouseEvent e) {
        int row = chatGrid.getRowSorter().convertRowIndexToModel(chatGrid.getSelectedRow());
        Long value = (Long) chatGrid.getModel().getValueAt(row, 2);
        Repository.map.put("userId", value);
    }

    private void chatComboBoxItemStateChanged(ItemEvent e) {
        fillChatGrid();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        searchField = new JTextField();
        searchButton = new JButton();
        chatLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        chatGrid = new JTable();
        openChat = new JButton();
        createChat = new JButton();
        chatComboBox = new JComboBox();
        chatTypeLabel = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new
        javax . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax
        . swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java
        . awt .Font ( "D\u0069al\u006fg", java .awt . Font. BOLD ,12 ) ,java . awt
        . Color .red ) , getBorder () ) );  addPropertyChangeListener( new java. beans .
        PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .
        equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(null);
        add(searchField);
        searchField.setBounds(14, 14, 281, 30);

        //---- searchButton ----
        searchButton.setText("Search");
        searchButton.addActionListener(e -> search(e));
        add(searchButton);
        searchButton.setBounds(310, 15, searchButton.getPreferredSize().width, 30);

        //---- chatLabel ----
        chatLabel.setText("Chats");
        chatLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(chatLabel);
        chatLabel.setBounds(15, 105, 385, chatLabel.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    chatGridMouseClicked(e);
                }
            });

            //---- chatGrid ----
            chatGrid.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    chatGridMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(chatGrid);
        }
        add(scrollPane1);
        scrollPane1.setBounds(15, 125, 385, 126);

        //---- openChat ----
        openChat.setText("Open Chat");
        openChat.addActionListener(e -> openChat(e));
        add(openChat);
        openChat.setBounds(215, 255, openChat.getPreferredSize().width, 30);

        //---- createChat ----
        createChat.setText("Create Chat");
        createChat.addActionListener(e -> createChat(e));
        add(createChat);
        createChat.setBounds(310, 255, createChat.getPreferredSize().width, 30);

        //---- chatComboBox ----
        chatComboBox.addItemListener(e -> chatComboBoxItemStateChanged(e));
        add(chatComboBox);
        chatComboBox.setBounds(85, 60, 130, chatComboBox.getPreferredSize().height);

        //---- chatTypeLabel ----
        chatTypeLabel.setText("Chat Type");
        add(chatTypeLabel);
        chatTypeLabel.setBounds(20, 65, 75, 20);

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
    private JTextField searchField;
    private JButton searchButton;
    private JLabel chatLabel;
    private JScrollPane scrollPane1;
    private JTable chatGrid;
    private JButton openChat;
    private JButton createChat;
    private JComboBox chatComboBox;
    private JLabel chatTypeLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
