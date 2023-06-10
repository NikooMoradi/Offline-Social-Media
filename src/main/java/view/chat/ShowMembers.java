/*
 * Created by JFormDesigner on Thu Aug 04 13:57:48 IRDT 2022
 */

package view.chat;

import model.User;
import model.chat.Chat;
import model.chat.Members;
import repo.ChatRepo;
import repo.Repository;
import repo.UserRepo;
import view.general.GeneralView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class ShowMembers extends JPanel {

    private final ChatRepo chatRepo = new ChatRepo();
    private final GeneralView generalView;
    private Chat chat;

    public ShowMembers(Chat chat, GeneralView generalView) {
        this.setVisible(true);
        this.chat = chat;
        this.generalView = generalView;
        initComponents();
        intiMembersGrid();
    }

    private void backBtn(ActionEvent e) {
        generalView.openChat(chat, Repository.currentUser);
    }

    private void intiMembersGrid() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            dtm.removeRow(0);
        }

        String[] header = new String[]{"username", " "};

        dtm.setColumnIdentifiers(header);
        membersGrid.setModel(dtm);

        List<Members> membersList = chatRepo.getAllMembers(chat);

        for (Members member : membersList) {
            long id = (member.getMember().getId() != null) ? member.getMember().getId() : 0L;
            String username = member.getMember().getUsername();

            dtm.addRow(new Object[]{username, id});
        }

        membersGrid.getColumnModel().getColumn(1).setMinWidth(0);
        membersGrid.getColumnModel().getColumn(1).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        membersGrid.setRowSorter(sorter);
    }

    private void membersGridMouseClicked(MouseEvent e) {
        int row = membersGrid.getRowSorter().convertRowIndexToModel(membersGrid.getSelectedRow());
        Long value = (Long) membersGrid.getModel().getValueAt(row, 1);
        Repository.map.put("userId", value);
    }

    private void showProfileBtn(ActionEvent e) {
        User user = new UserRepo().get((Long) Repository.map.get("userId"));
        generalView.showProfile(user);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        backBtn = new JButton();
        membersScrollPane = new JScrollPane();
        membersGrid = new JTable();
        showProfileBtn = new JButton();
        members = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
                EmptyBorder(0, 0, 0, 0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax.swing.border.TitledBorder.CENTER, javax.swing
                .border.TitledBorder.BOTTOM, new java.awt.Font("Dialo\u0067", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("borde\u0072".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });
        setLayout(null);

        //---- backBtn ----
        backBtn.setText("Back");
        backBtn.addActionListener(e -> backBtn(e));
        add(backBtn);
        backBtn.setBounds(new Rectangle(new Point(15, 10), backBtn.getPreferredSize()));

        //======== membersScrollPane ========
        {

            //---- membersGrid ----
            membersGrid.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    membersGridMouseClicked(e);
                }
            });
            membersScrollPane.setViewportView(membersGrid);
        }
        add(membersScrollPane);
        membersScrollPane.setBounds(15, 70, 365, 180);

        //---- showProfileBtn ----
        showProfileBtn.setText("Show Profile");
        showProfileBtn.addActionListener(e -> showProfileBtn(e));
        add(showProfileBtn);
        showProfileBtn.setBounds(255, 255, 125, showProfileBtn.getPreferredSize().height);

        //---- members ----
        members.setText("Members");
        members.setHorizontalAlignment(SwingConstants.CENTER);
        add(members);
        members.setBounds(20, 50, 350, members.getPreferredSize().height);

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
    private JButton backBtn;
    private JScrollPane membersScrollPane;
    private JTable membersGrid;
    private JButton showProfileBtn;
    private JLabel members;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
