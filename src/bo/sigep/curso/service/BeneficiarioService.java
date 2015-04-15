package bo.sigep.curso.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import bo.sigep.curso.bl.BeneficiarioBl;
import bo.sigep.curso.vo.BeneficiariosVo;
import com.owlike.genson.Genson;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("beneficiarios")
public class BeneficiarioService {

    /**
     * Default constructor.
     */
    public BeneficiarioService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of BeneficiarioService
     *
     * @return an instance of String
     */
    BeneficiarioBl beneficiarioBl = new BeneficiarioBl();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBeneficiarios() {
        Genson genson = new Genson();
        //return beneficiarioBl.getBeneficiarios();
        return Response.ok().entity(genson.serialize(beneficiarioBl.getBeneficiarios()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Content-Type, X-XSRF-TOKEN")
                .allow("OPTIONS").build();
    }
    
    @GET
    @Path("count/{apellido}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCantidadBeneficiarioPorApellidoPaterno(@PathParam("apellido") String apellido) {
        Genson genson = new Genson();
        //return beneficiarioBl.getBeneficiarios();
        return Response.ok().entity(genson.serialize(beneficiarioBl.getCantidadBeneficiarioPorApellidoPaterno(apellido)))
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("{apellido}/{nroPagina}/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBeneficiarioPorApellidoPaterno(@PathParam("apellido") String apellido,@PathParam("nroPagina") int nroPagina) {
        int nroRegistros = 20;
        Genson genson = new Genson();
        //return beneficiarioBl.getBeneficiarios();
        return Response.ok().entity(genson.serialize(beneficiarioBl.getBeneficiarioPorApellidoPaterno(apellido, nroPagina, nroRegistros)))
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
}
