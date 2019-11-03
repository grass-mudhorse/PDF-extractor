package org.apache.pdfbox.debugger.ui;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class SetKeyword {
	public SetKeyword(String path,String str){
		File f = new File(path);
		try {
			PDDocument pd = PDDocument.load(f);			
			PDDocumentInformation pdd =pd.getDocumentInformation();
			pdd.setKeywords(str);
			pd.save(path);
			pd.close();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}
}
