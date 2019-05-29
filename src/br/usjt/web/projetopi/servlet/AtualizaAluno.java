package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.services.AlunoService;
import br.usjt.web.projetopi.services.UsuarioService;
import br.usjt.web.projetopi.util.Criptografia;

/**
 * Servlet implementation class AtualizaAluno
 */
@WebServlet({ "/AtualizaAluno", "/AtualizaAluno.do" })
public class AtualizaAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaAluno() {
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

		String id = request.getParameter("id");
		
		
		Usuario user = new Usuario();
		UsuarioService userService = new UsuarioService();
		Criptografia cripto = new Criptografia();


		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		
		
		try
		{
			user.setNome(nome);
			user.setEmail(email);
			user.setSenha(cripto.convertToMD5(senha));
			
			user.setId(Integer.parseInt(id));
			
			userService.alterar(user);
			
			Usuario userNovo = userService.consultar(Integer.parseInt(id));
				
			if (email.equals(userNovo.getEmail()))
			{
				response.sendRedirect("listarusuarios.jsp?cadastro=true&login="+login+"&active="+active);
			}
			else
			{
				response.sendRedirect("listarusuarios.jsp?cadastro=false&login="+login+"&active="+active);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
