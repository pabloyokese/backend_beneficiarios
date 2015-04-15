<%@page import="bo.sigep.curso.vo.BeneficiariosVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Buscar de beneficiarios</h1>
	
	<form id="buscarBeneficiario"
		action="<%=request.getContextPath()%>/Controlador">
		<input type="hidden" name="accion" value="buscarBeneficiario" />
		Introduzca apellido paterno: <input type="text" name="apellido" value="<%=request.getAttribute("apellido") != null ?request.getAttribute("apellido"):"" %>" /> 
		<input type="submit" name="enviar" value="Buscar" />
	
	<%
		List<BeneficiariosVo> beneficiarios = (List<BeneficiariosVo>) request
				.getAttribute("listadoBeneficiarios");
		if (beneficiarios != null && beneficiarios.size() > 0) {
	%>
	<hr/>
	El total de beneficiarios encontrados es <%= request.getAttribute("total")  %>
	<table>
		<thead>
			<tr>
				<th>ID BENEFICIARIO</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
			</tr>
		</thead>
		<tbody>

			<%
				for (BeneficiariosVo ben : beneficiarios) {
			%>
			<tr>
				<td><%=ben.getIdBeneficiario()%></td>
				<td><%=ben.getNombre()%></td>
				<td>
			<%=ben.getApellido()%></td>
				</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<%
		}
	%>
	<% Integer total = (Integer)request.getAttribute("total"); 
		if(total!=null){
			for(int i=1;i<total/20;i++){
				out.println("<input type='submit' name='pagina' value='"+i+"' />");
			} 
		}
	%>
	</form>
</body>
</html>