package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class CallerRunSystem {
	public String LlamarNotifier(String pdu) {
		String result="";
		try
        {
            // Se lanza el ejecutable.
			//Se obtiene propiedad de ruta acceso a notifier
			HandleProperties hp = new HandleProperties();
			String pathNotifier = hp.leeProperties("executorNotifier");
					
			String llamada =pathNotifier + pdu;
			System.out.println("LLamada es :"+llamada);
            Process p=Runtime.getRuntime().exec (llamada);
            System.out.println("ejecutada llamada");
            result="Ejecutado";
            
            // Se obtiene el stream de salida del programa
            InputStream is = p.getInputStream();
            
            /* Se prepara un bufferedReader para poder leer la salida más comodamente. */
            BufferedReader br = new BufferedReader (new InputStreamReader (is));
            
            // Se lee la primera linea
            String aux = br.readLine();
            
            // Mientras se haya leido alguna linea
            while (aux!=null)
            {
                // Se escribe la linea en pantalla
                System.out.println (aux);
                
                // y se lee la siguiente.
                aux = br.readLine();
            }
        } 
        catch (Exception e)
        {
            // Excepciones si hay algún problema al arrancar el ejecutable o al leer su salida.*/
            e.printStackTrace();
            return e.getMessage();
        }
    
		
		return result;
	}
	public static void main(String[] args) {
       CallerRunSystem caller;//Modificada

			caller = new CallerRunSystem();
			caller.LlamarNotifier(" d:\\MIS\\ReportManager\\Data\\myExport.pdu");

	}
}
