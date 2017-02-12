/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author anmolb77
 */
public class PlacingOperation {
    HashMap shapes = new HashMap();
    //final float shapesBurn = (float) 0.5;
    static String glassInUse;
    static int glassInUseQuantity;
    String width;
    String height;
    String quantity;
    String OAH;
    String diameter;
    HashMap wrappingRectanglesQuantity = new HashMap();
    HashMap wrappingRectanglesArea  = new HashMap();
    HashMap wrappingRectanglesDim  = new HashMap();
    HashMap wrappingRectanglesGlass  = new HashMap();
    HashMap wrappingRectanglesAttributes  = new HashMap();
    HashMap glassData = new HashMap();
    HashMap glassAvailabilityData = new HashMap();
    HashMap plotRect = new HashMap();
    DatabaseOperations doObj;
    Object glassRowData[][];
    Object[] sampleGlassData = {"SSCLR","50.00","100.00","1","0","0","1"};
    float glassSheetHeight = (float)150.00;
    float glassSheetWidth = (float)150.00;
    float X = (float)0.0;//Float.parseFloat()glassData[3];
    float Y = (float)0.0;
    float X1 = (float)0.0;
    float Y1 = (float)0.0;
    float prevWidth = (float)0.0;
    float prevHeight = (float)0.0;
    float newWidth = (float)0.0;
    float newHeight = (float)0.0;
    
    public PlacingOperation(){
        //wrappingRectanglesQuantity = new HashMap();
        //wrappingRectanglesArea = new HashMap();
        //wrappingRectanglesDim = new HashMap();
        //wrappingRectanglesAttributes = new HashMap();
        //plotRect = new Stack();
        //shapes = new HashMap();
        glassInUse = "";
        glassInUseQuantity = 0;
        glassSheetHeight = (float)0.00;
        glassSheetWidth = (float)0.00;
        System.out.println("I am being called in constructor");
        shapes.put("Rectangle","4#5"); 
        shapes.put("Triangle", "4#5");
        shapes.put("Radius Rectangle", "4#5");
        shapes.put("Trapezoid", "5#6");
        shapes.put("Circle", "4");// --------------------------------
        shapes.put("Parallelogram", "4#5#6");// --------------------------------
        //shapes.put("Polygon", "");
        shapes.put("Single Circle Top", "4#5");
        shapes.put("Double Circle Top", "4#5");
        shapes.put("Semi-Circle", "4");
        shapes.put("Ellipse", "4#5");
        shapes.put("Arch", "5#6");
        shapes.put("Gothic", "4#5");
        shapes.put("Semi-Ellipse", "4#5");
        shapes.put("Shape 13", "5#6");
        shapes.put("Double Triangle Top", "4#6");
        shapes.put("Single Triangle Top", "4#6");
        shapes.put("Rake", "4#5#6");// --------------------------------
        shapes.put("Clipped Corner", "4#5");
        //shapes.put("Quadrilateral", "4#5");
        shapes.put("Donut", "4#4");
        shapes.put("Quarter Arc", "4#5");
        shapes.put("Extended Quarter Arc", "4#5");
        shapes.put("Half Arc", "4#5");
        shapes.put("Extended Half Arc", "4#5");
        try{
            getGlassData();
        }catch(IOException e){
            raiseError("Parsing glass data failed :: " + e);
        }
        
    }
    
    private void getGlassData() throws IOException{
        doObj = new DatabaseOperations();
        glassRowData = doObj.readFromFile("C:/PSTApplication/Bin/","glassDatabase.pt");
        for(int i = 0; i <glassRowData.length; i++){
            for(int j = 0; j<glassRowData[i].length; j++){
                glassAvailabilityData.put((String)glassRowData[i][0], Integer.parseInt((String)glassRowData[i][3]));
                glassData.put((String)glassRowData[i][0], glassRowData[i]);
            }
        }
    }
    
