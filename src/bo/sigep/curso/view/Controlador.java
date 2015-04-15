package bo.sigep.curso.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.sigep.curso.vo.BeneficiariosVo;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getAttribute("accion") != null ? (String) request
				.getAttribute("accion") : request.getParameter("accion");
				System.out.println("Controllador->accion " + accion);
				String destino = null;
				if("buscarBeneficiario".equals(accion)){
					destino = "/BuscarBeneficiario" ;
				}else if("mostrarBeneficiarios".equals("accion")){
					destino = "/index.jsp";
				}else{
					destino = "/index.jsp";
				}
				System.out.println("Controllador->destino " + destino);
				RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
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
