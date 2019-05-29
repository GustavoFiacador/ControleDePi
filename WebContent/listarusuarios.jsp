<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import="br.usjt.web.projetopi.services.AlunoService" %>
 <%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
   
 </head>
  <body class="bg-light">
<%
Usuario user = null;
session = request.getSession();

if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}

%>
  <jsp:include page="Navbar.jsp" />
  <div class="container text-center">
		<h1 class=" display-4 mt-4">Alunos</h1>
		<form>
			<div class="form-row justify-content-md-center mt-4">
				<div class="col-md-auto col-2">
					<strong class="d-block text-gray-dark">Nome:</strong>
				</div>
				<div class="col-md-8 col-xl-9 col-6">
					<input type="text" class="form-control subclass"
						placeholder="Nome" name="txtNome">
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
						<th scope="col">Nome</th>
						<th scope="col">RA</th>
						<th scope="col">Email</th>	
						<th scope="col">Turma</th>
						<th scope="col">Funções</th>
					</tr>
				</thead>
				<tbody>
					<%
						Aluno a = new Aluno();
						AlunoService aService = new AlunoService();
			
					ArrayList<Aluno> listaAluno = aService.listarAlunos();
				%>
				<%for(Aluno aluno : listaAluno){
			
				%>
				
					<tr>
						<th scope="row"><%=aluno.getId()%></th>
						<td><%=aluno.getNome()%></td>
						<td><%=aluno.getRa() %></td>
						<td><%=aluno.getEmail()%></td>
						<td><%=aluno.getTurma().getSigla()%></td>
						<td><a href="updatealuno.jsp?id=<%=aluno.getId()%>&active=<%=request.getParameter("active")%>"><i class="fas fa-user-lock"></i></a>
						<a href="DeletaAluno.do?id=<%=aluno.getId()%>&active=<%=request.getParameter("active")%>" style="margin-left: 10px;"><i class="fas fa-trash-alt"></i></a></td>
					</tr>
				<%
				}
			
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Progma", "no-cache");
				response.setDateHeader("Expires", 0);

				%>
				</tbody>
			</table>
			<div class="float-right">
			<a link href="cadastro.jsp?active=<%=request.getParameter("active")%>">Criar novo</a>
			</div>
			
		</div>
		<%if (request.getParameter("cadastro") != null){
			String cadastro = request.getParameter("cadastro");
			if (cadastro.equals("true"))
			{
				%>
		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Cadastro bem sucedido!</strong> Confira na tabela a cima o novo aluno!
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