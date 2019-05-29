package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Tema;
import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.services.TemaService;
import br.usjt.web.projetopi.services.UsuarioService;
import br.usjt.web.projetopi.util.Criptografia;

/**
 * Servlet implementation class AtualizaTema
 */
@WebServlet({ "/AtualizaTema", "/AtualizaTema.do" })
public class AtualizaTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaTema() {
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
		String titulo = request.getParameter("txtTitulo");
		String requisitos = request.getParameter("txtRequisitos");
		String introducao = request.getParameter("txtIntroducao");

		String id = request.getParameter("id");
		
		
		Tema t = new Tema();
		TemaService tService = new TemaService();


		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		
		
		try
		{
			t.setTitulo(titulo);
			t.setRequisitos(requisitos);
			t.setIntroducao(introducao);
			
			t.setId(Integer.parseInt(id));
			
			tService.alterar(t);
			
			Tema tNovo = tService.consultar(Integer.parseInt(id));
				
			if (titulo.equals(tNovo.getTitulo()))
			{
				response.sendRedirect("tema.jsp?cadastro=true&login="+login+"&active="+active);
			}
			else
			{
				response.sendRedirect("tema.jsp?cadastro=false&login="+login+"&active="+active);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
