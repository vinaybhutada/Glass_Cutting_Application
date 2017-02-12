/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import BusinessLayer.DatabaseOperations;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinay bhutada
 */
public class GlassDatabaseTab {
    
    private JPanel glassDatabaseTabPanel = new JPanel();
    private JTable glassDatabaseTable = new JTable();
    private JScrollPane glassTableScrollPane;
    private DatabaseOperations doObj;
    private DefaultTableModel glassDbTableModel = new DefaultTableModel();
    private JButton saveButton = new JButton("Save");
    private JButton discardButton = new JButton("Discard");
    private JButton addButton = new JButton("Add");
    private JPanel glassDbSavePanel = new JPanel();
    private Object emptyRow[];
  
    public GlassDatabaseTab(){
        if(addButton.getActionListeners().length<1){
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {                
                    addButtonActionPerformed();               
                }
            });
        }
        if(saveButton.getActionListeners().length<1){
            saveButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {                
                    try {               
                        saveButtonActionPerformed();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        if(discardButton.getActionListeners().length<1){
            discardButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {                
                    discardButtonActionPerformed();               
                }
            });
        }
    }
    
    public JScrollPane glassDatabaseTablePanel() throws IOException{
        doObj = new DatabaseOperations();
        Object rowData[][] = doObj.readFromFile("C:/PSTApplication/Bin/","glassDatabase.pt");
        Object columnData[] = {"Glass Type","X","Y","Quantity","Clear","Edge L","Edge T","Edge R","Edge B","Rad Gap","Grind Dist"};
        glassDbTableModel.setColumnIdentifiers(columnData);
        for(Object obj[] : rowData){
            glassDbTableModel.addRow(obj);
        }        
        glassDatabaseTable.setModel(glassDbTableModel);
        glassTableScrollPane = new JScrollPane(glassDatabaseTable);
        glassTableScrollPane.setPreferredSize(new Dimension(1000, 200));
        return glassTableScrollPane;
    };
    
    public JPanel glassDbSavePanel(){
        glassDbSavePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        saveButton.setPreferredSize(new Dimension(100,30));
        discardButton.setPreferredSize(new Dimension(100,30));
        addButton.setPreferredSize(new Dimension(100,30));
        glassDbSavePanel.add(addButton);
        glassDbSavePanel.add(saveButton);
        glassDbSavePanel.add(discardButton);
        return glassDbSavePanel;
    }
    
    public void addButtonActionPerformed(){
        emptyRow = new Object[11];
        glassDbTableModel.addRow(emptyRow);
    }
    
    public void saveButtonActionPerformed() throws IOException{
        //Vector tempRow = glassDbTableModel.getDataVector();
        Object rowData[][] = new Object[glassDbTableModel.getRowCount()][glassDbTableModel.getColumnCount()];
        for(int i=0; i<glassDbTableModel.getRowCount(); i++){
            
            for(int j=0; j<glassDbTableModel.getColumnCount(); j++){
                if(glassDbTableModel.getValueAt(i, j).equals("")){
                    rowData[i][j] = 0.0;
                }else{
                    rowData[i][j] = glassDbTableModel.getValueAt(i, j);
                }
                
            }
        }
        doObj = new DatabaseOperations();
        doObj.writeToFile(rowData, "C:/PSTApplication/Bin/","glassDatabase.pt", true);
    }
    
    public void discardButtonActionPerformed(){
        if(glassDatabaseTable.getSelectedRow() != -1){
            int confirmation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this entry?");
            if(confirmation == 0){
                glassDbTableModel.removeRow(glassDatabaseTable.getSelectedRow());
            }
            
        }
        
    }
    
    
    public JPanel glassDatabaseTabPanel() throws IOException{
        glassDatabaseTabPanel.setLayout(new BoxLayout(glassDatabaseTabPanel, BoxLayout.Y_AXIS));
        glassDatabaseTabPanel.add(Box.createHorizontalGlue());
        glassDatabaseTabPanel.add(glassDatabaseTablePanel());
        glassDatabaseTabPanel.add(glassDbSavePanel());
        return glassDatabaseTabPanel;
    }
}
