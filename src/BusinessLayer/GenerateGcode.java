package BusinessLayer;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author vinay bhutada
 */
public class GenerateGcode {

    private ArrayList finalGcode = new ArrayList();

    private Object rowData[];
    private String glassType;
    private String shape;
    private int quantity;
    private float d1;
    private float d2;
    private float d3;
    private float d4;
    private float d5;
    private float d6;
    private float d7;
    private float d8;
    private float positionX;
    private float positionY;
    private Color colorToPlot;
    private int count = 1;

    public GenerateGcode() {
    }

    public void initializeElements(Object[] row) {
        rowData = row;
        if (rowData.length < 17) {
            glassType = (String) rowData[0];
            shape = "";
            quantity = 1;
            d1 = Float.parseFloat((String) rowData[1]);
            d2 = Float.parseFloat((String) rowData[2]);
            d3 = Float.parseFloat((String) rowData[3]);
            d4 = Float.parseFloat((String) rowData[4]);
            d5 = Float.parseFloat((String) rowData[5]);
            d6 = Float.parseFloat((String) rowData[6]);
            d7 = (float) 0.00;
            d8 = (float) 0.00;
            positionX = 0;
            positionY = 0;
            colorToPlot = Color.RED;
        } else {
            glassType = (String) rowData[14];
            shape = (String) rowData[2];
            quantity = Integer.parseInt((String) rowData[3]);
            d1 = Float.parseFloat((String) rowData[4]);
            d2 = Float.parseFloat((String) rowData[5]);
            d3 = Float.parseFloat((String) rowData[6]);
            d4 = Float.parseFloat((String) rowData[7]);
            d5 = Float.parseFloat((String) rowData[8]);
            d6 = Float.parseFloat((String) rowData[9]);
            d7 = Float.parseFloat((String) rowData[10]);
            d8 = Float.parseFloat((String) rowData[11]);
            positionX = Float.parseFloat((String) rowData[17]);
            positionY = Float.parseFloat((String) rowData[18]);
            //colorToPlot = Color.WHITE;
            if (rowData[19].equals("white")) {
                colorToPlot = Color.WHITE;
            } else {
                if (rowData[19].equals("green")) {
                    colorToPlot = Color.GREEN;
                } else {
                    if (rowData[19].equals("yellow")) {
                        colorToPlot = Color.YELLOW;
                    }
                }
            }

        }
    }
    
    public void beginSheet(){
        getFinalGcode().add("----sheet 1 info----");
        getFinalGcode().add("F4000");
        getFinalGcode().add("M101");
        getFinalGcode().add("M102");
        getFinalGcode().add("M5");
    }
    
    public void endSheet(){
        getFinalGcode().add("G0 X0 Y0");
        getFinalGcode().add("M300");
        getFinalGcode().add("M30");
        getFinalGcode().add("(End Current Sheet)");
    }
    
    public void getGcodeForShape(){
        switch(this.shape){
            case "Rectangle":
                //d1-width, d2-height
                getFinalGcode().add("G0 X" + positionX + " Y" + positionY);
                getFinalGcode().add("M3");
                getFinalGcode().add("G1 X" + (positionX) + " Y" + (positionY+d2));
                getFinalGcode().add("G1 X" + (positionX+d1) + " Y" + (positionY+d2));
                getFinalGcode().add("G1 X" + (positionX+d1) + " Y" + (positionY));
                getFinalGcode().add("G1 X" + (positionX) + " Y" + (positionY));
                getFinalGcode().add("M5");
                break;
                
            case "Triangle":
                
                break;
            case "Radius_Rectangle": 
                //d1-width, d2-height, d3-radius, d4-code0, d5-code1, d6-code2, d7-code4
                
                
                
                break;
            case "Trapezoid":
                
                break;
            case "Circle":
                //d1-diameter
                getFinalGcode().add("G0 X" + (positionX+(d1/2)) + " Y" +positionY);
                getFinalGcode().add("M3");
                getFinalGcode().add("G2 X" + (positionX+(d1/2)) + " Y" + (positionY) + " I" + ("0.0") + " J" + (d1/2));
                getFinalGcode().add("M5");
                break;
            case "Parallelogram":
                //d1-height, d2-base, d3-offset, d4-code1, d5-code2
                
                
                
                break;
                
            case "Polygon": // implemented later
                
                

                break;
            case "Single_Circle_Top":
                //d1 - width, d2 - height
                                    
                                
                break;
                
            case "Double_Circle_Top":
                //d1-width, d2-length
                                    
                
                
                break;
            case "Semi-Circle":
                
                
                
                
                break;
            case "Ellipse":
                
                break;
            case "Arch":
                //d1-base, d2-height, d3-oah
                                    
               
                break;
            case "Gothic": //to be implemented later
                //d1-base, d2-height, d3-radius, d4-oah
                                  
                break;
            case "Semi-Ellipse":
                //d1-minor, d2-major
                break;
            case "Shape_13": // to be implemented later like arch
                //d1-height, d2-base, d3-oah
                
                break;
            case "Double_Triangle_Top":
                //d1-x1, d2-x2, d3-y1, d4-y2, d5-y3
                break;
            case "Single_Triangle_Top":
                                //d1-x1, d2-x2, d3-y1, d4-y2, d5-y3
                
                break;
            case "Rake":
                //d1-Lh, d2-Rh, d3-base
                
                break;
            case "Clipped_Corner":
                //d1-width, d2-height, d3-clip, d4 - code1, d5-code2, d6-code4
                
                
                break;
            case "Quadrilateral":
                
               
                break;
            case "Donut": //to implement edge to connect circles
                //d1-r1, d2-r2, d3-angle
                
               
                break;
            case "Quarter Arc":
                
                
                break;
            case "Extended Quarter Arc":
                
                
               
                break;
            case "Half Arc":
                
                
               
                break;
            case "Extended Half Arc":
                
                
               
                break;
            default:
                
                break;
        }
    }

    /**
     * @return the finalGcode
     */
    public ArrayList getFinalGcode() {
        return finalGcode;
    }

    /**
     * @param finalGcode the finalGcode to set
     */
    public void setFinalGcode(ArrayList finalGcode) {
        this.finalGcode = finalGcode;
    }

}
