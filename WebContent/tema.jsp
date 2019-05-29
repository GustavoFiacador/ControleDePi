<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import="br.usjt.web.projetopi.services.TemaService" %>
 <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
   
 </head>
  <body class="bg-light">
<%
if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}
%>
  <jsp:include page="Navbar.jsp" />
  <div class="container text-center">
		<h1 class=" display-4 mt-4">Temas</h1>
		<form>
			<div class="form-row justify-content-md-center mt-4">
				<div class="col-md-auto col-2">
					<strong class="d-block text-gray-dark">Nome:</strong>
				</div>
				<div class="col-md-8 col-xl-9 col-6">
					<input type="text" class="form-control subclass"
						placeholder="Tema" name="txtNome">
				</div>
				<div class="col-md-2  col-4">
					<input type="submit" class="form-control subclass btn btn-primary">
				</div>
			</div>
		</form>
	</div>
<div class="container mt-2">
		<div class="table-responsive-sm mt-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Titulo</th>
						<th scope="col">Data Cadastro</th>
						<th scope="col">Introdução</th>	
						<th scope="col">Funções</th>
			
					</tr>
				</thead>
				<tbody>
					<%
						Tema t = new Tema();
						TemaService tService = new TemaService();
			
					ArrayList<Tema> listaTema = tService.listarTemas();
				%>
				<%for(Tema tema : listaTema){
			
				%>
				
					<tr>
						<th scope="row"><%=tema.getId()%></th>
						<td><%=tema.getTitulo()%></td>
						<td><%=tema.getDtCadastro() %></td>
						<td><%=tema.getIntroducao()%></td>
						<td><a href="updatetema.jsp?id=<%=tema.getId()%>&active=<%=request.getParameter("active")%>"><i class="fas fa-user-lock"></i></a>
						<a href="DeletaTema.do?id=<%=tema.getId()%>&active=<%=request.getParameter("active")%>" style="margin-left: 10px;"><i class="fas fa-trash-alt"></i></a></td>
					</tr>
				<%
				}
			
			

				%>
				</tbody>
			</table>
			<div class="float-right">
			<a link href="cadastrotema.jsp?active=<%=request.getParameter("active")%>">Criar novo</a>
			</div>
			
		</div>
		<%if (request.getParameter("cadastro") != null){
			String cadastro = request.getParameter("cadastro");
			if (cadastro.equals("true"))
			{
				%>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Cadastro bem sucedido!</strong> Confira na tabela a cima o novo tema!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			}
			else
			{
				%>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<strong>Cadastro falhou!</strong>Tente novamente!
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<%
			}
			
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Progma", "no-cache");
		response.setDateHeader("Expires", 0);

		
		%>
	</div>




</body></html>