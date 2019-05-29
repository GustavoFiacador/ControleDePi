<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import="java.util.ArrayList" %>
 <%@page import="br.usjt.web.projetopi.services.*" %>

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
            
            <form class="form-signin" method="POST" action="CadastroAtividade.do?active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Cadastrar uma atividade</h1>
                </div>
                <div class="form-group">
                    <label for="txtNumero" >Numero</label>
                    <input type="text" class="form-control" id="txtNumero" name="txtNumero" aria-describedby="nomeHelp" placeholder="Digite numero" required="" autofocus="">
                </div>
                
                <div class="form-group">
                    <label for="txtDescricao">Descricao</label>
                    <input type="text" class="form-control" id="txtDescricao" name="txtDescricao" placeholder="Descricao" required="" autofocus="">
  
                </div>
			<div class="form-group">
				<label for="txtFormatoEntrega">Formato de Entrega</label> <input type="text"
					class="form-control" id="txtFormatoEntrega" name="txtFormatoEntrega"
					placeholder="Formato de Entrega" required="" autofocus="">

			</div>

			<div class="form-group">
                    <label for="txtDtInicio">Data Inicio</label>
                    <input type="text" class="form-control" id="txtDtInicio" name="txtDtInicio" placeholder="Data Inicio" required="" autofocus="">

                </div>
                
                <div class="form-group">
                    <label for="txtDtFim">Data Fim</label>
                    <input type="text" class="form-control" id="txtDtFim" name="txtDtFim" placeholder="Data Fim" required="" autofocus="">

                </div>
                
                <div class="form-group">
                    <label for="selectTema">Selecione o tema</label> <select
					class="form-control" name="selectTema" id="selectTema" required="">
					<%
						Tema t = new Tema();
						TemaService tService = new TemaService();

						ArrayList<Tema> listaTema = tService.listarTemas();
					%>
					<%
						for (Tema tema : listaTema) {
					%>
					<option value="<%=tema.getId()%>"><%=tema.getTitulo()%></option>
					<%
						}
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					response.setHeader("Progma", "no-cache");
					response.setDateHeader("Expires", 0);
					%>

				</select>

			</div>
                
                
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="atividade.jsp?login=<%=request.getParameter("login")%>&active=<%=request.getParameter("active")%>"> Voltar </a> </p>
            </form>
        </div>

</body>
</html>