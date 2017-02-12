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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinay bhutada
 */
public class FillerDatabaseTab {
    private JPanel fillerDatabaseTabPanel = new JPanel();
    private JTable fillerDatabaseTable  = new JTable();;
    private JScrollPane fillerTableScrollPane;
    private DatabaseOperations doObj;
    private DefaultTableModel fillerDbTableModel = new DefaultTableModel();
    private JButton saveButton = new JButton("Save");
    private JButton discardButton = new JButton("Discard");
    private JButton addButton = new JButton("Add");
    private JPanel fillerDbSavePanel = new JPanel();
    private Object emptyRow[];
    
    public FillerDatabaseTab(){
     
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
    
    public void addButtonActionPerformed(){
        emptyRow = new Object[11];
        fillerDbTableModel.addRow(emptyRow);
    }
    
    public void saveButtonActionPerformed() throws IOException{
        //Vector tempRow = glassDbTableModel.getDataVector();
        Object rowData[][] = new Object[fillerDbTableModel.getRowCount()][fillerDbTableModel.getColumnCount()];
        for(int i=0; i<fillerDbTableModel.getRowCount(); i++){
            
            for(int j=0; j<fillerDbTableModel.getColumnCount(); j++){
                if(fillerDbTableModel.getValueAt(i, j).equals("")){
                    rowData[i][j] = 0.0;
                }else{
                    rowData[i][j] = fillerDbTableModel.getValueAt(i, j);
                }
                
            }
        }
        doObj = new DatabaseOperations();
        doObj.writeToFile(rowData, "C:/PSTApplication/Bin/","fillerDatabase.pt", true);
    }
    
    public void discardButtonActionPerformed(){
        if(fillerDatabaseTable.getSelectedRow() != -1){
            int confirmation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this entry?");
            if(confirmation == 0){
                fillerDbTableModel.removeRow(fillerDatabaseTable.getSelectedRow());
            }
        }
        
    }
    
    public JScrollPane fillerDatabaseTablePanel() throws IOException{
        
        doObj = new DatabaseOperations();
        Object rowData[][] = doObj.readFromFile("C:/PSTApplication/Bin/","fillerDatabase.pt");
        
        //Object rowData[][] = {{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}};
        Object columnData[] = {"Customer", "Group", "Shape", "Quantity", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "Rack", "Slot", "Glass Type", "Info1", "Info2"};
        fillerDbTableModel.setColumnIdentifiers(columnData);
        for(Object obj[] : rowData){
            fillerDbTableModel.addRow(obj);
        }    
        fillerDatabaseTable.setModel(fillerDbTableModel);
        fillerTableScrollPane = new JScrollPane(fillerDatabaseTable);
        fillerTableScrollPane.setPreferredSize(new Dimension(1000, 200));
        return fillerTableScrollPane;
    };
    
    public JPanel fillerDbSavePanel(){
        fillerDbSavePanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        saveButton.setPreferredSize(new Dimension(100,30));
        discardButton.setPreferredSize(new Dimension(100,30));
        addButton.setPreferredSize(new Dimension(100,30));
        fillerDbSavePanel.add(addButton);
        fillerDbSavePanel.add(saveButton);
        fillerDbSavePanel.add(discardButton);
        return fillerDbSavePanel;
    }
    
    public JPanel fillerDatabaseTabPanel() throws IOException{
        fillerDatabaseTabPanel.setLayout(new BoxLayout(fillerDatabaseTabPanel, BoxLayout.Y_AXIS));
        fillerDatabaseTabPanel.add(Box.createHorizontalGlue());
        fillerDatabaseTabPanel.add(fillerDatabaseTablePanel());
        fillerDatabaseTabPanel.add(fillerDbSavePanel());
        return fillerDatabaseTabPanel;
    }
    
    public void refreshTable() throws IOException{
        int rowCount = fillerDbTableModel.getRowCount();
  
        for(int i=rowCount-1; i>=0; i--){  
            fillerDbTableModel.removeRow(i);
        } 
        doObj = new DatabaseOperations();
        Object rowData[][] = doObj.readFromFile("C:/PSTApplication/Bin/","fillerDatabase.pt");
        
        //Object rowData[][] = {{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}};
        Object columnData[] = {"Customer", "Group", "Shape", "Quantity", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "Rack", "Slot", "Glass Type", "Info1", "Info2"};
        fillerDbTableModel.setColumnIdentifiers(columnData);
        for(Object obj[] : rowData){
            fillerDbTableModel.addRow(obj);
        }    
        this.fillerDatabaseTable.setModel(fillerDbTableModel);
    }
}

