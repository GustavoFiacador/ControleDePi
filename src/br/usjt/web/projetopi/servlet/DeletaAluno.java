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
 * Servlet implementation class DeletaAluno
 */
@WebServlet({ "/DeletaAluno", "/DeletaAluno.do" })
public class DeletaAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletaAluno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		System.out.println(id);

		UsuarioService userService = new UsuarioService();
		AlunoService aService = new AlunoService();
		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		try
		{
			userService.excluir(id);
			aService.excluir(id);
			
			Aluno b = aService.consultar(id);
			Usuario userb = userService.consultar(id);
			
			if (b == null && userb == null)
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
