/*

 */
package BusinessLayer;

import PresentationLayer.OrderEntryTab;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * @author vinay bhutada
 */
public class PopulateOrderEntryTable {

    private String message;

    public PopulateOrderEntryTable() {

    }

    ;
    
    public Object[] populateTable(String shapeChosen, OrderEntryTab oetObj) throws Exception {

        Object[] row = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};

        row[1] = 1;
        row[12] = 1;
        row[13] = 1;

        switch (shapeChosen) {
            case "Rectangle":
                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getHeightText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;

            case "Triangle":
                row[4] = oetObj.getBaseText().getText();
                row[5] = oetObj.getlHeightText().getText();
                row[6] = oetObj.getrHeightText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Radius_Rectangle":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getHeightText().getText();
                row[6] = oetObj.getRadiusText().getText();
                row[7] = oetObj.getCode0Text().getText();
                row[8] = oetObj.getCode1Text().getText();
                row[9] = oetObj.getCode2Text().getText();
                row[10] = oetObj.getCode4Text().getText();

                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Trapezoid":

                row[4] = oetObj.getTopText().getText();
                row[5] = oetObj.getHeightText().getText();
                row[6] = oetObj.getBaseText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Circle":

                row[4] = oetObj.getDiameterText().getText();

                row[5] = 0;
                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Parallelogram":

                row[4] = oetObj.getHeightText().getText();
                row[5] = oetObj.getBaseText().getText();
                row[6] = oetObj.getOffsetText().getText();
                row[7] = oetObj.getCode1Text().getText();
                row[8] = oetObj.getCode2Text().getText();

                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();
                break;
            case "Polygon":

                row[4] = oetObj.getSidesText().getText();
                row[5] = oetObj.getLengthText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Single_Circle_Top":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getHeightText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Double_Circle_Top":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getLengthText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Semi-Circle":

                row[4] = oetObj.getRadiusText().getText();

                row[5] = 0;
                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Ellipse":

                row[4] = oetObj.getMajorDiaText().getText();
                row[5] = oetObj.getMinorDiaText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Arch":

                row[4] = oetObj.getBaseText().getText();
                row[5] = oetObj.getHeightText().getText();
                row[6] = oetObj.getOAHText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Gothic":

                row[4] = oetObj.getBaseText().getText();
                row[5] = oetObj.getHeightText().getText();
                row[6] = oetObj.getRadiusText().getText();
                row[7] = oetObj.getOAHText().getText();

                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Semi-Ellipse":

                row[4] = oetObj.getMinorDiaText().getText();
                row[5] = oetObj.getMajorDiaText().getText();

                row[6] = 0;
                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Shape_13":

                row[4] = oetObj.getHeightText().getText();
                row[5] = oetObj.getBaseText().getText();
                row[6] = oetObj.getOAHText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Double_Triangle_Top":

                row[4] = oetObj.getX1Text().getText();
                row[5] = oetObj.getX2Text().getText();
                row[6] = oetObj.getY1Text().getText();
                row[7] = oetObj.getY2Text().getText();
                row[8] = oetObj.getY3Text().getText();

                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Single_Triangle_Top":

                row[4] = oetObj.getX1Text().getText();
                row[5] = oetObj.getX2Text().getText();
                row[6] = oetObj.getY1Text().getText();
                row[7] = oetObj.getY2Text().getText();
                row[8] = oetObj.getY3Text().getText();

                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Rake":

                row[4] = oetObj.getlHeightText().getText();
                row[5] = oetObj.getrHeightText().getText();
                row[6] = oetObj.getBaseText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Clipped_Corner":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getHeightText().getText();
                row[6] = oetObj.getClipText().getText();
                row[7] = oetObj.getCode1Text().getText();
                row[8] = oetObj.getCode2Text().getText();
                row[9] = oetObj.getCode4Text().getText();

                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Quadrilateral":
                row[4] = oetObj.getTlText().getText();
                row[5] = oetObj.getTrText().getText();
                row[6] = oetObj.getBlText().getText();
                row[7] = oetObj.getBrText().getText();
                row[8] = oetObj.getTlsText().getText();
                row[9] = oetObj.getTrsText().getText();
                row[10] = oetObj.getBlsText().getText();
                row[11] = oetObj.getBrsText().getText();

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Donut":

                row[4] = oetObj.getR1Text().getText();
                row[5] = oetObj.getR2Text().getText();
                row[6] = oetObj.getAngleText().getText();

                row[7] = 0;
                row[8] = 0;
                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Quarter_Arc":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getOAHText().getText();
                row[6] = oetObj.getRadiusText().getText();
                row[7] = oetObj.getCode1Text().getText();
                row[8] = oetObj.getCode2Text().getText();

                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Extended_Quarter_Arc":

                row[4] = oetObj.getWidthText().getText();
                row[5] = oetObj.getOAHText().getText();
                row[6] = oetObj.getHeightText().getText();
                row[7] = oetObj.getRadiusText().getText();
                row[8] = oetObj.getCode1Text().getText();
                row[9] = oetObj.getCode2Text().getText();

                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Half_Arc":

                row[4] = oetObj.getBaseText().getText();
                row[5] = oetObj.getOAHText().getText();
                row[6] = oetObj.getRadiusText().getText();
                row[7] = oetObj.getCode1Text().getText();
                row[8] = oetObj.getCode2Text().getText();

                row[9] = 0;
                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            case "Extended_Half_Arc":

                row[4] = oetObj.getBaseText().getText();
                row[5] = oetObj.getOAHText().getText();
                row[6] = oetObj.getHeightText().getText();
                row[7] = oetObj.getRadiusText().getText();
                row[8] = oetObj.getCode1Text().getText();
                row[9] = oetObj.getCode2Text().getText();

                row[10] = 0;
                row[11] = 0;

                row[14] = oetObj.getGlassTypeText().getText();
                row[2] = shapeChosen;
                row[3] = oetObj.getQtyText().getText();
                row[0] = oetObj.getCustomerText().getText();
                row[15] = oetObj.getInfo1Text().getText();
                row[16] = oetObj.getInfo2Text().getText();

                break;
            default:
                
                break;
        }

        return row;

    }
}
