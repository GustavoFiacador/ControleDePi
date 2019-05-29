function link(user)
{
	if (user == 'cadastrar')
		{
			window.location.href="cadastro.jsp";
		}
	else
		{
	window.location.href = "login.jsp?login=" + user;
		}
	
}