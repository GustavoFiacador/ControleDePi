<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
  <%@page import="br.usjt.web.projetopi.services.AlunoService" %>

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
			String id = (String) request.getParameter("id");
			
			AlunoService as = new AlunoService();
			Aluno a = as.consultar(Integer.parseInt(id));
			
	%>
	<jsp:include page="Navbar.jsp" />
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="AtualizaAluno.do?id=<%=id %>&active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Atualize um aluno</h1>
                </div>
                <div class="form-group">
                    <label for="txtNome" >Nome</label>
                    <input type="text" value="<%=a.getNome() %>" class="form-control" id="txtNome" name="txtNome" aria-describedby="nomeHelp" placeholder="Digite seu nome" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtEmail">E-mail</label>
                    <input type="email" value="<%=a.getEmail() %>" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtRa">RA</label> <input type="number"
					class="form-control" id="txtRa" name="txtRa"
					placeholder="RA" value="<%=a.getRa() %>" disabled required="" autofocus="">

			</div>

			<div class="form-group">
                    <label for="txtSenha">Senha</label>
                    <input type="password" class="form-control" id="txtSenha" name="txtSenha" placeholder="Senha" required="" autofocus="">

                </div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Atualizar</button>
                <p class="mt-5 text-center"><a href="listarusuarios.jsp?login=<%=request.getParameter("login")%>&active=<%=request.getParameter("active")%>">Voltar</a> </p>
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