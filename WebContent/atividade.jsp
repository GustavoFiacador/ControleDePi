<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.usjt.web.projetopi.model.*"%>
<%@page import="br.usjt.web.projetopi.services.AtividadeService"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function buscarAtividade(){
		$.getJSON({
			url: 'buscarAtividades.java',
			success: function (dados){
				for(i=0; i<dados.length; i++){
					$('#resultado').append();
				}
			}
		})
	}
<%
Usuario user = null;
session = request.getSession();

if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}

%>
</script>
</head>
<body class="bg-light">

	<jsp:include page="Navbar.jsp" />
	<div class="container text-center">
		<h1 class=" display-4 mt-4">Atividades</h1>
		<form>
			<div class="form-row justify-content-md-center mt-4">
				<div class="col-md-auto col-2">
					<strong class="d-block text-gray-dark">Numero:</strong>
				</div>
				<div class="col-md-8 col-xl-9 col-6">
					<input type="text" class="form-control subclass"
						placeholder="Numero" name="txtNumero">
				</div>
				<div class="col-md-2  col-4">
					<input type="submit" value="Buscar" onclick="buscarAtividade()" class="form-control subclass btn btn-primary">
				</div>
				<div id="resultado"></div>
			</div>
		</form>
	</div>
	<div class="container mt-2">
		<div class="table-responsive-sm mt-5">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Numero</th>
						<th scope="col">Descrição</th>
						<th scope="col">Formato Entrega</th>
						<th scope="col">Data Inicio</th>
						<th scope="col">Data Fim</th>
						<th scope="col"># do Tema</th>

					</tr>
				</thead>
				<tbody>
					<%
						Atividade a = new Atividade();
						AtividadeService aService = new AtividadeService();

						ArrayList<Atividade> listaAtividade = aService.listarAtividades();
	
						for (Atividade atividade : listaAtividade) {
					%>

					<tr>
						<th scope="row"><%=atividade.getId()%></th>
						<td><%=atividade.getNumero()%></td>
						<td><%=atividade.getDescricao()%></td>
						<td><%=atividade.getFormatoEntrega()%></td>
						<td><%=atividade.getDtInicio()%></td>
						<td><%=atividade.getDtFim()%></td>
						<td><%=atividade.getTemaId().getId()%></td>

					
						<td><a
							href="updateAtividade.jsp?id=<%=atividade.getId()%>&active=<%=request.getParameter("active")%>"><i
								class="fas fa-user-lock"></i></a> 
							<a
							href="DeletaAtividade.do?id=<%=atividade.getId()%>&active=<%=request.getParameter("active")%>"
							style="margin-left: 10px;"><i class="fas fa-trash-alt"></i></a></td>
					
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			
			<div class="float-right">
				<a link
					href="cadastroatividade.jsp?active=<%=request.getParameter("active")%>">Criar
					novo</a>
			</div>
	
		</div>
		<%
			if (request.getParameter("cadastro") != null) {
				String cadastro = request.getParameter("cadastro");
				if (cadastro.equals("true")) {
		%>

		<div class="alert alert-success alert-dismissible fade show"
			role="alert">
			<strong>Cadastro bem sucedido!</strong> Confira na tabela a cima o
			novo tema!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%		} else {%>
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
</body>
</html>