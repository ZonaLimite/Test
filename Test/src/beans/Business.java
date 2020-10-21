package beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Business
 */
@Stateless
@LocalBean
public class Business {

    /**
     * Default constructor. 
     */
    public Business() {
        // TODO Auto-generated constructor stub
    }
    public String  dameConstante() {
    	return "Probando en MiS18";
    }
    

}
