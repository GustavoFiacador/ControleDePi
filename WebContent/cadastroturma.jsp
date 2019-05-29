<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
  <%@page import="br.usjt.web.projetopi.services.*" %>
   <%@page import="java.util.ArrayList" %>
 

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
            
            <form class="form-signin" method="POST" action="CadastroTurma.do?active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Cadastrar uma turma</h1>
                </div>
                <div class="form-group">
                    <label for="txtSemestre" >Semestre letivo</label>
                    <input type="number" class="form-control" id="txtSemestre" name="txtSemestre" aria-describedby="nomeHelp" placeholder="Semestre letivo" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtAno">Ano letivo</label>
                    <input type="number" class="form-control" id="txtAno" name="txtAno" placeholder="Ano letivo" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtSigla">Sigla</label> <input type="text"
					class="form-control" id="txtSigla" name="txtSigla"
					placeholder="Sigla" required="" autofocus="">

			</div>

			<div class="form-group">
                    <label for="selectTema">Selecine o tema:</label> 
                    <select class="form-control" name="selectTema" id="selectTema" required="">
					<%
						Tema t = new Tema();
						TemaService tService = new TemaService();
			
					ArrayList<Tema> listaTema = tService.listarTemas();
				%>
					<%for(Tema tema : listaTema){
					%>
						<option value="<%= tema.getId() %>"><%=tema.getTitulo() %></option>
					<%	
					}
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					response.setHeader("Progma", "no-cache");
					response.setDateHeader("Expires", 0);
				%>

				</select>

			</div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="turma.jsp?login=<%=request.getParameter("login")%>&active=<%=request.getParameter("active")%>"> Voltar para o login </a> </p>
            </form>
        </div>

</body>
</html>