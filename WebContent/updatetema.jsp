<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import="br.usjt.web.projetopi.services.TemaService" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <title>Cadastro</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta charset="UTF-8">
        <!-- Link Bootstrap -->
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Custom Style -->
        <link rel="stylesheet" href="css/estilocadastro.css">
    </head>
    <body>
    <%
if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}
%>
	<%

		

		if (request.getParameter("id") != null)
		{
			String id = request.getParameter("id");
			TemaService tS = new TemaService();
			Tema t = tS.consultar(Integer.parseInt(id));
			
		
	%>
	<jsp:include page="Navbar.jsp" />
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="AtualizaTema.do?id=<%=id %>&active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Atualizar um tema</h1>
                </div>
                <div class="form-group">
                    <label for="txtData" >Data Cadastro</label>
                    <input type="text" class="form-control" disabled value="<%=t.getDtCadastro() %>" id="txtData" name="txtData" aria-describedby="nomeHelp" placeholder="Digite seu nome" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtTitulo">Titulo</label>
                    <input type="text" class="form-control" value="<%=t.getTitulo() %>" id="txtTitulo" name="txtTitulo" placeholder="Titulo" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtRequisitos">Requisitos</label> 
				<textarea class="form-control" name="txtRequisitos"  rows="5" id="txtRequisitos" required="" autofocus=""><%=t.getRequisitos()%></textarea>

			</div>

			<div class="form-group">
                    <label for="txtIntroducao">Introdução</label>
                    <textarea class="form-control" name="txtIntroducao"  rows="5" id="txtIntroducao" required="" autofocus=""><%=t.getIntroducao()%></textarea>

                </div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="tema.jsp?login=<%=request.getParameter("login")%>&active=<%=request.getParameter("active")%>"> Voltar</a> </p>
            </form>
        </div>
	<%
		}
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Progma", "no-cache");
	response.setDateHeader("Expires", 0);

	%>
</body>
</html>