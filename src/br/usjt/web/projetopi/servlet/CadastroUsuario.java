package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.services.UsuarioService;
import br.usjt.web.projetopi.util.Criptografia;

/**
 * Servlet implementation class CadastroUsuario
 */
@WebServlet({ "/CadastroUsuario", "/CadastroUsuario.do" })
public class CadastroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("txtNome");
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		
		Usuario user = new Usuario();
		UsuarioService userService = new UsuarioService();
		Criptografia cripto = new Criptografia();
		
		try
		{
			user.setNome(nome);
			user.setEmail(email);
			user.setSenha(cripto.convertToMD5(senha));
			
			int idAuto = userService.cadastrar(user);
			
			Usuario userTeste = userService.consultar(idAuto);
			
			if (idAuto == userTeste.getId())
			{
				response.sendRedirect("cadastro.jsp?cadastro=true");
			}
			else
			{
				response.sendRedirect("cadastro.jsp?cadastro=false");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
