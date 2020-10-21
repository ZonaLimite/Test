package util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;

import beans.Business;

@WebServlet("/SourcerArraysMis")
public class SourcerArraysServlet extends HttpServlet{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Inject
private Business business;

HttpServletResponse response;
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.response=res;
	
		HttpSession htps=req.getSession();
		String idHttpSession = htps.getId();
		String usuario = req.getParameter("usuario");
		String perfil = req.getParameter("perfil");
		String ordCarton = req.getParameter("nCarton");
		String comando = req.getParameter("comando");

			
		Object datosLista [] = new String[10];
		Object arrayDatosLabel[]=null;

		arrayDatosLabel[0]= datosLista;
		
		//Ahora a enviarlo como JSON
		 String json = new Gson().toJson(arrayDatosLabel);
	   
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write(json);
		} catch (IOException e) {

			e.printStackTrace();
		}

}



}
