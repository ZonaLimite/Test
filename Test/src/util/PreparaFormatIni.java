package util;

import java.util.StringTokenizer;

public class PreparaFormatIni {
	String tipoFichero="";
	String parteMascara, parteFormato;
	public PreparaFormatIni() {

	}
	
	public String PreparaPduSIE(String maskName) {
		return "Pendiente";
		//(Pendiente)
	}

	  
////////////////////////////////////////////////
////     N O   S  I  E
////////////////////////////////////////////////
	public String PreparaDefNoSIE(String maskName) {
	  String result=null;
		
	  String Centro = null;
	  String ModeloInf = null;
	  String Maquina = null;
	  String Fecha = null;
	  String Turno = null;
	  String Programa = null;
	  String GroupBy = null;
	  String Idioma = null;


	  String tipoMachine = null;	
	  StringTokenizer stSepara2 = new StringTokenizer(maskName,".");	
	  while(stSepara2.hasMoreTokens()) {
		  parteMascara = stSepara2.nextToken();
		  parteFormato = stSepara2.nextToken();

	  }
	  //Conseguir Parametros NOSIE
	  StringTokenizer stMasc = new StringTokenizer(parteMascara,"-");
	  while(stMasc.hasMoreTokens()) {
		  Centro = stMasc.nextToken();
		  ModeloInf=stMasc.nextToken();
		  Maquina = stMasc.nextToken();
		  Fecha = stMasc.nextToken();
		  Turno = stMasc.nextToken();
		  Programa = stMasc.nextToken();
		  GroupBy = stMasc.nextToken();
		  Idioma = stMasc.nextToken();
	  }
	  //Preparar el fichero de propiedades myMachines_def y Definiciones
	  ManejaInis machinesIni = new ManejaInis("myMachines_def.ini");
	  ManejaInis definicionesIni = new ManejaInis("Definiciones.ini");
	  ManejaInis controlGroupByIni = new ManejaInis("ReportDef.ini");


		  //Asignar Equipement
		  	
		  		if(ModeloInf.contains("TOP"))tipoMachine="TOP";
		  		if(ModeloInf.contains("CSU"))tipoMachine="VCS";
		  		if(ModeloInf.contains("ITLS"))tipoMachine="ITLS";
		  		if(ModeloInf.contains("DISPO"))tipoMachine="DISPO";
		  		if(ModeloInf.contains("CMT"))tipoMachine="CMT";
		  		if(ModeloInf.contains("CMI"))tipoMachine="CMI";
		  		if(ModeloInf.contains("IRV"))tipoMachine="IRV";
		  		if(ModeloInf.contains("MIS_M"))tipoMachine="MIS_M";
		  		if(ModeloInf.contains("SIE_C"))tipoMachine="SIE_C";
		  		if(ModeloInf.contains("ARCHIVE"))tipoMachine="ARCHIVE";
		  		machinesIni.putKeySeccion(tipoMachine, "Equipement",tipoMachine );
		 if(tipoMachine.equals("TOP")){
///////////////////////////////////////////////
//              TOP
///////////////////////////////////////////////		  		
		 //Asignar Subject = 1
				machinesIni.putKeySeccion(tipoMachine, "Subject","1");		
				  			  
         //Asignar Fecha
	 
		  		StringTokenizer stFecha = new StringTokenizer(Fecha,"_");
		  		while(stFecha.hasMoreTokens()) {
		  			String parteIniFecha = stFecha.nextToken();
		  			String Año = parteIniFecha.substring(4, 8);
		  			String Mes = parteIniFecha.substring(2, 4);
		  			String Dia = parteIniFecha.substring(0, 2);
		  			String Hora = parteIniFecha.substring(8, 12);
		  			String maskIni= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateStart", maskIni);
		  			
		  			String parteFinFecha = stFecha.nextToken();
		  			Año = parteFinFecha.substring(4, 8);
		  			Mes = parteFinFecha.substring(2, 4);
		  			Dia = parteFinFecha.substring(0, 2);
		  			Hora = parteFinFecha.substring(8, 12);
		  			String maskFin= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateEnd", maskFin);
		  		}
		  
    	 // Asignar Maquinas MachineId
		  		String machineId = null;
		  		if (Maquina.equals("TM")) {
		  				machineId="-1";
		  		}else {
		  				machineId=Maquina;
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "MachineId", machineId);

		  //Asignar Shift
		  		String shift=null;
		  		if (Turno.equals("TT")) {
		  			shift = "-1";
		  		}else{
		  			shift = definicionesIni.getKeySeccion("Shift", Turno);

		  		}
	  			machinesIni.putKeySeccion(tipoMachine, "Shift", shift);

		  //Asignar Task
		  		String task=null;
		  		if(Programa.equals("TP")) {
		  			task="-1";
		  		}else {
		  			task=Programa;
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "Task", task);
		  		
		  //Asignar GroupBy
		  		String groupBy = definicionesIni.getKeySeccion("GroupBy", GroupBy);
		  		if(groupBy==null)return "Error: Mascara de Informe no valida en campo Group by";
		  		
		  		String valueGroupBy= controlGroupByIni.getKeySeccion("groupby."+tipoMachine, "value");
		  		
		  		//SI se comete un error con las posibles asignaciones groupBy
		  		//se asigna la primera posible
		  		
		  		if(valueGroupBy.contains(groupBy)) {
		  			
		  		}else {
		  			System.out.println("Esta agrupacion no existe, se elije defecto");
		  			StringTokenizer st=new StringTokenizer(valueGroupBy,",");
		  			if( (st.hasMoreTokens())) {
		  				groupBy = st.nextToken();
		  			}else {
		  				groupBy = valueGroupBy;
		  			}
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "GroupBy", groupBy);
		  		
          //Asignar Language
		  		String language = definicionesIni.getKeySeccion("Language", Idioma);
		  		machinesIni.putKeySeccion(tipoMachine, "Language", language);
		  		
          //Asignar Legende
		  		machinesIni.putKeySeccion(tipoMachine, "Legende", "");
		  		
		  //Asignar FileName 
		  		String fileName = definicionesIni.getKeySeccion("File", ModeloInf);
		  		machinesIni.putKeySeccion(tipoMachine, "FileName", fileName);
		  		
		  //Asignar Flag = 125
		  		machinesIni.putKeySeccion(tipoMachine, "Flag", "125");

	  }
	  if(tipoMachine.equals("VCS")){
		  ///////////////////////////////////////////////
		  //              VCS
		  ///////////////////////////////////////////////
		  		
		 //Asignar Subject = 1
				machinesIni.putKeySeccion(tipoMachine, "Subject","1");		
				  			  
         //Asignar Fecha
	 
		  		StringTokenizer stFecha = new StringTokenizer(Fecha,"_");
		  		while(stFecha.hasMoreTokens()) {
		  			String parteIniFecha = stFecha.nextToken();
		  			String Año = parteIniFecha.substring(4, 8);
		  			String Mes = parteIniFecha.substring(2, 4);
		  			String Dia = parteIniFecha.substring(0, 2);
		  			String Hora = parteIniFecha.substring(8, 12);
		  			String maskIni= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateStart", maskIni);
		  			
		  			String parteFinFecha = stFecha.nextToken();
		  			Año = parteFinFecha.substring(4, 8);
		  			Mes = parteFinFecha.substring(2, 4);
		  			Dia = parteFinFecha.substring(0, 2);
		  			Hora = parteFinFecha.substring(8, 12);
		  			String maskFin= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateEnd", maskFin);
		  		}
		  
    	 // Asignar Operator
		  		String operador = null;
		  		if (Maquina.equals("TM")) {
		  				operador="*";
		  		}else {
		  				operador=Maquina;
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "Operator", operador);

		  //Asignar GroupBy
		  		String groupBy = definicionesIni.getKeySeccion("GroupBy", GroupBy);
		  		if(groupBy==null)return "Error: Mascara de Informe no valida. En campo Group by";
		  		String valueGroupBy= controlGroupByIni.getKeySeccion("groupby."+tipoMachine, "value");
		  		
		  		//SI se comete un error con las posibles asignaciones groupBy
		  		//se asigna la primera posible
		  		
		  		if(valueGroupBy.contains(groupBy)) {
		  			
		  		}else {
		  			System.out.println("Esta agrupacion no existe, se elije defecto");
		  			StringTokenizer st=new StringTokenizer(valueGroupBy,",");
		  			if( (st.hasMoreTokens())) {
		  				groupBy = st.nextToken();
		  			}else {
		  				groupBy = valueGroupBy;
		  			}
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "GroupBy", groupBy);

          //Asignar Language
		  		String language = definicionesIni.getKeySeccion("Language", Idioma);
		  		machinesIni.putKeySeccion(tipoMachine, "Language", language);
		  		
          //Asignar Legende
		  		machinesIni.putKeySeccion(tipoMachine, "Legende", "");
		  		
		  //Asignar FileName 
		  		String fileName = definicionesIni.getKeySeccion("File", ModeloInf);
		  		machinesIni.putKeySeccion(tipoMachine, "FileName", fileName);
		  		
		  //Asignar Flag = 353
		  		machinesIni.putKeySeccion(tipoMachine, "Flag", "353");
	  }
	  if(tipoMachine.equals("ITLS")){
		  ///////////////////////////////////////////////
		  //              ITLS
		  ///////////////////////////////////////////////
		  		
		 //Asignar Subject = 1
				machinesIni.putKeySeccion(tipoMachine, "Subject","1");		
				  			  
         //Asignar Fecha
	 
		  		StringTokenizer stFecha = new StringTokenizer(Fecha,"_");
		  		while(stFecha.hasMoreTokens()) {
		  			String parteIniFecha = stFecha.nextToken();
		  			String Año = parteIniFecha.substring(4, 8);
		  			String Mes = parteIniFecha.substring(2, 4);
		  			String Dia = parteIniFecha.substring(0, 2);
		  			String Hora = parteIniFecha.substring(8, 12);
		  			String maskIni= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateStart", maskIni);
		  			
		  			String parteFinFecha = stFecha.nextToken();
		  			Año = parteFinFecha.substring(4, 8);
		  			Mes = parteFinFecha.substring(2, 4);
		  			Dia = parteFinFecha.substring(0, 2);
		  			Hora = parteFinFecha.substring(8, 12);
		  			String maskFin= Año+"/"+Mes+"/"+Dia+" "+ Hora.substring(0, 2)+":"+Hora.substring(2, 4);
		  			machinesIni.putKeySeccion(tipoMachine, "DateEnd", maskFin);
		  		}
		  
		  //Asignar GroupBy
		  		String groupBy = definicionesIni.getKeySeccion("GroupBy", GroupBy);
		  		if(groupBy==null)return "Error: Mascara de Informe no valida. En campo Group by";
		  		String valueGroupBy= controlGroupByIni.getKeySeccion("groupby."+tipoMachine, "value");
		  		
		  		//SI se comete un error con las posibles asignaciones groupBy
		  		//se asigna la primera posible
		  		
		  		if(valueGroupBy.contains(groupBy)) {
		  			
		  		}else {
		  			System.out.println("Esta agrupacion no existe, se elije defecto");
		  			StringTokenizer st=new StringTokenizer(valueGroupBy,",");
		  			if( (st.hasMoreTokens())) {
		  				groupBy = st.nextToken();
		  			}else {
		  				groupBy = valueGroupBy;
		  			}
		  		}
		  		machinesIni.putKeySeccion(tipoMachine, "GroupBy", groupBy);

          //Asignar Language
		  		String language = definicionesIni.getKeySeccion("Language", Idioma);
		  		machinesIni.putKeySeccion(tipoMachine, "Language", language);
		  		
          //Asignar Legende
		  		machinesIni.putKeySeccion(tipoMachine, "Legende", "");
		  		
		  //Asignar FileName 
		  		String fileName = definicionesIni.getKeySeccion("File", ModeloInf);
		  		machinesIni.putKeySeccion(tipoMachine, "FileName", fileName);
		  		
		  //Asignar Flag = 97
		  		machinesIni.putKeySeccion(tipoMachine, "Flag", "97");
	  }
	  
	  //Instanciar la escritura del fichero pdu
	  result = Centro+"-"+ModeloInf+"-"+Maquina+"-"+Fecha+"-"+Turno+"-"+Programa+"-"+GroupBy+"-"+Idioma;
	  return result;
		 
	}
    public static void main(String[] args){
    
       PreparaFormatIni pdu= new PreparaFormatIni();
       pdu.PreparaDefNoSIE(args[0]);
    }

}
