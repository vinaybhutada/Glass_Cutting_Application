/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.DatabaseOperations;
import BusinessLayer.GenerateGcode;
import BusinessLayer.GeneratePreview;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author vinay bhutada
 */
public class OrderReleaseTab {

    private JPanel orderReleaseTabPanel = new JPanel();
    private JPanel previewPanel = new JPanel();
    private Object rowDataRead[][];
    private Object rowDataWrite[][];
    private Object rowDataGCode[];
    private DatabaseOperations doObj;
    private GeneratePreview preview;
    private JList processedOrderList = new JList();
    private JPanel releaseJobPanel = new JPanel();
    private DefaultListModel processedListModel = new DefaultListModel();
    private JButton showPreviewButton = new JButton("Show Preview");
    private JButton releaseOrderButton = new JButton("Release");
    private GenerateGcode ggcObj;

    public OrderReleaseTab() {

        if (showPreviewButton.getActionListeners().length < 1) {
            showPreviewButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        showPreviewButtonActionPerformed();
                    } catch (IOException ex) {
                        //Logger.getLogger(OrderReleaseTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        if (releaseOrderButton.getActionListeners().length < 1) {
            releaseOrderButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        releaseOrderButtonActionPerformed();
                    } catch (IOException ex) {
                        Logger.getLogger(OrderReleaseTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        MouseListener mListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    showPreviewButtonActionPerformed();
                } catch (IOException ex) {
                    // Logger.getLogger(OrderReleaseTab.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        processedOrderList.addMouseListener(mListener);

        refreshList();
    }

    public OrderReleaseTab(String filePath, String fileName) throws IOException {

    }

    public void refreshList() {
        doObj = new DatabaseOperations();

        File[] allFiles = doObj.fetchAllFiles("C:/PSTApplication/Bin/order_processed/");
        if (allFiles.length > 0) {
            for (File file : allFiles) {
                if (!processedListModel.contains(file.getName().replace(".rsf", ""))) {
                    processedListModel.addElement(file.getName().replace(".rsf", ""));
                }
            }
            processedOrderList.setModel(processedListModel);

        }
    }

    public void showPreviewButtonActionPerformed() throws IOException {
        previewPanel.removeAll();
        doObj = new DatabaseOperations();
        String fileName = (String) processedOrderList.getSelectedValue()+".rsf";
        rowDataRead = doObj.readFromFile("C:/PSTApplication/Bin/order_processed/", fileName);
        //System.out.println(rowData.length + fileName);
        preview = new GeneratePreview();
        for (Object row[] : rowDataRead) {
            preview.initializeElements(row);
            preview.paintComponent();
        }
        System.out.println(preview.getSurfacePlotter().getComponentCount());
        previewPanel.add(preview.getSurfacePlotter());
        previewPanel.repaint();
        previewPanel.revalidate();
    }

    public void releaseOrderButtonActionPerformed() throws IOException {
        String releaseOrder = (String) processedOrderList.getSelectedValue();
        releaseOrder = releaseOrder + ".rsf";
        ggcObj = new GenerateGcode();
        doObj = new DatabaseOperations();
        rowDataRead = doObj.readFromFile("C:/PSTApplication/Bin/order_processed/", releaseOrder);
        ggcObj.beginSheet();
        for (Object[] row : rowDataRead) {
            ggcObj.initializeElements(row);
            ggcObj.getGcodeForShape();
        }
        ggcObj.endSheet();
        
        rowDataGCode = ggcObj.getFinalGcode().toArray();
        //System.out.println(row);
        doObj.writeToFileForGCode(rowDataGCode, "C:/PSTApplication/Bin/order_released/", releaseOrder.replaceAll(".rsf", ".acu"), true);
        
    }

    public JScrollPane processedOrderList() throws IOException {
        processedOrderList.setModel(processedListModel);
        processedOrderList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        processedOrderList.setLayoutOrientation(JList.VERTICAL);
        processedOrderList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(processedOrderList);
        listScroller.setPreferredSize(new Dimension(150, 300));
        return listScroller;
    }

    public JPanel previewPanel() throws IOException {
        previewPanel.setBackground(Color.BLACK);
        previewPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        previewPanel.setPreferredSize(new Dimension(600, 0));

        return previewPanel;
    }

    public JPanel releaseJobPanel() throws IOException {
        releaseJobPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        releaseJobPanel.add(processedOrderList());
        releaseJobPanel.add(releaseOrderButton);
        return releaseJobPanel;
    }

    public JPanel orderReleaseTabPanel() throws IOException {
        //orderReleaseTabPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        orderReleaseTabPanel.setLayout(new BoxLayout(orderReleaseTabPanel, BoxLayout.X_AXIS));
        orderReleaseTabPanel.add(previewPanel());
        orderReleaseTabPanel.add(releaseJobPanel());
        return orderReleaseTabPanel;
    }
}
