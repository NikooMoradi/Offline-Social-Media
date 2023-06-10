package view.activity;

import java.awt.event.*;
import model.Comment;
import model.Post;
import repo.CommentRepo;
import repo.PostRepo;
import repo.Repository;
import util.DateUtils;
import view.post.showPosts.PostEntity;
import view.post.showPosts.PostList;

import java.awt.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class ActivityList extends JPanel {
    private final Date toDate = DateUtils.getEndOfDay(new Date());
    private Date fromDate = DateUtils.getStartOfDay(new Date());

    public ActivityList() {
        initComponents();
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void today(ActionEvent e) {
        fromDate = DateUtils.getStartOfDay(new Date());
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void yesterday(ActionEvent e) {
        fromDate = DateUtils.getDateBefore(new Date() , 1 , ChronoUnit.DAYS);
        fromDate = DateUtils.getStartOfDay(fromDate);
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void week(ActionEvent e) {
        fromDate = DateUtils.getDateBefore(new Date() , 7 ,ChronoUnit.DAYS);
        fromDate = DateUtils.getStartOfDay(fromDate);
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void month(ActionEvent e) {
        fromDate = DateUtils.getDateBefore(new Date() , 30 , ChronoUnit.DAYS);
        fromDate = DateUtils.getStartOfDay(fromDate);
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void year(ActionEvent e) {
        fromDate = DateUtils.getDateBefore(new Date() , 365 , ChronoUnit.DAYS);
        fromDate = DateUtils.getStartOfDay(fromDate);
        scrollPane1.setViewportView(new ActivityListItem(fromDate , toDate));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        yesterdayButton = new JButton();
        todayButton = new JButton();
        weekButton = new JButton();
        yearButton = new JButton();
        monthButton = new JButton();
        label2 = new JLabel();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
        border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER
        , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font
        .BOLD ,12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (
        new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072"
        .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(null);

        //---- label1 ----
        label1.setText("Activities ");
        add(label1);
        label1.setBounds(10, 5, 100, 30);
        add(scrollPane1);
        scrollPane1.setBounds(0, 85, 425, 215);

        //---- yesterdayButton ----
        yesterdayButton.setText("Yesterday");
        yesterdayButton.addActionListener(e -> yesterday(e));
        add(yesterdayButton);
        yesterdayButton.setBounds(85, 35, yesterdayButton.getPreferredSize().width, 30);

        //---- todayButton ----
        todayButton.setText("Today");
        todayButton.addActionListener(e -> today(e));
        add(todayButton);
        todayButton.setBounds(5, 35, 78, 30);

        //---- weekButton ----
        weekButton.setText("Week");
        weekButton.addActionListener(e -> week(e));
        add(weekButton);
        weekButton.setBounds(175, 35, 78, 30);

        //---- yearButton ----
        yearButton.setText("Year");
        yearButton.addActionListener(e -> year(e));
        add(yearButton);
        yearButton.setBounds(335, 35, 78, 30);

        //---- monthButton ----
        monthButton.setText("Month");
        monthButton.addActionListener(e -> month(e));
        add(monthButton);
        monthButton.setBounds(250, 35, 78, 30);

        //---- label2 ----
        label2.setText("Black for post yellow for comment and blue for reaction ");
        add(label2);
        label2.setBounds(90, 5, 335, 30);

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
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JButton yesterdayButton;
    private JButton todayButton;
    private JButton weekButton;
    private JButton yearButton;
    private JButton monthButton;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
