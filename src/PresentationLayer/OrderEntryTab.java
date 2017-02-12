/*
 * This class creates OrderEntryTab panel elements and initializes them to there defaul values.
 */
package PresentationLayer;

import BusinessLayer.DatabaseOperations;
import BusinessLayer.FormElementsManager;
import BusinessLayer.PopulateOrderEntryTable;
import PSTApplication.PSTApplication;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vinay bhutada
 */
public class OrderEntryTab {

    private JLabel width = new JLabel("Width");
    private JLabel height = new JLabel("Height");
    private JLabel base = new JLabel("Base");
    private JLabel radius = new JLabel("Radius");
    private JLabel top = new JLabel("Top");
    private JLabel diameter = new JLabel("Diameter");
    private JLabel offset = new JLabel("Offset");
    private JLabel length = new JLabel("Length");
    private JLabel sides = new JLabel("Sides");
    private JLabel majorDia = new JLabel("MajorDia");
    private JLabel minorDia = new JLabel("MinorDia");
    private JLabel OAH = new JLabel("OAH");
    private JLabel x1 = new JLabel("X1");
    private JLabel x2 = new JLabel("X2");
    private JLabel y1 = new JLabel("Y1");
    private JLabel y2 = new JLabel("Y2");
    private JLabel y3 = new JLabel("Y3");
    private JLabel lHeight = new JLabel("L.Height");
    private JLabel rHeight = new JLabel("R.Height");
    private JLabel clip = new JLabel("Clip");
    private JLabel tl = new JLabel("TL");
    private JLabel tr = new JLabel("TR");
    private JLabel bl = new JLabel("BL");
    private JLabel br = new JLabel("BR");
    private JLabel tls = new JLabel("TLS");
    private JLabel bls = new JLabel("BLS");
    private JLabel trs = new JLabel("TRS");
    private JLabel brs = new JLabel("BRS");
    private JLabel code0 = new JLabel("Code0");
    private JLabel code1 = new JLabel("Code1");
    private JLabel code2 = new JLabel("Code2");
    private JLabel code4 = new JLabel("Code4");
    private JLabel r1 = new JLabel("R1");
    private JLabel r2 = new JLabel("R2");
    private JLabel angle = new JLabel("Angle");
    private JLabel qty = new JLabel("Quantity");
    private JLabel customer = new JLabel("Customer");
    private JLabel imageLabel;
    private JLabel info1 = new JLabel("Info1");
    private JLabel info2 = new JLabel("Info2");
    private JLabel glassType = new JLabel("Glass Type");
    private JLabel code = new JLabel("Code");

    private JTextField widthText = new JTextField(20);
    private JTextField heightText = new JTextField(20);
    private JTextField baseText = new JTextField(20);
    private JTextField radiusText = new JTextField(20);
    private JTextField topText = new JTextField(20);
    private JTextField diameterText = new JTextField(20);
    private JTextField offsetText = new JTextField(20);
    private JTextField lengthText = new JTextField(20);
    private JTextField sidesText = new JTextField(20);
    private JTextField majorDiaText = new JTextField(20);
    private JTextField minorDiaText = new JTextField(20);
    private JTextField OAHText = new JTextField(20);
    private JTextField x1Text = new JTextField(20);
    private JTextField x2Text = new JTextField(20);
    private JTextField y1Text = new JTextField(20);
    private JTextField y2Text = new JTextField(20);
    private JTextField y3Text = new JTextField(20);
    private JTextField lHeightText = new JTextField(20);
    private JTextField rHeightText = new JTextField(20);
    private JTextField clipText = new JTextField(20);
    private JTextField tlText = new JTextField(20);
    private JTextField trText = new JTextField(20);
    private JTextField blText = new JTextField(20);
    private JTextField brText = new JTextField(20);
    private JTextField tlsText = new JTextField(20);
    private JTextField blsText = new JTextField(20);
    private JTextField trsText = new JTextField(20);
    private JTextField brsText = new JTextField(20);
    private JTextField code0Text = new JTextField(20);
    private JTextField code1Text = new JTextField(20);
    private JTextField code2Text = new JTextField(20);
    private JTextField code4Text = new JTextField(20);
    private JTextField r1Text = new JTextField(20);
    private JTextField r2Text = new JTextField(20);
    private JTextField angleText = new JTextField(20);
    private JTextField qtyText = new JTextField(20);
    private JTextField customerText = new JTextField(20);
    private JTextField info1Text = new JTextField(20);
    private JTextField info2Text = new JTextField(20);
    private JTextField glassTypeText = new JTextField(20);

