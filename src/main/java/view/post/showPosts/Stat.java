/*
 * Created by JFormDesigner on Wed Aug 03 13:00:18 IRDT 2022
 */

package view.post.showPosts;

import model.Post;
import model.Reaction;
import model.chat.Chat;
import repo.ReactionRepo;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author unknown
 */
public class Stat extends JFrame {
    private final ReactionRepo reactionRepo = new ReactionRepo();
    private final Post post;

    public Stat(Post post) {
        this.setVisible(true);
        this.post = post;
        initComponents();
        setValue();
    }

    private void setValue(){
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        for(int i = 0 ; i < dtm.getRowCount() ; i++ ){
            dtm.removeRow(0);
        }

        String[] header = new String[] {"User" , "Date" , "Type"};

        dtm.setColumnIdentifiers(header);
        reactionGrid.setModel(dtm);

        List<Reaction> reactions = reactionRepo.getAllReactionsOfPost(post.getId());

        for (Reaction reaction : reactions) {
            String type = reaction.getType().toString();
            String username = reaction.getUser().getUsername();
            String date = reaction.getCreateDate().toString();

            dtm.addRow(new Object[] {username , date , type});
        }
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        reactionGrid = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .
            EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing
            . border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,
            java . awt. Color .red ) ,panel1. getBorder () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( )
            { @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )
            throw new RuntimeException( ) ;} } );
            panel1.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(reactionGrid);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(35, 25, 330, 200);

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
    private JScrollPane scrollPane1;
    private JTable reactionGrid;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
