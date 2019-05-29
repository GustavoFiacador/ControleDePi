<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="br.usjt.web.projetopi.model.Usuario" %>

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
	
        <div class="container mt-5">
            
            <form class="form-signin" method="POST" action="CadastroUsuario.do">
                <div class="text-center mb-4">
                   <h1 class=" display-4 mt-4">Cadastro</h1>
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
                    <label for="txtSenha">Senha</label>
                    <input type="password" class="form-control" id="txtSenha" name="txtSenha" placeholder="Senha" required="" autofocus="">

                </div>
                <button type="submit" class="btn btn-lg mt-2 btn-primary btnCadastrar">Cadastrar</button>
                <p class="mt-5 text-center"><a href="pagprincipal.html"> Voltar para o login </a> </p>
            </form>
        </div>

</body>
</html>