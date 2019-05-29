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
        <script type="text/javascript"  src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <!-- Link Bootstrap -->
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Custom Style -->
        <link rel="stylesheet" href="css/estilocadastro.css">

<script>
	$(document).ready(function(){
		$('#selectTurma').on('change', function(){
			var val = $('#selectTurma').val();
			
			$.ajax({
				type: "GET",
				url: "PopularAlunos.do",
				data: {valor : val},
				success: function(data){
					$("#selectAlunos").html(data)
				}
			});
		});
		
	});

</script>
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
            
            <form class="form-signin" method="POST" action="CadastroGrupo.do?active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Cadastrar um grupo</h1>
                </div>
                <div class="form-group">
                    <label for="txtNumero" >Numero</label>
                    <input type="number" class="form-control" id="txtNumero" name="txtNumero" aria-describedby="nomeHelp" placeholder="Numero" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtNome">Nome</label>
                    <input type="text" class="form-control" id="txtNome" name="txtNome" placeholder="Nome" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtProf">Selecione o professor</label>
				<select class="form-control" name="selectProfessor" id="selectProfessor" required>
				<%
					Professor p = new Professor();
					ProfessorService pService = new ProfessorService();
					ArrayList<Professor> listaProf = pService.listarProfessor();
					
					for (Professor professor : listaProf)
					{
						%>
						<option value = "<%= professor.getId() %>"><%= professor.getNome() %></option>
						<% 
					}
				%>
				</select>
			</div>
	
			<div class="form-group">
                    <label for="selectTurma">Selecine a turma:</label> 
                    <select class="form-control" name="selectTurma" id="selectTurma" required="" >
                    	<option selected>SELECIONE A TURMA</option>
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
							
							
			<label for="selectAlunos">Selecione os alunos:</label>
			<div class="form-group">
				<select class="custom-select" id="selectAlunos" name="selectAlunos" multiple>
					
				</select>
			</div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="grupos.jsp?&active=<%=request.getParameter("active")%>"> Voltar</a> </p>
            </form>
        </div>

</body>
</html>