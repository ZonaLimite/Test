package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author F.J. BOGA
 * Prepara el fichero pdu para el lanzamiento del Informe
 * Parametros : Nombre del Informe (Predefinido o Directo)
 *              DescriptorFile nombre base del fichero de definiciones de parametros del Informe
 *              Tipo de formato del Reporte (Excel o HTML)
 *              Confirmacion de envio a pasarela SIE (True o False)
 */
public class WritePDU {
	String nameReport;
	String descriptorFile;
	String formatoReport;
	String okSIE;
	String pathPduFile;
		
	public WritePDU(String name, String descrip, String formatR, String oksie) {
		this.nameReport = name;
		this.descriptorFile = descrip;
		if(formatR.equals("xls"))this.formatoReport="8";
		if(formatR.equals("html"))this.formatoReport="2";
		this.okSIE=oksie;
		HandleProperties hp = new HandleProperties();
		this.pathPduFile=hp.leeProperties("pathPduFile");
		this.writeFile(this.pathPduFile);
	}

	public void writeFile(String pathNameFile) {
		try {
			File f = new File(pathNameFile);
			f.delete();
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("####################################################");bw.newLine();
			bw.write("#");bw.newLine();
			bw.write("# d:\\MIS\\ReportManager\\Data\\my_export.pdu ");bw.newLine();
			bw.write("# Automatically generates by mi");bw.newLine();
			bw.write("####################################################");bw.newLine();
			bw.newLine();
			bw.write("PduParse v1.0");bw.newLine();
			bw.write("ADDRESS 127.0.0.1");bw.newLine();
			bw.write("PORT 7550");bw.newLine();
			bw.write("PDUTYPE 116");bw.newLine();
			bw.newLine();
			bw.write("# Parameters");bw.newLine();
			bw.write("STRING \"PREDEFINED\"");bw.newLine();
			bw.write("STRING \""+this.nameReport+"\"");bw.newLine();
			bw.write("STRING \""+this.descriptorFile+"\"");bw.newLine();
			bw.write("INT32 "+this.formatoReport);bw.newLine();
			bw.write("STRING \""+this.okSIE+"\"");
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

