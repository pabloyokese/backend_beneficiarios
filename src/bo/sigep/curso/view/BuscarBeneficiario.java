package bo.sigep.curso.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.sigep.curso.bl.BeneficiarioBl;
import bo.sigep.curso.vo.BeneficiariosVo;

/**
 * Servlet implementation class BuscarBeneficiario
 */
@WebServlet("/BuscarBeneficiario")
public class BuscarBeneficiario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BeneficiarioBl beneficiarioBl;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuscarBeneficiario() {
		super();
		beneficiarioBl = new BeneficiarioBl();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String apellido = (String)request.getParameter("apellido");
		int nroPagina = request.getParameter("pagina")!=null? Integer.parseInt(request.getParameter("pagina")):1;
		int nroRegistros = 20;
		
		System.out.println("BuscarBeneficiario -> :" + apellido);
		int total = beneficiarioBl.getCantidadBeneficiarioPorApellidoPaterno(apellido);
		List<BeneficiariosVo> lista = beneficiarioBl.getBeneficiarioPorApellidoPaterno(apellido,nroPagina,nroRegistros);
		request.setAttribute("total", total);
		request.setAttribute("apellido", apellido);
		request.setAttribute("listadoBeneficiarios", lista);
		System.out.println("BuscarBeneficiario -> lista :" + lista);
		if(lista!=null){
			System.out.println("BuscarBeneficiario -> lista.size :" + lista.size());
		}
		request.setAttribute("accion", "mostrarBeneficiarios");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Controlador");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
