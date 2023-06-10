/*
 * Created by JFormDesigner on Mon Aug 01 11:40:24 IRDT 2022
 */

package view.search;

import java.awt.event.*;
import model.User;
import repo.Repository;
import repo.UserRepo;
import view.general.GeneralView;

import java.awt.*;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author unknown
 */
public class Search extends JPanel {
    private final UserRepo userRepo = new UserRepo();
    private final GeneralView generalView;

    public Search(GeneralView generalView) {
        this.generalView = generalView;
        this.setVisible(true);
        initComponents();
        initializeGrid();
    }

    private void initializeGrid(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for(int i = 0 ; i < dtm.getRowCount() ; i++ ){
            dtm.removeRow(0);
        }

        String[] header = new String[] { "username" , " "};

        dtm.setColumnIdentifiers(header);
        userGrid.setModel(dtm);

        List<User> users = userRepo.list();

        for (User user : users) {
            long id = (user.getId() != null) ? user.getId() : 0L;
            String username = user.getUsername();

            dtm.addRow(new Object[] { username, id});
        }

        userGrid.getColumnModel().getColumn(1).setMinWidth(0);
        userGrid.getColumnModel().getColumn(1).setMaxWidth(0);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dtm);
        userGrid.setRowSorter(sorter);
    }

    private void search(ActionEvent e) {
        String text = searchField.getText();
        final TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) userGrid.getRowSorter();
        if(text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter(text));
            } catch(PatternSyntaxException pse) {
                System.out.println("Bad regex pattern");
            }
        }
    }

    private void userGridMouseClicked(MouseEvent e) {
        int column = 1;
        int row = userGrid.getRowSorter().convertRowIndexToModel(userGrid.getSelectedRow());
        Long value = (Long) userGrid.getModel().getValueAt(row, column);
        Repository.map.put("userId" , value);
    }

    private void showProfile(ActionEvent e) {
        User user = userRepo.get((Long) Repository.map.get("userId"));
        generalView.showProfile(user);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        searchField = new JTextField();
        searchButton = new JButton();
        scrollPane1 = new JScrollPane();
        userGrid = new JTable();
        label1 = new JLabel();
        showProfileButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);
        add(searchField);
        searchField.setBounds(30, 15, 260, 30);

        //---- searchButton ----
        searchButton.setText("search");
        searchButton.addActionListener(e -> search(e));
        add(searchButton);
        searchButton.setBounds(315, 15, 80, 30);

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
        scrollPane1.setBounds(40, 95, 355, 165);

        //---- label1 ----
        label1.setText("Users");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBorder(LineBorder.createBlackLineBorder());
        add(label1);
        label1.setBounds(40, 70, 355, 26);

        //---- showProfileButton ----
        showProfileButton.setText("show profile");
        showProfileButton.addActionListener(e -> showProfile(e));
        add(showProfileButton);
        showProfileButton.setBounds(170, 270, 105, 30);

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
    private JScrollPane scrollPane1;
    private JTable userGrid;
    private JLabel label1;
    private JButton showProfileButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
