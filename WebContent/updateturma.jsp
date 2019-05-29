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
if (session.getAttribute("user") == null) {

	response.sendRedirect("pagprincipal.html");
}
%>
	<%
	
		
		if (request.getParameter("id") != null)
		{
			String id = request.getParameter("id");
			TurmaService tS = new TurmaService();
			Turma tur = tS.consultar(Integer.parseInt(id));
			int idTemaSele = tur.getTema().getId();
			
			
			
	%>
	<jsp:include page="Navbar.jsp" />
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="AtualizaTurma.do?id=<%=id %>&active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Atualizar uma turma</h1>
                </div>
                <div class="form-group">
                    <label for="txtSemestre" >Semestre letivo</label>
                    <input type="number" class="form-control" value="<%=tur.getSemestreLetivo()%>" id="txtSemestre" name="txtSemestre" aria-describedby="nomeHelp" placeholder="Semestre letivo" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtAno">Ano letivo</label>
                    <input type="number" class="form-control" value="<%=tur.getAnoLetivo()%>" id="txtAno" name="txtAno" placeholder="Ano letivo" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtSigla">Sigla</label> <input type="text"
					class="form-control" id="txtSigla" name="txtSigla"
					placeholder="Requisitos" required="" value="<%=tur.getSigla()%>" autofocus="">

			</div>

			<div class="form-group">
                    <label for="selectTema">Selecine o tema:</label> 
                    <select class="form-control" name="selectTema" id="selectTema" required="">
					<%
						Tema t = new Tema();
						TemaService tService = new TemaService();
			
					ArrayList<Tema> listaTema = tService.listarTemas();
					String aux = null;
				%>
					<%for(Tema tema : listaTema){
					%>
						
						<%if(idTemaSele == tema.getId())
						{
							
							aux = "selected";
						}
						else {
							aux = "";
						}
						%>
						<option  <%=aux %> value="<%= tema.getId() %>"><%=tema.getTitulo()%></option>
					<%	
					}
				%>

				</select>

			</div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="turma.jsp?login=<%=request.getParameter("login")%>&active=<%=request.getParameter("active")%>"> Voltar</a> </p>
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