/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.DatabaseOperations;
import BusinessLayer.PlacingOperation;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author vinay bhutada
 */
public class OrderPlacementTab {

    private JLabel availableOrders = new JLabel("----Available Orders----");
    private JLabel ordersToPlace = new JLabel("----Orders To Place----");
    private JPanel orderPlacementTabPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel sourceOrderPanel = new JPanel();
    private JPanel destOrderPanel = new JPanel();
    private JList sourceList = new JList();
    private JList destList = new JList();
    private DefaultListModel sourceListModel = new DefaultListModel();
    
    private DefaultListModel destListModel = new DefaultListModel();
    private DatabaseOperations doObj = null;
    private Object[] listOfFiles = null;
    private JButton refreshButton = new JButton("Refresh Orders in List");
    private JButton addButton = new JButton("Add->");
    private JButton addAllButton = new JButton("Add All->>");
    private JButton removeButton = new JButton("<-Remove");
    private JButton removeAllButton = new JButton("<<-Remove All");
    private JButton deleteButton = new JButton("<-Delete->");
    private JButton deleteAllButton = new JButton("<<-Delete All->>");
    private JButton startPlacement = new JButton("Start Placement of Orders");
    //private OrderReleaseTab orlObj;

    public OrderPlacementTab() {

        if (refreshButton.getActionListeners().length < 1) {
            refreshButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    refreshButtonActionPerformed();
                }
            });
        }

        if (addButton.getActionListeners().length < 1) {
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    addButtonActionPerformed();
                }
            });
        }

        if (addAllButton.getActionListeners().length < 1) {
            addAllButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    addAllButtonActionPerformed();
                }
            });
        }

        if (removeButton.getActionListeners().length < 1) {
            removeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    removeButtonActionPerformed();
                }
            });
        }

        if (removeAllButton.getActionListeners().length < 1) {
            removeAllButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    removeAllButtonActionPerformed();
                }
            });
        }

        if (deleteButton.getActionListeners().length < 1) {
            deleteButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteButtonActionPerformed();
                }
            });
        }

        if (deleteAllButton.getActionListeners().length < 1) {
            deleteAllButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteAllButtonActionPerformed();
                }
            });
        }

        if (startPlacement.getActionListeners().length < 1) {
            startPlacement.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    startPlacementActionPerformed();
                }
            });
        }

        refreshButtonActionPerformed();

    }

    public void refreshButtonActionPerformed() {
        doObj = new DatabaseOperations();

        File[] allFiles = doObj.fetchAllFiles("C:/PSTApplication/Bin/order_placed/");
        if (allFiles.length > 0) {
            for (File file : allFiles) {
                if (!sourceListModel.contains(file.getName()) && !destListModel.contains(file.getName())) {
                    sourceListModel.addElement(file.getName());
                }
            }
            sourceList.setModel(sourceListModel);

        }
    }

    public void addButtonActionPerformed() {
        List value = sourceList.getSelectedValuesList();
        for (Object obj : value) {
            destListModel.addElement(obj);
        }
        for (Object obj : value) {
            sourceListModel.removeElement(obj);
        }
    }

    ;
    
    public void addAllButtonActionPerformed() {
        Object value[] = sourceListModel.toArray();
        for (Object obj : value) {
            destListModel.addElement(obj);
        }
        for (Object obj : value) {
            sourceListModel.removeElement(obj);
        }
    }

    ;
    
    public void removeButtonActionPerformed() {
        List value = destList.getSelectedValuesList();
        for (Object obj : value) {
            sourceListModel.addElement(obj);
        }
        for (Object obj : value) {
            destListModel.removeElement(obj);
        }
    }

    ;
    
    public void removeAllButtonActionPerformed() {
        Object value[] = destListModel.toArray();
        for (Object obj : value) {
            sourceListModel.addElement(obj);
        }
        for (Object obj : value) {
            destListModel.removeElement(obj);
        }
    }

    ;
    
    public void deleteButtonActionPerformed() {
        List sList = sourceList.getSelectedValuesList();
        List dList = destList.getSelectedValuesList();
        for (Object obj : sList) {
            sourceListModel.removeElement(obj);
        };
        for (Object obj : dList) {
            destListModel.removeElement(obj);
        };
        doObj = new DatabaseOperations();
        doObj.removeFile("C:/PSTApplication/Bin/order_placed/", sList);
        doObj.removeFile("C:/PSTApplication/Bin/order_placed/", dList);
    }

    ;
    
    public void startPlacementActionPerformed(){
        try {
            PlacingOperation plObj = new PlacingOperation();
            String fileName = null;
            List value = destList.getSelectedValuesList();

            for (Object obj : value) {
                System.out.print("\n " + (String) obj + "\n");
                plObj.parseOrderInfo(doObj.readFromFile("C:/PSTApplication/Bin/order_placed/", (String) obj));
                plObj.plotRectangles();
                
                fileName = ((String) obj).split(".ord")[0] + ".rsf";
                doObj.writeToFile(plObj.savePlot(), "C:/PSTApplication/Bin/order_processed/", fileName, true);

            }
            for(Object obj : value){
                destListModel.removeElement(obj);
            }
            
            //doObj.removeFile("C:/PSTApplication/Bin/order_placed/", value);
            
        } catch (Exception e) {
            System.out.print("IO ERROR :: \n \n" + e);
        }

    }

    public void deleteAllButtonActionPerformed() {
    }    ;
    
    public JScrollPane sourceOrderList() throws IOException {
        sourceList.setModel(sourceListModel);
        sourceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        sourceList.setLayoutOrientation(JList.VERTICAL);
        sourceList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(sourceList);
        listScroller.setPreferredSize(new Dimension(150, 300));
        return listScroller;
    }

    public JScrollPane destOrderList() throws IOException {
        destList.setModel(destListModel);
        destList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        destList.setLayoutOrientation(JList.VERTICAL);
        destList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(destList);
        listScroller.setPreferredSize(new Dimension(150, 300));
        return listScroller;
    }

    public JPanel sourceOrderPanel() throws IOException {
        sourceOrderPanel.setLayout(new BoxLayout(sourceOrderPanel, BoxLayout.Y_AXIS));
        availableOrders.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 0));
        availableOrders.setAlignmentY(Component.CENTER_ALIGNMENT);
        sourceList.setAlignmentY(Component.CENTER_ALIGNMENT);
        refreshButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        refreshButton.setMinimumSize(new Dimension(200, 40));
        refreshButton.setMaximumSize(new Dimension(200, 40));
        //refreshButton.setBorder(BorderFactory.createEmptyBorder(20, 60, 10, 10));
        sourceOrderPanel.add(availableOrders);
        sourceOrderPanel.add(sourceOrderList());
        sourceOrderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        sourceOrderPanel.add(refreshButton);
        return sourceOrderPanel;
    }

    public JPanel buttonPanel() {
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        addButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        addAllButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        removeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        removeAllButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        deleteAllButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        ordersToPlace.setAlignmentY(Component.CENTER_ALIGNMENT);

        addButton.setMinimumSize(new Dimension(120, 40));
        addButton.setMaximumSize(new Dimension(120, 40));
        addAllButton.setMinimumSize(new Dimension(120, 40));
        addAllButton.setMaximumSize(new Dimension(120, 40));
        removeButton.setMinimumSize(new Dimension(120, 40));
        removeButton.setMaximumSize(new Dimension(120, 40));
        removeAllButton.setMinimumSize(new Dimension(120, 40));
        removeAllButton.setMaximumSize(new Dimension(120, 40));
        deleteButton.setMaximumSize(new Dimension(120, 40));
        deleteButton.setMaximumSize(new Dimension(120, 40));
        deleteAllButton.setMaximumSize(new Dimension(120, 40));
        deleteAllButton.setMaximumSize(new Dimension(120, 40));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(addAllButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(removeAllButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        // buttonPanel.add(deleteAllButton);
        // buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        return buttonPanel;
    }

    public JPanel destOrderPanel() throws IOException {
        destOrderPanel.setLayout(new BoxLayout(destOrderPanel, BoxLayout.Y_AXIS));
        ordersToPlace.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 0));
        ordersToPlace.setAlignmentY(Component.CENTER_ALIGNMENT);
        destList.setAlignmentY(Component.CENTER_ALIGNMENT);
        startPlacement.setAlignmentY(Component.CENTER_ALIGNMENT);
        //startPlacement.setBorder(BorderFactory.createEmptyBorder(20, 60, 10, 10));
        startPlacement.setMinimumSize(new Dimension(200, 40));
        startPlacement.setMaximumSize(new Dimension(200, 40));
        destOrderPanel.add(ordersToPlace);
        destOrderPanel.add(destOrderList());
        destOrderPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        destOrderPanel.add(startPlacement);
        return destOrderPanel;
    }

    public JPanel orderPlacementTabPanel() throws IOException {
        orderPlacementTabPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        orderPlacementTabPanel.add(sourceOrderPanel());
        orderPlacementTabPanel.add(buttonPanel());
        orderPlacementTabPanel.add(destOrderPanel());
        return orderPlacementTabPanel;
    }
}
