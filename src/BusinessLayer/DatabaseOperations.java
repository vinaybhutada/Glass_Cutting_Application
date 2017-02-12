
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLayer;

import PresentationLayer.OrderEntryTab;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/*
 * @author vinay bhutada
 */
public class DatabaseOperations {

//    ObjectOutputStream oos = null;
//    ObjectInputStream ois = null;
//    FileOutputStream fout = null;
//    FileInputStream fin = null;
    Object[][] rowData = null;
    FileWriter fwObj = null;
    FileReader frObj = null;
    BufferedReader br = null;
    String str = null;
    String temp = "";

    public DatabaseOperations() {
    }

    ;
    
    public DatabaseOperations(String filePath, String fileName) throws IOException {
        //create and write Glass database object
        try {
            File writeToFile = new File(filePath + fileName);
            if (writeToFile.createNewFile()) {
                Object glassDb[][] = {{"G1", 1, 2, 2, "Y", 0, 12, 12, 12, 12, 10, 10}, {"G2", 1, 2, 2, "N", 0, 123, 123, 123, 123, 10, 10}};
                writeToFile(glassDb, filePath, fileName, false);
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            if (fwObj != null) {
                fwObj.close();
            }
        }

    }

    ;
    
    
    public void writeToFile(Object fileRow[][], String filePath, String fileName, boolean overwrite) throws IOException {
        try {
            str = new String();
            File writeToFile = new File(filePath + fileName);
            /*if(writeToFile.createNewFile()){
                System.out.print("File doesn't exists!");
            }else{
                System.out.print("File exists!");
            }*/
            if (writeToFile.createNewFile()) {
                fwObj = new FileWriter(writeToFile);
                for (int i = 0; i < fileRow.length; i++) {
                    str = "";
                    for (int j = 0; j < fileRow[i].length; j++) {
                        str = str + fileRow[i][j] + "\t";
                    }
                    fwObj.write(str + "\n");
                }
            } else if (overwrite) {
                fwObj = new FileWriter(writeToFile);
                for (int i = 0; i < fileRow.length; i++) {
                    str = "";
                    for (int j = 0; j < fileRow[i].length; j++) {
                        str = str + fileRow[i][j] + "\t";
                    }
                    fwObj.write(str + "\n");
                }
            } else {

                frObj = new FileReader(filePath + fileName);
                br = new BufferedReader(frObj);
                str = new String();
                while ((temp = br.readLine()) != null) {
                    str = str + temp + "\n";
                }

                fwObj = new FileWriter(writeToFile);
                for (int i = 0; i < fileRow.length; i++) {
                    if (i != 0) {
                        str = "";
                    }
                    for (int j = 0; j < fileRow[i].length; j++) {
                        str = str + fileRow[i][j] + "\t";
                    }
                    fwObj.write(str + "\n");
                }
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            if (fwObj != null) {
                fwObj.flush();
                fwObj.close();
            }
        }
    }

    public void writeToFileForGCode(Object fileRow[], String filePath, String fileName, boolean overwrite) throws IOException {
        try{
        File writeToFile = new File(filePath + fileName);
        /*if(writeToFile.createNewFile()){
                System.out.print("File doesn't exists!");
            }else{
                System.out.print("File exists!");
            }*/
        //if (writeToFile.createNewFile()) {
            fwObj = new FileWriter(writeToFile);
            for (int i = 0; i < fileRow.length; i++) {
                fwObj.write(fileRow[i] + "\n");
            }
        }
        catch(Exception e){
        
        }
        finally{
        if (fwObj != null) {
                fwObj.flush();
                fwObj.close();
            }
        }
       // }
    }

    public Object[][] readFromFile(String filePath, String fileName) throws IOException {
        try {

            File checkFile = new File(filePath + fileName);
            if (!checkFile.createNewFile()) {
                frObj = new FileReader(filePath + fileName);
                br = new BufferedReader(frObj);
                str = new String();
                ArrayList<String> parseString = new ArrayList();
                while ((str = br.readLine()) != null) {
                    parseString.add(str);
                }
                rowData = new Object[parseString.size()][];
                for (int i = 0; i < parseString.size(); i++) {
                    String removeMultipleSpaces = parseString.get(i).trim().replaceAll("\\s+", " ");
                   
                    String[] splitStr = removeMultipleSpaces.split(" ");
                    rowData[i] = new Object[splitStr.length];
                    for (int j = 0; j < splitStr.length; j++) {
                        rowData[i][j] = splitStr[j];

                    }

                }
                return rowData;
            } else {
                JOptionPane.showMessageDialog(null, "File Does Not Exist");
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } finally {
            if (frObj != null) {
                frObj.close();
            }
            return rowData;
        }

    }

    public File[] fetchAllFiles(String filePath) {
        File allFiles = new File(filePath);
        File[] listOfFiles = allFiles.listFiles();
        return listOfFiles;
    }

    public int getFilesCount(String filePath) {
        File file[] = new File(filePath).listFiles();
        return file.length;
    }

    public void removeFile(String filePath, List list) {
        for (Object obj : list) {
            File file = new File(filePath + obj);
            file.delete();
        }
    }

    public void removeAllFiles(String filePath) {
        File file[] = new File(filePath).listFiles();
        for (File delFile : file) {
            delFile.delete();
        }
    }

}
