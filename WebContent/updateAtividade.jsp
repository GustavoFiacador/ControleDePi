<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.*" %>
 <%@page import="br.usjt.web.projetopi.services.AtividadeService" %>

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
			AtividadeService aS = new AtividadeService();
			Atividade a = aS.consultar(Integer.parseInt(id));
			
		
	%>
	<jsp:include page="Navbar.jsp" />
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="AtualizaTema.do?id=<%=id %>&active=<%=request.getParameter("active")%>">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Atualizar uma atividade</h1>
                </div>
                <div class="form-group">
                    <label for="txtId" >Id</label>
                    <input type="text" 
                    class="form-control" 
                    disabled 
                    value="<%=id %>" 
                    id="txtId" 
                    name="txtId" 
                    aria-describedby="nomeHelp" 
                    required="" autofocus="">
                </div>
                 <div class="form-group">
                    <label for="txtNumero">Numero</label>
                    <input type="text" class="form-control" value="<%=a.getNumero() %>" id="txtNumero" name="txtNumero" required="" autofocus="">
                </div>
                <div class="form-group">
                    <label for="txtDescricao">Descrição</label>
                    <input type="textarea" class="form-control" value="<%=a.getDescricao() %>" id="txtDescricao" name="txtDescricao" required="" autofocus="">
                </div>
				<div class="form-group">
					<label for="txtFormatoEntrega">Formato de Entrega</label> <input type="text"
						class="form-control" id="txtFormatoEntrega" name="txtFormatoEntrega"
						placeholder="Formato de Entrega" required="" value="<%=a.getFormatoEntrega() %>" autofocus="">
				</div>

				<div class="form-group">
                    <label for="txtDtInicio">Data Início</label>
                    <input type="text" class="form-control" value="<%=a.getDtInicio() %>" id="txtDtInicio" name="txtDtInicio" placeholder="Data Inicio" required="" autofocus="">

            	</div>
            	
            	<div class="form-group">
                    <label for="txtDtFim">Data Final</label>
                    <input type="text" class="form-control" value="<%=a.getDtFim() %>" id="txtDtFim" name="txtDtFim" placeholder="Data Final" required="" autofocus="">
            	</div>
            	
            	<div class="form-group">
                    <label for="txtTemaId">Id do Tema</label>
                    <input type="text" class="form-control" value="<%=a.getTemaId() %>" id="txtTemaId" name="txtTemaId" placeholder="Tema Id" required="" autofocus="">
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