<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import ="br.usjt.web.projetopi.services.TurmaService" %> 
 <%@page import ="java.util.ArrayList" %>


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
Usuario user = null;
session = request.getSession();

if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}

%>
	<jsp:include page="Navbar.jsp" />
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="CadastroAluno.do?active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Cadastrar um aluno</h1>
                </div>
                <div class="form-group">
                    <label for="txtNome" >Nome</label>
                    <input type="text" class="form-control" id="txtNome" name="txtNome" aria-describedby="nomeHelp" placeholder="Digite seu nome" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtEmail">E-mail</label>
                    <input type="email" class="form-control" id="txtEmail" name="txtEmail" placeholder="Email" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtRa">RA</label> <input type="number"
					class="form-control" id="txtRa" name="txtRa"
					placeholder="RA" required="" autofocus="">

			</div>

			<div class="form-group">
				<label for="txtSenha">Senha</label> <input type="password"
					class="form-control" id="txtSenha" name="txtSenha"
					placeholder="Senha" required="" autofocus="">
			</div>
				<div class="form-group">
                    <label for="selectTurma">Selecione a turma:</label> 
                    <select class="form-control" name="selectTurma" id="selectTurma" required="">
					<%
						Turma t = new Turma();
						TurmaService tService = new TurmaService();
			
					ArrayList<Turma> listaTurma = tService.listarTurmas();
				%>
					<%for(Turma turma : listaTurma){
					%>
						<option value="<%= turma.getId() %>"><%=turma.getSigla() %></option>
					<%	
					}
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					response.setHeader("Progma", "no-cache");
					response.setDateHeader("Expires", 0);
				%>

				</select>

			</div>
			
			<button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="listarusuarios.jsp?active=<%=request.getParameter("active")%>">Voltar</a> </p>
            </form>
        </div>

</body>
</html>