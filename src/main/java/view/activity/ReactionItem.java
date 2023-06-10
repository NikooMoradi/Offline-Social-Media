/*
 * Created by JFormDesigner on Wed Aug 03 16:59:11 IRDT 2022
 */

package view.activity;

import model.Reaction;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class ReactionItem extends JPanel {
    private final Reaction reaction;

    public ReactionItem(Reaction reaction) {
        initComponents();
        this.reaction = reaction;
        setValues();
    }

    private void setValues(){
        reactionText.append("Post id: " + reaction.getReactionTo().getId() + "\nType: " + reaction.getType() + "\n");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        reactionText = new JTextArea();

        //======== this ========
        setBorder(new LineBorder(Color.blue));
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax.
        swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border
        . TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog"
        ,java .awt .Font .BOLD ,12 ), java. awt. Color. red) , getBorder
        ( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java
        .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException
        ( ); }} );
        setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(reactionText);
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
    private JTextArea reactionText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
