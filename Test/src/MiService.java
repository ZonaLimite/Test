import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Business;

import util.ReportMaker;


@Stateless
@Path("/services")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MiService {
	@EJB
	Business business;

	
	@GET
    @Path("/status")
	@Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok().entity("{Service online:okeyTest}").build();
    }
	
	@GET
	@Path("/Report-GET")
	@Produces(MediaType.TEXT_PLAIN)
	public Response SolicitarInforme(@QueryParam("Informe") String maskReport ,@QueryParam("EnviarSIE") String okSIE,@QueryParam("TipoAutomatizacion") String tipoAut) {

		ReportMaker cr = new ReportMaker();
		String result = cr.CreaReporteMask(maskReport,okSIE,tipoAut);
		return Response.ok().entity(" Solicitado Informe :"+result).build();
	}
	
	@GET
	@Path("/TestBean")
	@Produces(MediaType.TEXT_PLAIN)
	public Response methodTestBean() {

		return Response.ok().entity(business.dameConstante()+" ha dicho el bicho").build();
	}

}
