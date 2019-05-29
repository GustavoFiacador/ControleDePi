<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="br.usjt.web.projetopi.model.Usuario"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.5">
<title>Freemon</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.3/examples/offcanvas/">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">


<!-- Custom styles for this template -->
<link href="css/estilonavbar.css" rel="stylesheet">
</head>
<%

Usuario user = null;
session = request.getSession();

if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}
else
{

	user = (Usuario) session.getAttribute("user"); 
	String nome = user.getNome();

%>
			
		

		
		

<body class="bg-light">
	<nav class="navbar navbar-expand-lg fixed-top navbar-dark bgnav-new">
		<a class="navbar-brand mr-auto mr-lg-0" href="#">Freemon</a>
		<button class="navbar-toggler p-0 border-0" type="button"
			data-toggle="offcanvas">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="navbar-collapse offcanvas-collapse "
			id="navbarsExampleDefault">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Principal
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Perfil</a></li>
				<li class="nav-item"><a class="nav-link" href="Logoff.do">Sair da
						conta</a></li>

			</ul>

		</div>
	</nav>
	<%
	String teste = null;
	if (session.getAttribute("testeAux") != null) {
		teste = (String) session.getAttribute("testeAux");
	%>
	<div class="nav-scroller bg-white shadow-sm">
		<nav class="nav nav-underline">
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("home")) out.println("active"); }%>" href="index.jsp?active=home">Olá, <%= nome  %></a>
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("atividade")) out.println("active"); }%>" href="atividade.jsp?active=atividade"> Atividades</a>
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("grupo")) out.println("active"); }%>" href="grupos.jsp?active=grupo"> Grupo</a>
			<%
		
				

					if (teste.equals("adm") || teste.equals("prof")) {
			%>
			
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("listaruser")) out.println("active"); }%>" href="listarusuarios.jsp?active=listaruser">Alunos</a>
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("turma")) out.println("active"); }%>" href="turma.jsp?active=turma">Turmas</a>
			<a class="nav-link <%if(request.getParameter("active") != null){ if(request.getParameter("active").equals("tema")) out.println("active"); }%>" href="tema.jsp?active=tema">Tema</a>
			
			<%
				}
	}
			
				}
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Progma", "no-cache");
	response.setDateHeader("Expires", 0);

			%>
		</nav>
	</div>
			
<script type="text/javascript"  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/scriptcanvas.js"></script>
</body>
</html>