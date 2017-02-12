/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author vinay bhutada
 */
public class GeneratePreview {

    private BufferedImage surface;
    private JLabel surfacePlotter;

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
    private float d9;
    private float d10;
    private float positionX;
    private float positionY;
    private Color colorToPlot;
    private int count = 1;

    public GeneratePreview() {

        surface = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        surfacePlotter = new JLabel(new ImageIcon(getSurface()));
        Graphics g = surface.getGraphics();
        //g.setColor(Color.WHITE);
        //g.fillRect(0,0,500,500);
        //g.setColor(Color.BLACK);
        // Keep this until I figured out if it's painted on load or not.
        //g.drawLine(10, 20, 350, 380);
        //g.dispose();

    }

    public void initializeElements(Object row[]) {
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
            d7 = Float.parseFloat((String) rowData[7]);
            d8 = Float.parseFloat((String) rowData[8]);
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

    public void paintComponent() {
        Graphics g = surface.getGraphics();
        Graphics2D g2D = (Graphics2D) g;
        Path2D.Float pathObj = null;
        //pathObj.moveTo(300.0f, 270.0f);
        //pathObj.curveTo(400.0f, 270.0f,400.0f, 70.0f,500.0f, 70.0f);
        //g2D.draw(pathObj);
        AffineTransform transform = new AffineTransform();
        transform.scale(4.5, -4.5);
        transform.translate(0, -100);
        g2D.setTransform(transform);
        g2D.setStroke(new BasicStroke(0));
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 2));
        switch (this.shape) {
            case "Rectangle":
                g2D.setColor(colorToPlot);
                g2D.draw(new Rectangle2D.Double(positionX, positionY, d1, d2));

                if (colorToPlot.equals(Color.WHITE)) {
                    String string = "1-" + count;
                    int stringX = (int) (positionX + (d1 / 2) - (0.1 * d1));
                    int stringY = (int) (positionY + (d2 / 2));
                    g2D.drawString(string, stringX, stringY);
                    count++;
                }

                break;

            case "Triangle":
                g2D.setColor(colorToPlot);
                //g2D.drawPolygon(new int[] {10, 0, 30}, new int[] {100, 20, 100}, 3);
                if (d2 > 0.00) {
                    //float hypotenuse = (float) Math.sqrt((d1*d1)+(d2*d2));
                    //g2D.drawPolygon(new int[] {positionX, positionX, positionX+Math.round(d1)}, new int[] {positionY, positionY+Math.round(d2) , positionY+Math.round(d2)}, 3);                    
                    // g2D.draw(new Polygon(new int[] {positionX, positionX, positionX+Math.round(d1)}, new int[] {positionY, positionY+Math.round(d2) , positionY+Math.round(d2)}, 3));
                } else {
                    if (d3 > 0.00) {
                        //float hypotenuse = (float) Math.sqrt((d1*d1)+(d3*d3));
                        // g2D.drawPolygon(new int[] {positionX+Math.round(d1), positionX, positionX+Math.round(d1)}, new int[] {positionY, positionY+Math.round(d3) , positionY+Math.round(d3)}, 3);                    
                    }
                }

                break;
            case "Radius_Rectangle":
                //d1-width, d2-height, d3-radius, d4-code0, d5-code1, d6-code2, d7-code4
                g2D.setColor(colorToPlot);

                if (d7 > 0) {
                    g2D.draw(new RoundRectangle2D.Double(positionX, positionY, d1, d2, d3, d3));
                } else {
                    if (d6 > 0) {
                        pathObj = new Path2D.Float();
                        pathObj.moveTo(positionX, positionY);
                        pathObj.lineTo(positionX + d1 - d3, positionY);
                        pathObj.curveTo(positionX + d1, positionY, positionX + d1, positionY, positionX + d1, positionY + d3);
                        pathObj.lineTo(positionX + d1, positionY + d2 - d3);
                        pathObj.curveTo(positionX + d1, positionY + d2, positionX + d1, positionY + d2, (positionX + d1) - (d3), positionY + d2);
                        pathObj.lineTo(positionX, positionY + d2);
                        pathObj.closePath();
                        g2D.draw(pathObj);
                    } else {
                        if (d5 > 0) {
                            pathObj = new Path2D.Float();
                            pathObj.moveTo(positionX, positionY);
                            pathObj.lineTo(positionX + d1 - d3, positionY);
                            pathObj.curveTo(positionX + d1, positionY, positionX + d1, positionY, positionX + d1, positionY + d3);
                            pathObj.lineTo(positionX + d1, positionY + d2);
                            pathObj.lineTo(positionX, positionY + d2);
                            pathObj.closePath();
                            g2D.draw(pathObj);
                        } else {
                            if (d4 > 0) {
                                g2D.draw(new Rectangle2D.Double(positionX, positionY, d1, d2));
                            }
                        }
                    }
                }

                break;
            case "Trapezoid":
                g2D.setColor(colorToPlot);
                //d1 - top, d2 - height, d3- base
                float offset = (d3 - d1) / 2;
                // g2D.drawPolygon(new int[] {positionX+Math.round(offset), positionX, positionX+Math.round(d3), positionX+Math.round(d3)-Math.round(offset)}, new int[] {positionY, positionY+Math.round(d2) , positionY+Math.round(d2), positionY}, 4);                    

                break;
            case "Circle":
                g2D.setColor(colorToPlot);
                g2D.draw(new Ellipse2D.Double(positionX, positionY, d1, d1));

                break;
            case "Parallelogram":
                //d1-height, d2-base, d3-offset, d4-code1, d5-code2
                g2D.setColor(colorToPlot);
                if (d4 > 0) {
                    //  g2D.drawPolygon(new int[] {positionX+Math.round(d3), positionX, positionX+Math.round(d2), positionX+Math.round(d2)+Math.round(d3)}, new int[] {positionY, positionY+Math.round(d1), positionY+Math.round(d1), positionY}, 4);
                } else {
                    if (d5 > 0) {
                        //      g2D.drawPolygon(new int[] {positionX, positionX+Math.round(d3),  positionX+Math.round(d2)+Math.round(d3), positionX+Math.round(d2)}, new int[] {positionY, positionY+Math.round(d1), positionY+Math.round(d1), positionY}, 4);
                    }
                }

                break;

            case "Polygon": // implemented later

                break;
            case "Single_Circle_Top":
                //d1 - width, d2 - height
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX + d1 / 2, positionY);

