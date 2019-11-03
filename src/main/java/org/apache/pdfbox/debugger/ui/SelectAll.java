package org.apache.pdfbox.debugger.ui;

import java.io.File;

import javax.swing.JFileChooser;    

/**  
 * @author yinxm  
 * @version 1.0 2005/06/17  
 *   
 * This class can take file's path and file's name;  
 * you must give the path where you want to take the file.  
 */ 
public class SelectAll {     
 
 public static void main(String[] args) {    
 // This is the path where the file's name you want to take.  
     new SelectAll();    
     String[] str = new String[]{};
     str =  SelectAll.selectF();
     
     for(int i = 0; i<str.length;i++){
    	System.out.println(str[i]);
     }
     
	// String path = "F:\\ttt";
    //    getFile(path);   
    }   
 
 public static String[] selectF(){
		
		JFileChooser chooser=new JFileChooser();
		String[] str = new String[]{};
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = chooser.showOpenDialog(chooser);
		if(result ==JFileChooser.APPROVE_OPTION){			
			str = getFile(chooser.getSelectedFile().getAbsolutePath());			
		}
		return str;
	}
 
 private static String[] getFile(String path){    
       // get file list where the path has  
        File file = new File(path);   
       // get the folder list  
        File[] array = file.listFiles();   
        
        String[] str = new String[array.length];
 
 for(int i=0;i<array.length;i++){    
 if(array[i].isFile()){   
	 			str[i] = array[i].getPath();
             //   System.out.println(array[i].getPath());
                
            }else if(array[i].isDirectory()){    
                getFile(array[i].getPath());   
            }   
        }
 	return str;
    }   
}   