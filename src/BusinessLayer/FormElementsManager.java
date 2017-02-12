/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import PresentationLayer.OrderEntryTab;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author vinay bhutada
 */
public class FormElementsManager{
    
   // private OrderEntryTab oetObj;
    private ArrayList formElementsIndex;
    private String imagePath;
    
    public void setImagepath(String Path){
        imagePath = Path;
    }
    
    public String getImagePath(){
        return imagePath;
    }
    
    public void enableFormElements(String shapeChosen, OrderEntryTab oetObj){
        switch(shapeChosen){
            case "Rectangle":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Rectangle.JPG");
                break;
                
            case "Triangle":
                oetObj.disableFormElements();
                oetObj.disableFormElements();
                oetObj.getBaseText().setEnabled(true);
                oetObj.getlHeightText().setEnabled(true);
                oetObj.getrHeightText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Triangle.JPG");
                break;
            case "Radius_Rectangle":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getRadiusText().setEnabled(true);
                /*oetObj.getCode0Text().setEnabled(true);
                oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);
                oetObj.getCode4Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Radius_Rectangle.JPG");
                break;
            case "Trapezoid":
                oetObj.disableFormElements();
                oetObj.getTopText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Trapezoid.JPG");
                break;
            case "Circle":
                oetObj.disableFormElements();
                oetObj.getDiameterText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Circle.JPG");
                break;
            case "Parallelogram":
                oetObj.disableFormElements();
                oetObj.getHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getOffsetText().setEnabled(true);
                /*oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Parallelogram.JPG");
                break;
            case "Polygon":
                oetObj.disableFormElements();
                oetObj.getSidesText().setEnabled(true);
                oetObj.getLengthText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Polygon.JPG");
                break;
            case "Single_Circle_Top":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Single_Circle_Top.JPG");
                break;
            case "Double_Circle_Top":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getLengthText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Double_Circle_Top.JPG");
                break;
            case "Semi-Circle":
                oetObj.disableFormElements();
                oetObj.getDiameterText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Semi_Circle.JPG");
                break;
            case "Ellipse":
                oetObj.disableFormElements();
                oetObj.getMajorDiaText().setEnabled(true);
                oetObj.getMinorDiaText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Ellipse.JPG");
                break;
            case "Arch":
                oetObj.disableFormElements();
                oetObj.getHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Arch.JPG");
                break;
            case "Gothic":
                oetObj.disableFormElements();
                oetObj.getHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getRadiusText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Gothic.JPG");
                break;
            case "Semi-Ellipse":
                oetObj.disableFormElements();
                oetObj.getMinorDiaText().setEnabled(true);
                oetObj.getMajorDiaText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Semi_Ellipse.JPG");
                break;
            case "Shape_13":
                oetObj.disableFormElements();
                oetObj.getHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Shape_13.JPG"); //to confirm
                break;
            case "Double_Triangle_Top":
                oetObj.disableFormElements();
                oetObj.getX1Text().setEnabled(true);
                oetObj.getX2Text().setEnabled(true);
                oetObj.getY1Text().setEnabled(true);
                oetObj.getY2Text().setEnabled(true);
                oetObj.getY3Text().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Double_Triangle_Top.JPG");
                break;
            case "Single_Triangle_Top":
                oetObj.disableFormElements();
                oetObj.getX1Text().setEnabled(true);
                oetObj.getX2Text().setEnabled(true);
                oetObj.getY1Text().setEnabled(true);
                oetObj.getY2Text().setEnabled(true);
                oetObj.getY3Text().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Single_Triangle_Top.JPG");
                break;
            case "Rake":
                oetObj.disableFormElements();
                oetObj.getlHeightText().setEnabled(true);
                oetObj.getrHeightText().setEnabled(true);
                oetObj.getBaseText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Rake.JPG");
                break;
            case "Clipped_Corner":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getClipText().setEnabled(true);
                /*oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);
                oetObj.getCode4Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Clipped_Corner.JPG");
                break;
            case "Quadrilateral":
                oetObj.disableFormElements();
                oetObj.getTlText().setEnabled(true);
                oetObj.getTrText().setEnabled(true);
                oetObj.getBlText().setEnabled(true);
                oetObj.getBrText().setEnabled(true);
                oetObj.getTlsText().setEnabled(true);
                oetObj.getTrsText().setEnabled(true);
                oetObj.getBlsText().setEnabled(true);
                oetObj.getBrsText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Quadrilateral.JPG");
                break;
            case "Donut":
                oetObj.disableFormElements();
                oetObj.getR1Text().setEnabled(true);
                oetObj.getR2Text().setEnabled(true);
                oetObj.getAngleText().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Donut.JPG");
                break;
            case "Quarter_Arc":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getRadiusText().setEnabled(true);
               /* oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Quarter_Arc.JPG");
                break;
            case "Extended_Quarter_Arc":
                oetObj.disableFormElements();
                oetObj.getWidthText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getRadiusText().setEnabled(true);
               /* oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Extended_Quarter_Arc.JPG");  //to change
                break;
            case "Half_Arc":
                oetObj.disableFormElements();
                oetObj.getBaseText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);                
                oetObj.getRadiusText().setEnabled(true);
               /* oetObj.getCode1Text().setEnabled(true);
                oetObj.getCode2Text().setEnabled(true);*/
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Half_Arc.JPG");
                break;
            case "Extended_Half_Arc":
                oetObj.disableFormElements();
                oetObj.getBaseText().setEnabled(true);
                oetObj.getOAHText().setEnabled(true);
                oetObj.getHeightText().setEnabled(true);
                oetObj.getRadiusText().setEnabled(true);
              //  oetObj.getCode1Text().setEnabled(true);
              //  oetObj.getCode2Text().setEnabled(true);
                oetObj.getCodeComboBox().setEnabled(true);
                oetObj.getQtyText().setEnabled(true);
                oetObj.getGlassTypeText().setEnabled(true);
                oetObj.getCustomerText().setEnabled(true);
                oetObj.getInfo1Text().setEnabled(true);
                oetObj.getInfo2Text().setEnabled(true);
                imagePath = new String("C:/PSTApplication/Bin/images/Extended_Half_Arc.JPG");
                break;
            default:
                oetObj.disableFormElements();
                break;
        }
        
    }
    
    
}