    //private JComboBox shapeComboBox;
    private JPanel orderEntryTabPanel = new JPanel();
    private JPanel shapePreview = new JPanel();
    private JTable orderEntryTablePanel = new JTable();
    private JPanel orderEntrySavePanel = new JPanel();
    private DefaultTableModel orderEntryTableModel = new DefaultTableModel();
    private JButton submitButton = new JButton("Submit");
    private JButton clearButton = new JButton("Clear");
    private JButton saveButton = new JButton("Save");
    private JButton discardButton = new JButton("Discard");
    private JButton saveAsButton = new JButton("Save As");
    private JButton openButton = new JButton("Open");
    private JButton addToFillerButton = new JButton("Add to Filler");
    private JComboBox<Object> codeComboBox;

    private ArrayList<JTextField> listOfTextFields;
    private ImageIcon imagePreview;
    private static int count = -1;

    private DatabaseOperations doObj = null;
    private OrderPlacementTab optObj = null;

    private String errorMessage = null;
    private String saveAs = null;

    public OrderEntryTab() {
        String[] codeComboValues = {"code0","code1","code2","code4"};
        codeComboBox = new JComboBox<Object>(codeComboValues);

        if (count == -1) {
            doObj = new DatabaseOperations();
            count = doObj.getFilesCount("C:/PSTApplication/Bin/order_placed/");
            //count += doObj.getFilesCount("src/DatabaseLayer/database_files/orders_processed/");
            count++;
        }

        if (addToFillerButton.getActionListeners().length < 1) {
            addToFillerButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        addToFillerButtonActionPerformed();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        if (clearButton.getActionListeners().length < 1) {
            clearButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    clearButtonActionPerformed();
                }
            });
        }

        if (saveButton.getActionListeners().length < 1) {
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

        if (discardButton.getActionListeners().length < 1) {
            discardButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    discardButtonActionPerformed();
                }
            });
        }

        if (openButton.getActionListeners().length < 1) {
            openButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        openButtonActionPerformed();
                    } catch (IOException ex) {
                        //Logger.getLogger(OrderEntryTab.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        if (saveAsButton.getActionListeners().length < 1) {
            saveAsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        saveAsButtonActionPerformed();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

    }

    ;

       
    public JPanel createFormElements() {

        JPanel createdElements = new JPanel();
        GroupLayout layout = new GroupLayout(createdElements);
        createdElements.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel chooseShapeLabel = new JLabel("Choose Shape To Score");
        String[] shapeList = {"-Choose Shape-", "Rectangle", "Triangle", "Radius Rectangle", "Trapezoid", "Circle", "Parallelogram", "Polygon", "Single Circle Top", "Double Circle Top", "Semi-Circle", "Ellipse", "Arch", "Gothic", "Semi-Ellipse", "Shape 13", "Double Triangle Top", "Single Triangle Top", "Rake", "Clipped Corner", "Quadrilateral", "Donut", "Quarter Arc", "Extended Quarter Arc", "Half Arc", "Extended Half Arc"};
        JComboBox shapeComboBox = new JComboBox(shapeList);
        shapeComboBox.setPreferredSize(new Dimension(200, 30));
        shapeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shapeComboBoxActionPerformed(shapeComboBox.getSelectedItem().toString());
            }
        });

        if (getSubmitButton().getActionListeners().length < 1) {
            getSubmitButton().addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        submitButtonActionPerformed(shapeComboBox.getSelectedItem().toString());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(chooseShapeLabel)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(getWidth())
                                        .addComponent(getTop())
                                        .addComponent(getSides())
                                        .addComponent(getX1())
                                        .addComponent(getY3())
                                        .addComponent(getTl())
                                        .addComponent(getTls())
                                        .addComponent(getQty())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getWidthText())
                                        .addComponent(getTopText())
                                        .addComponent(getSidesText())
                                        .addComponent(getX1Text())
                                        .addComponent(getY3Text())
                                        .addComponent(getTlText())
                                        .addComponent(getTlsText())
                                        .addComponent(getQtyText())
                                )
                        )
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(shapeComboBox)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(getHeight())
                                        .addComponent(getDiameter())
                                        .addComponent(getMajorDia())
                                        .addComponent(getX2())
                                        .addComponent(getlHeight())
                                        .addComponent(getTr())
                                        .addComponent(getBls())
                                        .addComponent(getGlassType())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getHeightText())
                                        .addComponent(getDiameterText())
                                        .addComponent(getMajorDiaText())
                                        .addComponent(getX2Text())
                                        .addComponent(getlHeightText())
                                        .addComponent(getTrText())
                                        .addComponent(getBlsText())
                                        .addComponent(getGlassTypeText()) //, GroupLayout.PREFERRED_SIZE,50,GroupLayout.PREFERRED_SIZE
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(getBase())
                                        .addComponent(getOffset())
                                        .addComponent(getMinorDia())
                                        .addComponent(getY1())
                                        .addComponent(getrHeight())
                                        .addComponent(getBl())
                                        .addComponent(getTrs())
                                        .addComponent(getCustomer())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getBaseText())
                                        .addComponent(getOffsetText())
                                        .addComponent(getMinorDiaText())
                                        .addComponent(getY1Text())
                                        .addComponent(getrHeightText())
                                        .addComponent(getBlText())
                                        .addComponent(getTrsText())
                                        .addComponent(getCustomerText())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(getRadius())
                                        .addComponent(getLength())
                                        .addComponent(getOAH())
                                        .addComponent(getY2())
                                        .addComponent(getClip())
                                        .addComponent(getBr())
                                        .addComponent(getBrs())
                                        .addComponent(getInfo1())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getRadiusText())
                                        .addComponent(getLengthText())
                                        .addComponent(getOAHText())
                                        .addComponent(getY2Text())
                                        .addComponent(getClipText())
                                        .addComponent(getBrText())
                                        .addComponent(getBrsText())
                                        .addComponent(getInfo1Text())
                                        .addComponent(getSubmitButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(code)
                                        .addComponent(getR1())
                                        .addComponent(getR2())
                                        .addComponent(getAngle())
                                        .addComponent(getInfo2())
                                )
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getCodeComboBox())
                                        .addComponent(getR1Text())
                                        .addComponent(getR2Text())
                                        .addComponent(getAngleText())
                                        .addComponent(getInfo2Text())
                                        .addComponent(getClearButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                )
                        )
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseShapeLabel)
                        .addComponent(shapeComboBox)
                )
                //.addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getWidth())
                        .addComponent(getWidthText())
                        .addComponent(getHeight())
                        .addComponent(getHeightText())
                        .addComponent(getBase())
                        .addComponent(getBaseText())
                        .addComponent(getRadius())
                        .addComponent(getRadiusText())
                        .addComponent(code)
                        .addComponent(getCodeComboBox())
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getTop())
                        .addComponent(getTopText())
                        .addComponent(getDiameter())
                        .addComponent(getDiameterText())
                        .addComponent(getOffset())
                        .addComponent(getOffsetText())
                        .addComponent(getLength())
                        .addComponent(getLengthText())
                        .addComponent(getR1())
                        .addComponent(getR1Text())
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getSides())
                        .addComponent(getSidesText())
                        .addComponent(getMajorDia())
                        .addComponent(getMajorDiaText())
                        .addComponent(getMinorDia())
                        .addComponent(getMinorDiaText())
                        .addComponent(getOAH())
                        .addComponent(getOAHText())
                        .addComponent(getR2())
                        .addComponent(getR2Text())
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getX1())
                        .addComponent(getX1Text())
                        .addComponent(getX2())
                        .addComponent(getX2Text())
                        .addComponent(getY1())
                        .addComponent(getY1Text())
                        .addComponent(getY2())
                        .addComponent(getY2Text())
                        .addComponent(getAngle())
                        .addComponent(getAngleText())
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getY3())
                        .addComponent(getY3Text())
                        .addComponent(getlHeight())
                        .addComponent(getlHeightText())
                        .addComponent(getrHeight())
                        .addComponent(getrHeightText())
                        .addComponent(getClip())
                        .addComponent(getClipText())
                        
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getTl())
                        .addComponent(getTlText())
                        .addComponent(getTr())
                        .addComponent(getTrText())
                        .addComponent(getBl())
                        .addComponent(getBlText())
                        .addComponent(getBr())
                        .addComponent(getBrText())
                        
                )
                // .addGap(7,7,7)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getTls())
                        .addComponent(getTlsText())
                        .addComponent(getBls())
                        .addComponent(getBlsText())
                        .addComponent(getTrs())
                        .addComponent(getTrsText())
                        .addComponent(getBrs())
                        .addComponent(getBrsText())
                        
                )
                // .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getQty())
                        .addComponent(getQtyText())
                        .addComponent(getGlassType())
                        .addComponent(getGlassTypeText())
                        .addComponent(getCustomer())
                        .addComponent(getCustomerText())
                        .addComponent(getInfo1())
                        .addComponent(getInfo1Text())
                        .addComponent(getInfo2())
                        .addComponent(getInfo2Text())
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(getSubmitButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(getClearButton(), GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        return createdElements;
    }

    public JPanel orderEntryFormPanel() {
        JPanel orderEntryFormPanel = new JPanel();
        orderEntryFormPanel.setLayout(new BoxLayout(orderEntryFormPanel, BoxLayout.X_AXIS));
        orderEntryFormPanel.setPreferredSize(new Dimension(0, 400));
        orderEntryFormPanel.setBorder(BorderFactory.createTitledBorder("Please fill in the form below"));

        JPanel formElements = createFormElements();
        formElements.setBorder(BorderFactory.createTitledBorder(""));
        formElements.setPreferredSize(new Dimension(100, 0));

        getShapePreview().setBackground(Color.WHITE);
        getShapePreview().setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
        getShapePreview().setPreferredSize(new Dimension(0, 300));
        setImageLabel("Choose shape to preview image of that shape");
        getShapePreview().add(getImageLabel());

        orderEntryFormPanel.add(formElements);
        orderEntryFormPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        orderEntryFormPanel.add(getShapePreview());

        return orderEntryFormPanel;
    }

    ;
    
    public JScrollPane orderEntryTablePanel() {
        Object rowData[][] = {{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}};
        String columnData[] = {"Customer", "Group", "Shape", "Quantity", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "Rack", "Slot", "Glass Type", "Info1", "Info2"};
        getOrderEntryTableModel().setColumnIdentifiers(columnData);
        getOrderEntryTablePanel().setModel(getOrderEntryTableModel());
        JScrollPane tableScrollPane = new JScrollPane(getOrderEntryTablePanel());
        tableScrollPane.setPreferredSize(new Dimension(1000, 250));
        return tableScrollPane;
    }

    ;
    
    public JPanel orderEntrySavePanel() {
        getOrderEntrySavePanel().setLayout(new FlowLayout(FlowLayout.TRAILING));
        getAddToFillerButton().setPreferredSize(new Dimension(100, 30));
        getOpenButton().setPreferredSize(new Dimension(100, 30));
        getSaveAsButton().setPreferredSize(new Dimension(100, 30));
        getSaveButton().setPreferredSize(new Dimension(100, 30));
        getDiscardButton().setPreferredSize(new Dimension(100, 30));
        getOrderEntrySavePanel().add(getAddToFillerButton());
        getOrderEntrySavePanel().add(getOpenButton());
        getOrderEntrySavePanel().add(getSaveAsButton());
        getOrderEntrySavePanel().add(getSaveButton());
        getOrderEntrySavePanel().add(getDiscardButton());
        return getOrderEntrySavePanel();
    }

    public JPanel orderEntryTabPanel() {
        getOrderEntryTabPanel().setLayout(new BoxLayout(getOrderEntryTabPanel(), BoxLayout.Y_AXIS));
        getOrderEntryTabPanel().add(Box.createHorizontalGlue());

        //Add components to the parent panel
        getOrderEntryTabPanel().add(orderEntryFormPanel());
        getOrderEntryTabPanel().add(orderEntryTablePanel());
        getOrderEntryTabPanel().add(orderEntrySavePanel());

        setListOfTextFields();
        disableFormElements();
        return getOrderEntryTabPanel();
    }

    ;
    
       
    public void shapeComboBoxActionPerformed(String shapeChosen) {
        FormElementsManager femObj = new FormElementsManager();
        String shapeChosenReplaced = shapeChosen.replace(" ", "_");
        femObj.enableFormElements(shapeChosenReplaced, this);
        // enableFormElements(formElementsIndex);
        String imagePath = femObj.getImagePath();
        setImageOnShapePreviewPanel(imagePath);
    }

    public void submitButtonActionPerformed(String shapeChosen) throws Exception {
        try {
            PopulateOrderEntryTable poetObj = new PopulateOrderEntryTable();
            String shapeChosenReplaced = shapeChosen.replace(" ", "_");
            Object row[] = poetObj.populateTable(shapeChosenReplaced, this);
            for (int i = 0; i < row.length; i++) {
                Object checkForEmpty = "";
                if (row[i].equals(checkForEmpty) && i != 2) {
                    errorMessage = "Please input all the fields, if not required then put \"-\"";
                    throw new Exception();
                } else {
                    if (i > 3 && i < 12) {
                        String checkForValue = row[i].toString();
                        if (!checkForValue.matches("[-+]?\\d*\\.?\\d+")) {
                            errorMessage = "Please input numeric values";
                            throw new Exception();
                        }
                    }
                }

            }
            if (row[2] != null) {
                getOrderEntryTableModel().addRow(row);
                clearButtonActionPerformed();

            } else {
                errorMessage = "Please choose a shape";
                throw new Exception();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }

    public void clearButtonActionPerformed() {
        for (JTextField txtField : getListOfTextFields()) {
            txtField.setText("");
        }
    }

    public void addToFillerButtonActionPerformed() throws IOException {
        int selected = getOrderEntryTablePanel().getSelectedRow();
        if (selected != -1) {
            Object rowData[][] = new Object[1][getOrderEntryTableModel().getColumnCount()];
            doObj = new DatabaseOperations();
            for (int j = 0; j < getOrderEntryTableModel().getColumnCount(); j++) {
                rowData[0][j] = getOrderEntryTableModel().getValueAt(selected, j);
            }

            getDoObj().writeToFile(rowData, "C:/PSTApplication/Bin/", "fillerDatabase.pt", false);

            getOrderEntryTableModel().removeRow(selected);

        }

    }

    public void saveButtonActionPerformed() throws IOException {
        try {
            String fileName = null;
            if (saveAs != null) {
                fileName = saveAs;
                saveAs = null;
            } else {
                fileName = "ORDER_" + getCount() + ".ord";
                count++;
            }

            Object rowData[][] = new Object[getOrderEntryTableModel().getRowCount()][getOrderEntryTableModel().getColumnCount()];
            doObj = new DatabaseOperations();
            for (int i = 0; i < getOrderEntryTableModel().getRowCount(); i++) {
                for (int j = 0; j < getOrderEntryTableModel().getColumnCount(); j++) {
                    if (getOrderEntryTableModel().getValueAt(i, j).equals("")) {
                        rowData[i][j] = 0.0;
                    } else {
                        rowData[i][j] = getOrderEntryTableModel().getValueAt(i, j);
                    }
                }
            }

            getDoObj().writeToFile(rowData, "C:/PSTApplication/Bin/order_placed/", fileName, false);

            int rowCount = getOrderEntryTableModel().getRowCount();

            for (int i = rowCount - 1; i >= 0; i--) {
                getOrderEntryTableModel().removeRow(i);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, errorMessage);
        }
    }

    public void openButtonActionPerformed() throws IOException {
        doObj = new DatabaseOperations();
        File[] filesFetched = doObj.fetchAllFiles("C:/PSTApplication/Bin/order_placed/");
        Object[] openFileOptions = new Object[filesFetched.length];
        for (int i = 0; i < filesFetched.length; i++) {
            openFileOptions[i] = filesFetched[i].getName().replace(".ord", "");
        }
        JDialog parentComponent = new JDialog();

        String openFile = (String) JOptionPane.showInputDialog(parentComponent, "Open File", "Open File", JOptionPane.PLAIN_MESSAGE, null, openFileOptions, "Choose file");
        saveAs = openFile;
        if (!(openFile == null)) {
            Object[][] rowData = doObj.readFromFile("C:/PSTApplication/Bin/order_placed/", openFile);
            for (Object[] row : rowData) {
                orderEntryTableModel.addRow(row);
            }
        }

    }

    public void saveAsButtonActionPerformed() throws IOException {
        saveAs = JOptionPane.showInputDialog("Save As");
        saveAs = saveAs + ".ord";
        saveButtonActionPerformed();

    }

    public void discardButtonActionPerformed() {
        int checkSelection = getOrderEntryTablePanel().getSelectedRow();
        if (checkSelection >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this entry?");
            if(confirmation == 0){
                getOrderEntryTableModel().removeRow(getOrderEntryTablePanel().getSelectedRow());
            }
        }
    }

    /*    public void enableFormElements(ArrayList<Integer> index){
        disableFormElements();
        if(index.size()>0){
            ArrayList<JTextField> listOfTextFieldsTemp = getListOfTextFields();
            for(Integer temp : index){
                JTextField jText = listOfTextFieldsTemp.get(temp);
                jText.setEnabled(true);
            }
        }
        
    }*/
    public void disableFormElements() {

        ArrayList<JTextField> listOfTextFieldsTemp = getListOfTextFields();
        for (JTextField textFields : listOfTextFieldsTemp) {
            textFields.setEnabled(false);
        };
        getCodeComboBox().setEnabled(false);
    }

    public void setListOfTextFields() {
        Component[] components = createFormElements().getComponents();
        listOfTextFields = new ArrayList();
        for (Component component : components) {
            if (component.getClass().equals(JTextField.class)) {
                getListOfTextFields().add((JTextField) component);
            }
        };
    }

    /**
     * @param imageLabel the imageLabel to set
     */
    public void setImageLabel(String pathToImage) {
        imagePreview = new ImageIcon(pathToImage);
        imageLabel = new JLabel();
        getImageLabel().setIcon(getImagePreview());
        // imageLabel.setPreferredSize(new Dimension(100, 100));
    }

    public void setImageOnShapePreviewPanel(String imagePath) {
        getShapePreview().removeAll();
        setImageLabel(imagePath);
        getShapePreview().add(getImageLabel());
        getShapePreview().revalidate();
        getShapePreview().repaint();
    }

    /**
     * @return the width
     */
    public JLabel getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public JLabel getHeight() {
        return height;
    }

    /**
     * @return the base
     */
    public JLabel getBase() {
        return base;
    }

    /**
     * @return the radius
     */
    public JLabel getRadius() {
        return radius;
    }

    /**
     * @return the top
     */
    public JLabel getTop() {
        return top;
    }

    /**
     * @return the diameter
     */
    public JLabel getDiameter() {
        return diameter;
    }

    /**
     * @return the offset
     */
    public JLabel getOffset() {
        return offset;
    }

    /**
     * @return the length
     */
    public JLabel getLength() {
        return length;
    }

    /**
     * @return the sides
     */
    public JLabel getSides() {
        return sides;
    }

    /**
     * @return the majorDia
     */
    public JLabel getMajorDia() {
        return majorDia;
    }

    /**
     * @return the minorDia
     */
    public JLabel getMinorDia() {
        return minorDia;
    }

    /**
     * @return the OAH
     */
    public JLabel getOAH() {
        return OAH;
    }

    /**
     * @return the x1
     */
    public JLabel getX1() {
        return x1;
    }

    /**
     * @return the x2
     */
    public JLabel getX2() {
        return x2;
    }

    /**
     * @return the y1
     */
    public JLabel getY1() {
        return y1;
    }

    /**
     * @return the y2
     */
    public JLabel getY2() {
        return y2;
    }

    /**
     * @return the y3
     */
    public JLabel getY3() {
        return y3;
    }

    /**
     * @return the lHeight
     */
    public JLabel getlHeight() {
        return lHeight;
    }

    /**
     * @return the rHeight
     */
    public JLabel getrHeight() {
        return rHeight;
    }

    /**
     * @return the clip
     */
    public JLabel getClip() {
        return clip;
    }

    /**
     * @return the tl
     */
    public JLabel getTl() {
        return tl;
    }

    /**
     * @return the tr
     */
    public JLabel getTr() {
        return tr;
    }

    /**
     * @return the bl
     */
    public JLabel getBl() {
        return bl;
    }

    /**
     * @return the br
     */
    public JLabel getBr() {
        return br;
    }

    /**
     * @return the tls
     */
    public JLabel getTls() {
        return tls;
    }

    /**
     * @return the bls
     */
    public JLabel getBls() {
        return bls;
    }

    /**
     * @return the trs
     */
    public JLabel getTrs() {
        return trs;
    }

    /**
     * @return the brs
     */
    public JLabel getBrs() {
        return brs;
    }

    /**
     * @return the code0
     */
    public JLabel getCode0() {
        return code0;
    }

    /**
     * @return the code1
     */
    public JLabel getCode1() {
        return code1;
    }

    /**
     * @return the code2
     */
    public JLabel getCode2() {
        return code2;
    }

    /**
     * @return the code4
     */
    public JLabel getCode4() {
        return code4;
    }

    /**
     * @return the r1
     */
    public JLabel getR1() {
        return r1;
    }

    /**
     * @return the r2
     */
    public JLabel getR2() {
        return r2;
    }

    /**
     * @return the angle
     */
    public JLabel getAngle() {
        return angle;
    }

    /**
     * @return the qty
     */
    public JLabel getQty() {
        return qty;
    }

    /**
     * @return the customer
     */
    public JLabel getCustomer() {
        return customer;
    }

    /**
     * @return the imageLabel
     */
    public JLabel getImageLabel() {
        return imageLabel;
    }

    /**
     * @return the info1
     */
    public JLabel getInfo1() {
        return info1;
    }

    /**
     * @return the info2
     */
    public JLabel getInfo2() {
        return info2;
    }

    /**
     * @return the glassType
     */
    public JLabel getGlassType() {
        return glassType;
    }

    /**
     * @return the widthText
     */
    public JTextField getWidthText() {
        return widthText;
    }

    /**
     * @return the heightText
     */
    public JTextField getHeightText() {
        return heightText;
    }

    /**
     * @return the baseText
     */
    public JTextField getBaseText() {
        return baseText;
    }

    /**
     * @return the radiusText
     */
    public JTextField getRadiusText() {
        return radiusText;
    }

    /**
     * @return the topText
     */
    public JTextField getTopText() {
        return topText;
    }

    /**
     * @return the diameterText
     */
    public JTextField getDiameterText() {
        return diameterText;
    }

    /**
     * @return the offsetText
     */
    public JTextField getOffsetText() {
        return offsetText;
    }

    /**
     * @return the lengthText
     */
    public JTextField getLengthText() {
        return lengthText;
    }

    /**
     * @return the sidesText
     */
    public JTextField getSidesText() {
        return sidesText;
    }

    /**
     * @return the majorDiaText
     */
    public JTextField getMajorDiaText() {
        return majorDiaText;
    }

    /**
     * @return the minorDiaText
     */
    public JTextField getMinorDiaText() {
        return minorDiaText;
    }

    /**
     * @return the OAHText
     */
    public JTextField getOAHText() {
        return OAHText;
    }

    /**
     * @return the x1Text
     */
    public JTextField getX1Text() {
        return x1Text;
    }

    /**
     * @return the x2Text
     */
    public JTextField getX2Text() {
        return x2Text;
    }

    /**
     * @return the y1Text
     */
    public JTextField getY1Text() {
        return y1Text;
    }

    /**
     * @return the y2Text
     */
    public JTextField getY2Text() {
        return y2Text;
    }

    /**
     * @return the y3Text
     */
    public JTextField getY3Text() {
        return y3Text;
    }

    /**
     * @return the lHeightText
     */
    public JTextField getlHeightText() {
        return lHeightText;
    }

    /**
     * @return the rHeightText
     */
    public JTextField getrHeightText() {
        return rHeightText;
    }

    /**
     * @return the clipText
     */
    public JTextField getClipText() {
        return clipText;
    }

    /**
     * @return the tlText
     */
    public JTextField getTlText() {
        return tlText;
    }

    /**
     * @return the trText
     */
    public JTextField getTrText() {
        return trText;
    }

    /**
     * @return the blText
     */
    public JTextField getBlText() {
        return blText;
    }

    /**
     * @return the brText
     */
    public JTextField getBrText() {
        return brText;
    }

    /**
     * @return the tlsText
     */
    public JTextField getTlsText() {
        return tlsText;
    }

    /**
     * @return the blsText
     */
    public JTextField getBlsText() {
        return blsText;
    }

    /**
     * @return the tlrText
     */
    public JTextField getTrsText() {
        return trsText;
    }

    /**
     * @return the blrText
     */
    public JTextField getBrsText() {
        return brsText;
    }

    /**
     * @return the code0Text
     */
    public JTextField getCode0Text() {
        return code0Text;
    }

    /**
     * @return the code1Text
     */
    public JTextField getCode1Text() {
        return code1Text;
    }

    /**
     * @return the code2Text
     */
    public JTextField getCode2Text() {
        return code2Text;
    }

    /**
     * @return the code4Text
     */
    public JTextField getCode4Text() {
        return code4Text;
    }

    /**
     * @return the r1Text
     */
    public JTextField getR1Text() {
        return r1Text;
    }

    /**
     * @return the r2Text
     */
    public JTextField getR2Text() {
        return r2Text;
    }

    /**
     * @return the angleText
     */
    public JTextField getAngleText() {
        return angleText;
    }

    /**
     * @return the qtyText
     */
    public JTextField getQtyText() {
        return qtyText;
    }

    /**
     * @return the customerText
     */
    public JTextField getCustomerText() {
        return customerText;
    }

    /**
     * @return the info1Text
     */
    public JTextField getInfo1Text() {
        return info1Text;
    }

    /**
     * @return the info2Text
     */
    public JTextField getInfo2Text() {
        return info2Text;
    }

    /**
     * @return the glassTypeText
     */
    public JTextField getGlassTypeText() {
        return glassTypeText;
    }

    /**
     * @return the orderEntryTabPanel
     */
    public JPanel getOrderEntryTabPanel() {
        return orderEntryTabPanel;
    }

    /**
     * @return the shapePreview
     */
    public JPanel getShapePreview() {
        return shapePreview;
    }

    /**
     * @return the orderEntryTablePanel
     */
    public JTable getOrderEntryTablePanel() {
        return orderEntryTablePanel;
    }

    /**
     * @return the orderEntrySavePanel
     */
    public JPanel getOrderEntrySavePanel() {
        return orderEntrySavePanel;
    }

    /**
     * @return the orderEntryTableModel
     */
    public DefaultTableModel getOrderEntryTableModel() {
        return orderEntryTableModel;
    }

    /**
     * @return the submitButton
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * @return the clearButton
     */
    public JButton getClearButton() {
        return clearButton;
    }

    /**
     * @return the saveButton
     */
    public JButton getSaveButton() {
        return saveButton;
    }

    /**
     * @return the discardButton
     */
    public JButton getDiscardButton() {
        return discardButton;
    }

    /**
     * @return the listOfTextFields
     */
    public ArrayList<JTextField> getListOfTextFields() {
        return listOfTextFields;
    }

    /**
     * @return the imagePreview
     */
    public ImageIcon getImagePreview() {
        return imagePreview;
    }

    /**
     * @return the count
     */
    public static int getCount() {
        return count;
    }

    /**
     * @return the doObj
     */
    public DatabaseOperations getDoObj() {
        return doObj;
    }

    /**
     * @return the optObj
     */
    public OrderPlacementTab getOptObj() {
        return optObj;
    }

    /**
     * @return the saveAs
     */
    public JButton getSaveAsButton() {
        return saveAsButton;
    }

    /**
     * @return the open
     */
    public JButton getOpenButton() {
        return openButton;
    }

    /**
     * @return the addToFiller
     */
    public JButton getAddToFillerButton() {
        return addToFillerButton;
    }

    /**
     * @return the codeComboBox
     */
    public JComboBox<Object> getCodeComboBox() {
        return codeComboBox;
    }

    /**
     * @param codeComboBox the codeComboBox to set
     */
    public void setCodeComboBox(JComboBox<Object> codeComboBox) {
        this.codeComboBox = codeComboBox;
    }

}
