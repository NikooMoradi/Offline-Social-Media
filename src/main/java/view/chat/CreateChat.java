package view.chat;

import model.User;
import model.chat.Chat;
import model.chat.Members;
import repo.ChatRepo;
import repo.FollowRepo;
import repo.Repository;
import repo.UserRepo;
import util.ChatType;
import view.general.GeneralView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

public class CreateChat extends JPanel {

    private final UserRepo userRepo = new UserRepo();
    private final ChatRepo chatRepo = new ChatRepo();
    private final FollowRepo followRepo = new FollowRepo();
    private final User user;
    private final GeneralView generalView;
    private Object typeSelected;
    private int row;
    private Chat chat;

    public CreateChat(User user, GeneralView generalView) {
        this.setVisible(true);
        this.user = user;
        this.generalView = generalView;
        initComponents();
        initCreateChatView();
    }
    public CreateChat(User user, GeneralView generalView, Chat chat) {
        this.setVisible(true);
        this.user = user;
        this.generalView = generalView;
        this.chat = chat;
        initComponents();
        initEditChatView();
    }

    private void initEditChatView() {
        groupType.setVisible(false);
        label1.setVisible(false);
        initUserGrid();
        initMembersGrid();
        groupName.setText(chat.getChatName());
        createBtn.setText("Done");
    }

    private void initCreateChatView() {
        label4.setVisible(false);
        groupName.setVisible(false);
        addBtn.setVisible(false);
        rmvBtn.setVisible(false);
        label3.setVisible(false);
        members.setVisible(false);
        scrollPane2.setVisible(false);

        groupType.addItem(ChatType.Single);
        groupType.addItem(ChatType.Group);

        initUserGrid();
    }

