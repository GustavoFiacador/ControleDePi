<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title>Login</title>
		<!-- Bootstrap core CSS -->
		 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<!-- Custom styles for this template -->
		<link href="css/style.css" rel="stylesheet">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	</head>
	<body>
		<% String login = null;
		if (request.getParameter("login") != null)
			{
				login = request.getParameter("login");
			}
		String erro = null;
	
		%>
		<form class="form-signin" method="POST" action="UsuarioLogin.do?login=<%=login%>">
		<%
			if (request.getParameter("erro") != null)
				{
			erro = request.getParameter("erro");
			if (erro.equals("profnull"))
			{
		%>
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			 <strong>O usuário não é um professor.</strong> Verifique o tipo de login.
		
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%
			}
			else if (erro.equals("admnull"))
			{
				%>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					 <strong>O usuário não é um administrador.</strong> Verifique o tipo de login.
				
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<%
			}
			else if(erro.equals("alunonull"))
			{
				%>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					 <strong>O usuário não é um aluno.</strong> Verifique o tipo de login.
				
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<%
			}
			else if(erro.equals("loginfail"))
			{
				%>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					 <strong>A senha não combina com o email.</strong> Tente de novo.
				
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<%
			}
			else if (erro.equals("emailnull"))
			{
				%>
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					 <strong>O email não existe.</strong> Verifique o email.
				
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<%
			}
			}
		%>

		<div class="text-center mb-4">
				<img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
				<h1 class="h3 mb-3 font-weight-normal">Faça seu login</h1>
				
			</div>
			<div class="form-label-group">
				<input type="email" id="inputEmail" name="txtEmail" class="form-control" placeholder="Email address" required="">
				<label for="inputEmail">Email</label>
			</div>
			<div class="form-label-group">
				<input type="password" id="inputPassword" name="txtSenha" class="form-control" placeholder="Password" required="">
				<label for="inputPassword">Senha</label>
			</div>
			<button id="alert" class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
			<p class="mt-5 text-center"><a href="#"> Esqueci minha senha </a> </p>
			<p class="mt-2 text-center"><a href="pagprincipal.html"> Voltar </a> </p>
		</form>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>