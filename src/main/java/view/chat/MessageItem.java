/*
 * Created by JFormDesigner on Thu Aug 04 12:38:35 IRDT 2022
 */

package view.chat;

import model.chat.Message;
import repo.Repository;

import javax.swing.*;
import java.awt.*;

/**
 * @author unknown
 */
public class MessageItem extends JPanel {

    private Message message;

    public MessageItem(Message message) {
        this.setVisible(true);
        this.message = message;
        initComponents();
        fistInit();
        secondInit();
    }

    private void fistInit() {
        youLabel.setVisible(false);
        otherLabel.setVisible(false);
        youTextArea.setVisible(false);
        otherTextArea.setVisible(false);
        youScrollPane.setVisible(false);
        otherScrollPane.setVisible(false);
    }

    private void secondInit() {
        if (message.getSender().equals(Repository.currentUser)) {
            youLabel.setVisible(true);
            youScrollPane.setVisible(true);
            youTextArea.append(message.getMessage());
            youTextArea.setVisible(true);
        } else {
            otherLabel.setText(message.getSender().getUsername());
            otherLabel.setVisible(true);
            otherScrollPane.setVisible(true);
            otherTextArea.append(message.getMessage());
            otherTextArea.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        youLabel = new JLabel();
        otherLabel = new JLabel();
        youScrollPane = new JScrollPane();
        youTextArea = new JTextArea();
        otherScrollPane = new JScrollPane();
        otherTextArea = new JTextArea();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
                (0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER, javax.swing.border
                .TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font.BOLD, 12), java.awt
                .Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void
            propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException()
                        ;
            }
        });
        setLayout(null);

        //---- youLabel ----
        youLabel.setText("You");
        add(youLabel);
        youLabel.setBounds(10, 5, 165, 25);

        //---- otherLabel ----
        otherLabel.setText("You");
        otherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(otherLabel);
        otherLabel.setBounds(180, 5, 165, 25);

        //======== youScrollPane ========
        {
            youScrollPane.setViewportView(youTextArea);
        }
        add(youScrollPane);
        youScrollPane.setBounds(10, 35, 165, 29);

        //======== otherScrollPane ========
        {
            otherScrollPane.setViewportView(otherTextArea);
        }
        add(otherScrollPane);
        otherScrollPane.setBounds(180, 35, 165, 30);

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
    private JLabel youLabel;
    private JLabel otherLabel;
    private JScrollPane youScrollPane;
    private JTextArea youTextArea;
    private JScrollPane otherScrollPane;
    private JTextArea otherTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
