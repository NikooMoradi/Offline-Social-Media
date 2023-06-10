/*
 * Created by JFormDesigner on Wed Aug 03 15:57:58 IRDT 2022
 */

package view.activity;

import model.Comment;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class CommentItem extends JPanel {
    private final Comment comment;

    public CommentItem(Comment comment) {
        this.comment = comment;
        initComponents();
        setValues();
    }

    private void setValues(){
        commentText.append("Post id: " + comment.getPost().getId() + "\nText: " + comment.getText() + "\n");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        commentText = new JTextArea();

        //======== this ========
        setBorder(new LineBorder(Color.yellow));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
        swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border
        . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067"
        ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
        .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
        setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(commentText);
        }
        add(scrollPane1);
        scrollPane1.setBounds(15, 15, 395, 90);

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
    private JScrollPane scrollPane1;
    private JTextArea commentText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