    private void initMembersGrid() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(0);
        }

        String[] header = new String[]{"username", " "};

        dtm.setColumnIdentifiers(header);
        members.setModel(dtm);

        List<Members> membersList = chatRepo.getAllMembers(chat);

        for (Members member : membersList) {
            long id = (member.getMember().getId() != null) ? member.getMember().getId() : 0L;
            String username = member.getMember().getUsername();

            dtm.addRow(new Object[]{username, id});
        }

        members.getColumnModel().getColumn(1).setMinWidth(0);
        members.getColumnModel().getColumn(1).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        members.setRowSorter(sorter);
    }

    private void initUserGrid() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(0);
        }

        String[] header = new String[]{"username", " "};

        dtm.setColumnIdentifiers(header);
        userGrid.setModel(dtm);

        List<User> users = followRepo.getFollowers(user);

        for (User user : users) {
            long id = (user.getId() != null) ? user.getId() : 0L;
            String username = user.getUsername();

            dtm.addRow(new Object[]{username, id});
        }

        userGrid.getColumnModel().getColumn(1).setMinWidth(0);
        userGrid.getColumnModel().getColumn(1).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        userGrid.setRowSorter(sorter);
    }

    private void search(ActionEvent e) {
        String text = searchField.getText();
        final TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) userGrid.getRowSorter();
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

    private void userGridMouseClicked(MouseEvent e) {
        row = userGrid.getRowSorter().convertRowIndexToModel(userGrid.getSelectedRow());
        Long id = (Long) userGrid.getModel().getValueAt(row, 1);
        String username = (String) userGrid.getModel().getValueAt(row, 0);
        Repository.map.put("userId", id);
        Repository.map.put("username", username);
    }

    private void membersMouseClicked(MouseEvent e) {
        row = members.getRowSorter().convertRowIndexToModel(members.getSelectedRow());
    }

    private void addBtn(ActionEvent e) {
        DefaultTableModel dtm = (DefaultTableModel) members.getModel();

        dtm.addRow(new Object[]{Repository.map.get("username"), Repository.map.get("userId")});
    }

    private void rmvBtn(ActionEvent e) {
        DefaultTableModel dtm = (DefaultTableModel) members.getModel();

        dtm.removeRow(row);
    }

    private void cancelBtn(ActionEvent e) {
        if (Objects.isNull(chat))
            generalView.directButton();
        else
            generalView.openChat(chat, user);
    }

    private void createBtn(ActionEvent e) {

        List<User> membersList = new ArrayList<>();

        if (Objects.isNull(chat)) {
            if (typeSelected.toString().equalsIgnoreCase("Group")) {
                DefaultTableModel dtm = (DefaultTableModel) members.getModel();

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    membersList.add(userRepo.get((Long) userGrid.getModel().getValueAt(i, 1)));
                }

                String nameGroup = groupName.getText();

                chatRepo.createChat(user, membersList, ChatType.Group, nameGroup);

            } else {
                membersList.add(user);
                membersList.add(userRepo.get((Long) Repository.map.get("userId")));

                chatRepo.createChat(user, membersList, ChatType.Single, null);

            }
            generalView.directButton();
        } else {
            DefaultTableModel dtm = (DefaultTableModel) members.getModel();

            for (int i = 0; i < dtm.getRowCount(); i++) {
                membersList.add(userRepo.get((Long) userGrid.getModel().getValueAt(i, 1)));
            }

            String nameGroup = groupName.getText();

            chat = chatRepo.updateChat(chat.getId(), membersList, nameGroup);

            generalView.openChat(chat, user);
        }
    }

    private void groupTypeItemStateChanged(ItemEvent e) {
        typeSelected = groupType.getSelectedItem();
        if (typeSelected.toString().equalsIgnoreCase("Group")) {

            DefaultTableModel dtm = new DefaultTableModel(0, 0);
            for (int i = 0; i < dtm.getRowCount(); i++) {
                dtm.removeRow(0);
            }

            String[] header = new String[]{"username", " "};

            dtm.setColumnIdentifiers(header);
            members.setModel(dtm);


            dtm.addRow(new Object[]{user.getUsername(), user.getId()});

            members.getColumnModel().getColumn(1).setMinWidth(0);
            members.getColumnModel().getColumn(1).setMaxWidth(0);

            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
            members.setRowSorter(sorter);

            label4.setVisible(true);
            groupName.setVisible(true);
            addBtn.setVisible(true);
            rmvBtn.setVisible(true);
            label3.setVisible(true);
            members.setVisible(true);
            scrollPane2.setVisible(true);
        } else {
            label4.setVisible(false);
            groupName.setVisible(false);
            addBtn.setVisible(false);
            rmvBtn.setVisible(false);
            label3.setVisible(false);
            members.setVisible(false);
            scrollPane2.setVisible(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        groupType = new JComboBox();
        searchField = new JTextField();
        search = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        userGrid = new JTable();
        addBtn = new JButton();
        rmvBtn = new JButton();
        scrollPane2 = new JScrollPane();
        members = new JTable();
        label4 = new JLabel();
        groupName = new JTextField();
        cancelBtn = new JButton();
        createBtn = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                .EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder.CENTER, javax
                .swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD,
                12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans
                .PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062ord\u0065r".equals(e.
                        getPropertyName())) throw new RuntimeException();
            }
        });
        setLayout(null);

        //---- label1 ----
        label1.setText("Chat Type");
        add(label1);
        label1.setBounds(new Rectangle(new Point(24, 28), label1.getPreferredSize()));

        //---- groupType ----
        groupType.addItemListener(e -> groupTypeItemStateChanged(e));
        add(groupType);
        groupType.setBounds(93, 21, 163, 30);
        add(searchField);
        searchField.setBounds(14, 72, 276, 30);

        //---- search ----
        search.setText("Search");
        search.addActionListener(e -> search(e));
        add(search);
        search.setBounds(306, 72, search.getPreferredSize().width, 30);

        //---- label2 ----
        label2.setText("Users");
        add(label2);
        label2.setBounds(new Rectangle(new Point(45, 130), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("Members");
        add(label3);
        label3.setBounds(new Rectangle(new Point(280, 130), label3.getPreferredSize()));

        //======== scrollPane1 ========
        {

            //---- userGrid ----
            userGrid.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    userGridMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(userGrid);
        }
        add(scrollPane1);
        scrollPane1.setBounds(14, 155, 91, 105);

        //---- addBtn ----
        addBtn.setText("Add");
        addBtn.addActionListener(e -> addBtn(e));
        add(addBtn);
        addBtn.setBounds(120, 170, addBtn.getPreferredSize().width, 30);

        //---- rmvBtn ----
        rmvBtn.setText("Remove");
        rmvBtn.addActionListener(e -> rmvBtn(e));
        add(rmvBtn);
        rmvBtn.setBounds(120, 200, rmvBtn.getPreferredSize().width, 30);

        //======== scrollPane2 ========
        {

            //---- members ----
            members.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    membersMouseClicked(e);
                }
            });
            scrollPane2.setViewportView(members);
        }
        add(scrollPane2);
        scrollPane2.setBounds(220, 155, 165, 105);

        //---- label4 ----
        label4.setText("Group Name");
        add(label4);
        label4.setBounds(new Rectangle(new Point(10, 295), label4.getPreferredSize()));
        add(groupName);
        groupName.setBounds(85, 285, 163, 30);

        //---- cancelBtn ----
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(e -> cancelBtn(e));
        add(cancelBtn);
        cancelBtn.setBounds(255, 290, cancelBtn.getPreferredSize().width, 30);

        //---- createBtn ----
        createBtn.setText("Create");
        createBtn.addActionListener(e -> createBtn(e));
        add(createBtn);
        createBtn.setBounds(340, 290, createBtn.getPreferredSize().width, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
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
    private JLabel label1;
    private JComboBox groupType;
    private JTextField searchField;
    private JButton search;
    private JLabel label2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTable userGrid;
    private JButton addBtn;
    private JButton rmvBtn;
    private JScrollPane scrollPane2;
    private JTable members;
    private JLabel label4;
    private JTextField groupName;
    private JButton cancelBtn;
    private JButton createBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
