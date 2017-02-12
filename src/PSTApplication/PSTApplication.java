/*
 * This is the Starting point of the application. 
 *Here GUI is invoked and form elements are created by calling subsequent classes
 */
package PSTApplication;

import BusinessLayer.DatabaseOperations;
import BusinessLayer.PlacingOperation;
import PresentationLayer.FillerDatabaseTab;
import PresentationLayer.GlassDatabaseTab;
import PresentationLayer.OrderEntryTab;
import PresentationLayer.OrderPlacementTab;
import PresentationLayer.OrderReleaseTab;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javafx.beans.value.ObservableValue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static java.awt.SystemColor.text;
import java.util.HashMap;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author vinay bhutada
 */
public class PSTApplication extends JFrame {

      private GlassDatabaseTab gdtObj = new GlassDatabaseTab();
      private FillerDatabaseTab fdtObj = new FillerDatabaseTab();
      private OrderEntryTab oetObj = new OrderEntryTab();
      private OrderPlacementTab optObj = new OrderPlacementTab();
      private OrderReleaseTab ortObj = new OrderReleaseTab();

      
      
    public PSTApplication() throws IOException {
        DatabaseOperations doObj = new DatabaseOperations("C:/PSTApplication/Bin/", "glassDatabase.pt");
        //PlacingOperation plObj = new PlacingOperation();
    }

    private void createApplication() throws IOException {
        JFrame frame = new JFrame("PST Application");
        frame.setBounds(10, 10, 1300, 650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set grid layout for the frame
        frame.getContentPane().setLayout(new BorderLayout(5, 5));

        //create order entry tab
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        JPanel glassdatabaseTabPanel = gdtObj.glassDatabaseTabPanel();
        tabbedPane.addTab("Glass Database", null, glassdatabaseTabPanel, "Glass Database Information");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel orderEntryTabPanel = oetObj.orderEntryTabPanel();
        tabbedPane.addTab("Order Entry", null, orderEntryTabPanel, "Enter order summary");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JPanel fillerDatabaseTabPanel = fdtObj.fillerDatabaseTabPanel();
        tabbedPane.addTab("Filler Database", null, fillerDatabaseTabPanel, "Filler Database Information");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        JPanel orderPlacementTabPanel;
        orderPlacementTabPanel = optObj.orderPlacementTabPanel();
        tabbedPane.addTab("Order Placement", null, orderPlacementTabPanel, "Orders Placed");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        JPanel orderReleaseTabPanel = ortObj.orderReleaseTabPanel();
        tabbedPane.addTab("Order Release", null, orderReleaseTabPanel, "Orders Released");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        tabbedPane.addChangeListener(new ChangeListener() {
            // This method is called whenever the selected tab changes
            @Override
            public void stateChanged(ChangeEvent evt) {
                JTabbedPane tabbedPaneListener = (JTabbedPane) evt.getSource();
                // Get current tab
                int tab = tabbedPaneListener.getSelectedIndex();
                if(tab == 3){                    
                    //OrderPlacementTab optObj = new OrderPlacementTab();
                    optObj.refreshButtonActionPerformed();
                }else if(tab == 2){
                    try{
                        fdtObj.refreshTable();
                    }
                    catch(Exception e){
                        System.out.print(" Refresh Failed for Filler table : \t" + e);
                    }
                }else if(tab == 4){
                    ortObj.refreshList();
                }
            }
        });

        //add tabs to the frame
        frame.getContentPane().add(tabbedPane);
    }

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PSTApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PSTApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PSTApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PSTApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PSTApplication pstObj = new PSTApplication();
                    pstObj.createApplication();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