    public void parseOrderInfo(Object[][] rowData){
        String temp = "";
        Object[] tempArr;
        for(int i=0; i < rowData.length; i++){   
            
            
            //System.out.print("nos rows  : " + rowData[i].length + "\t Rectangle ? " + rowData[i][2] + "\n");
            for(int j=0; j < rowData[i].length; j++){
                temp = temp + (String)rowData[i][j] + "\t";
                if( (j == 2) ){// && (((String)rowData[i][j]).equals("Rectangle"))){
                    System.out.print((String)rowData[i][j] + "\n");
                    System.out.println(shapes.get("Rectangle").toString());
                    System.out.print(shapes.get(((String)rowData[i][j]).trim()).toString() + "\n");
                    tempArr = shapes.get((String)rowData[i][j]).toString().split("#");
                    if (tempArr.length != 2){
                        quantity = (String)rowData[i][3];
                        if (((String)rowData[i][j]).equals("Circle")){
                            glassInUse = (String)rowData[i][14];
                            
                            width = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[0].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                            height = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[0].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                        } else if(((String)rowData[i][j]).equals("Rake")){
                            glassInUse = (String)rowData[i][14];
                            width = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[2].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                            height = String.valueOf(Math.max(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[0].toString())]), Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[1].toString())])) + 2*getClearAdjustment((String)rowData[i][14]));
                        } else if (((String)rowData[i][j]).equals("Parallelogram")){
                            glassInUse = (String)rowData[i][14];
                            width = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[1].toString())]) + Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[1].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                            height = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[0].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                        }
                        for(int q = 0; q < Integer.parseInt(quantity); q++){
                            
                            
                            //System.out.println("Width : \t" + width + "\t Height : \t" + height + "\n");
                            wrappingRectanglesAttributes.put("R"+i+"-"+q, rowData[i]);
                            wrappingRectanglesQuantity.put("R"+i+"-"+q, quantity);
                            //System.out.print("Current hashmap size" + wrappingRectanglesQuantity.size());
                            wrappingRectanglesArea.put("R"+i+"-"+q, Float.parseFloat(width)*Float.parseFloat(height));
                            wrappingRectanglesDim.put("R"+i+"-"+q, width + "#" + height);
                            
                        }
                    }else{
                        quantity = (String)rowData[i][3];
                        glassInUse = (String)rowData[i][14];
                        width = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[0].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                        height = String.valueOf(Float.parseFloat((String)rowData[i][Integer.parseInt(tempArr[1].toString())]) + 2*getClearAdjustment((String)rowData[i][14]));
                        //Object[] tempArrT = rowData[i];
                        //System.out.println("Array length is  : " + tempArrT.length + "\n" + temp.toString());
                        //System.out.println("Quantity is :: " + quantity);
                        for(int q = 0; q < Integer.parseInt(quantity); q++){
                            //System.out.println("Width : \t" + width + "\t Height : \t" + height + "\n");
                            wrappingRectanglesQuantity.put("R"+i+"-"+q, quantity);
                            //System.out.print("Current hashmap size" + wrappingRectanglesQuantity.size());
                            wrappingRectanglesArea.put("R"+i+"-"+q, Float.parseFloat(width)*Float.parseFloat(height));
                            wrappingRectanglesDim.put("R"+i+"-"+q, width + "#" + height);
                            wrappingRectanglesAttributes.put("R"+i+"-"+q, rowData[i]);
                        }
                    }
                    /*width = (String)rowData[i][4];
                    height = (String)rowData[i][5];
                    quantity = (String)rowData[i][3];
                    */
                    //System.out.println("Width : \t" + width + "\t Height : \t" + height + "\n");
                    //System.out.println(/*Float.parseFloat(width) <*/ glassSheetWidth);
                    /*if(Float.parseFloat(width) <= glassSheetWidth && Float.parseFloat(height) <= glassSheetHeight){
                        //System.out.println("Width : \t" + width + "\t Height : \t" + height + "\n");
                        wrappingRectanglesQuantity.put("R"+i, quantity);
                        //System.out.print("Current hashmap size" + wrappingRectanglesQuantity.size());
                        wrappingRectanglesArea.put("R"+i, Float.parseFloat(width)*Float.parseFloat(height));
                        wrappingRectanglesDim.put("R"+i, width + "#" + height);
                    }*/
                }
            }
            
            temp = temp + "\n";
            //wrappingRectanglesAttributes.put("R"+i, temp);
            temp = "";
        }
    }
    
    public void plotRectangles(){
        String biggestRectangle;
        
        Object currentGlassAttr[] = (Object[])glassData.get(glassInUse);
        glassSheetHeight = Float.parseFloat((String)currentGlassAttr[2]) - Float.parseFloat((String)currentGlassAttr[6]);
        glassSheetWidth = Float.parseFloat((String)currentGlassAttr[1]) - Float.parseFloat((String)currentGlassAttr[7]);
        glassInUseQuantity = Integer.parseInt((String)currentGlassAttr[3]);
        
        /************** 
         X,Y is for X axis || X1, Y1 if for Y axis
         * 
         * Adjustments for the glass burn area is below
         ****************/
        X = X + Float.parseFloat((String)currentGlassAttr[5]);
        Y = Y + Float.parseFloat((String)currentGlassAttr[8]); 
        X1 = X1 + Float.parseFloat((String)currentGlassAttr[5]);
        Y1 = Y1 + Float.parseFloat((String)currentGlassAttr[8]);       
        /*******
         Placing algorithm
         *****/
        while(!wrappingRectanglesQuantity.isEmpty()){
        
            biggestRectangle = getMaxArea();
            
                if(X + prevWidth <= glassSheetWidth && Y1 + prevHeight <= glassSheetHeight){
                    
                    //Y = Y1;
                    //X1 = X;
                    if(prevWidth > X){
                        X = X + prevWidth;
                    }
                    Y1 = Y1 + prevHeight;
                    
                    
                    newWidth = Float.parseFloat(wrappingRectanglesDim.get(biggestRectangle).toString().split("#")[0]);
                    newHeight = Float.parseFloat(wrappingRectanglesDim.get(biggestRectangle).toString().split("#")[1]);
                    if((Y1 + newHeight) <= glassSheetHeight){
                        System.out.print("\n Checked for height" + "\t X : " + X + "\t Y : " + Y + "\t X1 : " + X1 + "\t Y1 : " + Y1 + "\t Rect : " + biggestRectangle + "\n");
                        //System.out.print("\n :: " + startCoordinates.toString() + "\n ::");
                        plotRect.put(biggestRectangle , X1 + "#" + Y1 + "#white#" + newWidth + "#" + newHeight);
                        //Y1 = Y1 + newHeight;
                        prevWidth = newWidth;
                        prevHeight = newHeight;
                        
                        wrappingRectanglesArea.remove(biggestRectangle);
                        wrappingRectanglesDim.remove(biggestRectangle);
                        wrappingRectanglesQuantity.remove(biggestRectangle);  
                    }else if((X + newWidth) <= glassSheetWidth){
                        System.out.print("\n Checked for width" + "\t X : " + X + "\t Y : " + Y + "\t X1 : " + X1 + "\t Y1 :" + Y1 + "\t Rect : " + biggestRectangle + "\n");
                        //System.out.print("\n :: " + startCoordinates.toString() + "\n ::");
                        plotRect.put(biggestRectangle , X + "#" + Y + "#white#" + newWidth + "#" + newHeight);
                        //X = X + newWidth;
                        prevWidth = newWidth;
                        prevHeight = newHeight;
                        
                        wrappingRectanglesArea.remove(biggestRectangle);
                        wrappingRectanglesDim.remove(biggestRectangle);
                        wrappingRectanglesQuantity.remove(biggestRectangle);
                    }else{
                        wrappingRectanglesArea.clear();
                        wrappingRectanglesDim.clear();
                        wrappingRectanglesQuantity.clear();
                        raiseError("Insufficient Glass Sheet");
                    }
                    
                }else{
                    wrappingRectanglesArea.clear();
                    wrappingRectanglesDim.clear();
                    wrappingRectanglesQuantity.clear();
                    raiseError("Insufficient Glass Sheets");
                }
        }
    }
    
    public String getMaxArea(){
        HashMap.Entry maxEntry = null;
        Iterator it = wrappingRectanglesArea.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (maxEntry == null || (float)pair.getValue() > (float)maxEntry.getValue()){
                    maxEntry = pair;
                }
        }
        return (String)maxEntry.getKey();
    }
    
    public void printPlot(){
        while(!plotRect.isEmpty()){
            //Object a = plotRect.pop(); 
            //System.out.print(a);
        }
    }
    
    public Object[][] savePlot(){
        int i = 0;
        int i1 = plotRect.size();//4
        int c = 1;
        Object[] tempRow = new Object[14];
        Object[] temp = new Object[17];
        //ArrayList container = new ArrayList();
        Object[][] rowData = new Object[(wrappingRectanglesAttributes.size() * 2) +1][20];
        ArrayList element;
        rowData[0] = (Object[])glassData.get(glassInUse);
        Set<Map.Entry> entrySet1 = wrappingRectanglesAttributes.entrySet();
        Iterator<Entry> entrySetIterator = entrySet1.iterator();
        /************ 
         Loop for wrapping rectangles in white
         ************/
        while(entrySetIterator.hasNext()){
            System.out.println("C = " + c);
            Entry entry = entrySetIterator.next();
            tempRow = (Object[]) wrappingRectanglesAttributes.get(entry.getKey());
            element = new ArrayList(Arrays.asList(tempRow));
            //if(!element.get(2).toString().equals("Rectangle")){
                element.add(plotRect.get(entry.getKey()).toString().split("#")[0]);
                element.add(plotRect.get(entry.getKey()).toString().split("#")[1]);
                element.add(plotRect.get(entry.getKey()).toString().split("#")[2]);
                element.set(4, plotRect.get(entry.getKey()).toString().split("#")[3]);
                element.set(5, plotRect.get(entry.getKey()).toString().split("#")[4]);
                element.set(2, "Rectangle");
                
                rowData[c] = element.toArray();
                c++;
               // System.out.println("rd  : "+rowData[i].toString());
                element.clear();
            //}else{
            //    c++;
             //   element.clear();
            //}
        }
        /*
        for(int c = 0; c < wrappingRectanglesAttributes.size(); c++){
            tempRow = (Object[]) wrappingRectanglesAttributes.get("R"+c);
            element = new ArrayList(Arrays.asList(tempRow));
            element.add(plotRect.get("R"+c).toString().split("#")[0]);
            element.add(plotRect.get("R"+c).toString().split("#")[1]);
            element.add(plotRect.get("R"+c).toString().split("#")[2]);
            element.set(4, plotRect.get("R"+c).toString().split("#")[3]);
            element.set(5, plotRect.get("R"+c).toString().split("#")[4]);
            element.set(2, "Rectangle");
            //System.out.print(element.toString());
            rowData[c] = element.toArray();
           // System.out.println("rd  : "+rowData[i].toString());
            element.clear();
            
        }*/
        Iterator<Entry> entrySetIterator2 = entrySet1.iterator();
        
        
        /************ 
         Loop for actual shapes in green
         ************/
        while(entrySetIterator2.hasNext()){
            
            Entry entry = entrySetIterator2.next();
            tempRow = (Object[]) wrappingRectanglesAttributes.get(entry.getKey());
            element = new ArrayList(Arrays.asList(tempRow));
            element.add(String.valueOf(Float.parseFloat(plotRect.get(entry.getKey()).toString().split("#")[0]) + getClearAdjustment((String)tempRow[14])));
            element.add(String.valueOf(Float.parseFloat(plotRect.get(entry.getKey()).toString().split("#")[1]) + getClearAdjustment((String)tempRow[14])));
            //element.add(plotRect.get(entry.getKey()).toString().split("#")[2]);
            element.add("green");
            
            //System.out.print(element.toString());
            rowData[c] = element.toArray();
            c++;
           // System.out.println("rd  : "+rowData[i].toString());
            element.clear();
        }
        /*
        while(!wrappingRectanglesAttributes.isEmpty()){
            //String a = (String)wrappingRectanglesAttributes.get("R"+i);
            tempRow = (Object[]) wrappingRectanglesAttributes.get("R"+i);
            element = new ArrayList(Arrays.asList(tempRow));
            element.add(plotRect.get("R"+i).toString().split("#")[0]);
            element.add(plotRect.get("R"+i).toString().split("#")[1]);
            element.add("green");
            //System.out.println(a.toString());
            
            rowData[i1] = element.toArray();
            //container.add(element);
            element.clear();
            wrappingRectanglesAttributes.remove("R"+i);
            i++;
            i1++;
        }
        */
        return rowData;   
    }
    
    public void raiseError(String msg){
        System.out.print(msg);
    }
    
    public float getClearAdjustment(String glassType){
        Object a[] = (Object[])glassData.get(glassType);
        return Float.parseFloat((String)a[4]);
    }
    public float getBurnAdjustmentHeight(String shape){
        return (float)0.0;
    }
}