                pathObj.quadTo(positionX + d1, positionY, positionX + d1, positionY + d1 / 2);
                pathObj.lineTo(positionX + d1, positionY + d2);
                pathObj.lineTo(positionX, positionY + d2);
                pathObj.lineTo(positionX, positionY + d1 / 2);
                pathObj.quadTo(positionX, positionY, positionX + d1 / 2, positionY);

                g2D.draw(pathObj);

                break;

            case "Double_Circle_Top":
                //d1-width, d2-length
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX + d1 / 2, positionY);

                pathObj.quadTo(positionX + d1, positionY, positionX + d1, positionY + d1 / 2);
                pathObj.lineTo(positionX + d1, positionY + d2 - (d1 / 2));
                pathObj.quadTo(positionX + d1, positionY + d2, positionX + d1 / 2, positionY + d2);
                pathObj.quadTo(positionX, positionY + d2, positionX, positionY + d2 - d1 / 2);
                pathObj.lineTo(positionX, positionY + d1 / 2);
                pathObj.quadTo(positionX, positionY, positionX + d1 / 2, positionY);

                g2D.draw(pathObj);

                break;
            case "Semi-Circle":
                g2D.setColor(colorToPlot);
                g2D.draw(new Arc2D.Double(positionX, positionY, d1, d1, 0, 180, Arc2D.CHORD));

                break;
            case "Ellipse":
                g2D.setColor(colorToPlot);
                g2D.draw(new Ellipse2D.Double(positionX, positionY, d1, d2));

                break;
            case "Arch":
                //d1-base, d2-height, d3-oah
                g2D.setColor(colorToPlot);
                offset = d3 - d2;
                pathObj = new Path2D.Float();
                //pathObj.moveTo(positionX+d1/2, positionY);
                pathObj.moveTo(positionX + d1, positionY + offset);
                //pathObj.quadTo(positionX+d1, positionY, positionX+d1, positionY+offset);
                pathObj.lineTo(positionX + d1, positionY + d3);
                pathObj.lineTo(positionX, positionY + d3);
                pathObj.lineTo(positionX, positionY + offset);
                //pathObj.quadTo(positionX, positionY, positionX+d1/2, positionY);
                pathObj.curveTo(positionX, positionY + offset, positionX + d1 / 2, positionY, positionX + d1, positionY + offset);
                g2D.draw(pathObj);

                break;
            case "Gothic": //to be implemented later
                //d1-base, d2-height, d3-radius, d4-oah
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX, positionY + d4);
                pathObj.lineTo(positionX + d1, positionY + d4);
                pathObj.curveTo(positionX + d1, positionY + d4, positionX + d1, positionY, positionX + d1 / 2, positionY);
                pathObj.curveTo(positionX + d1 / 2, positionY, positionX, positionY, positionX, positionY + d4);
                //pathObj.quadTo(positionX+d1, positionY, positionX+d1/2, positionY);
                //pathObj.quadTo(positionX, positionY, positionX, positionY+d4);
                g2D.draw(pathObj);
                break;
            case "Semi-Ellipse":
                //d1-minor, d2-major
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX, positionY + d1);
                pathObj.quadTo(positionX, positionY, positionX + d2 / 2, positionY);
                pathObj.quadTo(positionX + d2, positionY, positionX + d2, positionY + d1);
                pathObj.lineTo(positionX, positionY + d1);

                g2D.draw(pathObj);
                break;
            case "Shape_13": // to be implemented later like arch
                //d1-height, d2-base, d3-oah
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX, positionY + d3);
                pathObj.lineTo(positionX + d2, positionY + d3);
                pathObj.curveTo(positionX + d2, positionY + d3, positionX + d2 / 2, positionY, positionX, positionY + d3);
                g2D.draw(pathObj);

                break;
            case "Double_Triangle_Top":
                //d1-x1, d2-x2, d3-y1, d4-y2, d5-y3
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX + d2, positionY);
                pathObj.lineTo(positionX + d1, positionY + (d3 - d5) / 2);
                pathObj.lineTo(positionX + d1, positionY + d5 + (d3 - d5) / 2);
                pathObj.lineTo(positionX + d2, positionY + d3);
                pathObj.lineTo(positionX, positionY + d4 + (d3 - d4) / 2);
                pathObj.lineTo(positionX, positionY + (d3 - d4) / 2);
                pathObj.closePath();
                g2D.draw(pathObj);
                break;
            case "Single_Triangle_Top":
                //d1-x1, d2-x2, d3-y1, d4-y2, d5-y3
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                pathObj.moveTo(positionX + d2, positionY);
                pathObj.lineTo(positionX + d1, positionY + d3 - d5);
                pathObj.lineTo(positionX + d1, positionY + d3);
                pathObj.lineTo(positionX, positionY + d3);
                pathObj.lineTo(positionX, positionY + d3 - d4);
                pathObj.closePath();
                g2D.draw(pathObj);

                break;
            case "Rake":
                //d1-Lh, d2-Rh, d3-base
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                if (d1 >= d2) {
                    pathObj.moveTo(positionX, positionY);
                    pathObj.lineTo(positionX, positionY + d1);
                    pathObj.lineTo(positionX + d3, positionY + d1);
                    pathObj.lineTo(positionX + d3, positionY + d1 - d2);
                    pathObj.closePath();
                } else {
                    pathObj.moveTo(positionX + d3, positionY);
                    pathObj.lineTo(positionX + d3, positionY + d2);
                    pathObj.lineTo(positionX, positionY + d2);
                    pathObj.lineTo(positionX, positionY + d2 - d1);
                    pathObj.closePath();
                }

                g2D.draw(pathObj);

                break;
            case "Clipped_Corner":
                //d1-width, d2-height, d3-clip, d4 - code1, d5-code2, d6-code4
                g2D.setColor(colorToPlot);
                pathObj = new Path2D.Float();
                offset = d3 * 2;
                if (d6 > 0) {
                    pathObj.moveTo(positionX + d3, positionY);
                    pathObj.lineTo(positionX + d1 - d3, positionY);
                    pathObj.lineTo(positionX + d1, positionY + d3);
                    pathObj.lineTo(positionX + d1, positionY + d2 - d3);
                    pathObj.lineTo(positionX + d1 - d3, positionY + d2);
                    pathObj.lineTo(positionX + d3, positionY + d2);
                    pathObj.lineTo(positionX, positionY + d2 - d3);
                    pathObj.lineTo(positionX, positionY + d3);
                    pathObj.closePath();
                } else {
                    if (d5 > 0) {
                        pathObj.moveTo(positionX + d3, positionY);
                        pathObj.lineTo(positionX + d1 - d3, positionY);
                        pathObj.lineTo(positionX + d1, positionY + d3);
                        pathObj.lineTo(positionX + d1, positionY + d2);
                        pathObj.lineTo(positionX, positionY + d2);
                        pathObj.lineTo(positionX, positionY + d3);
                        pathObj.closePath();
                    } else {
                        pathObj.moveTo(positionX + d3, positionY);
                        pathObj.lineTo(positionX + d1, positionY);
                        pathObj.lineTo(positionX + d1, positionY + d2);
                        pathObj.lineTo(positionX, positionY + d2);
                        pathObj.lineTo(positionX, positionY + d3);
                        pathObj.closePath();
                    }
                }

                g2D.draw(pathObj);

                break;
            case "Quadrilateral":

                break;
            case "Donut": //to implement edge to connect circles
                //d1-r1, d2-r2, d3-angle
                offset = (d1 - d2) / 2;
                g2D.setColor(colorToPlot);
                g2D.draw(new Arc2D.Double(positionX, positionY, d1, d1, 0, d3, Arc2D.PIE));
                g2D.draw(new Arc2D.Double(positionX + offset, positionY + offset, d2, d2, 0, d3, Arc2D.OPEN));

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
                g2D.setColor(colorToPlot);
                g2D.draw(new Rectangle2D.Double(positionX, positionY, d1, d2));
                if (d5 > 0) {
                    g2D.setColor(Color.YELLOW);
                    g2D.draw(new Line2D.Double(d5, 0, d5, d2));
                }
                if (d8 > 0) {
                    g2D.setColor(Color.YELLOW);
                    g2D.draw(new Line2D.Double(d5, d8, d1 - d7, d8));
                }
                if (d7 > 0) {
                    g2D.setColor(Color.YELLOW);
                    g2D.draw(new Line2D.Double(d1 - d7, 0, d1 - d7, d2 - d8));
                }
                if (d6 > 0) {
                    g2D.setColor(Color.YELLOW);
                    g2D.draw(new Line2D.Double(d5, d2 - d6, d1, d2 - d6));
                }

                break;
        }

        g2D.dispose();
        surfacePlotter.repaint();
    }

    /**
     * @return the surface
     */
    public BufferedImage getSurface() {
        return surface;
    }

    /**
     * @return the surfacePlotter
     */
    public JLabel getSurfacePlotter() {
        return surfacePlotter;
    }

}
