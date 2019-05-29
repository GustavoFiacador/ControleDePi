package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Atividade;
import br.usjt.web.projetopi.services.AtividadeService;

/**
 * Servlet implementation class DeletaAtividade
 */
@WebServlet("/DeletaAtividade.do")
public class DeletaAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletaAtividade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		AtividadeService aService = new AtividadeService();
		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		try
		{
			aService.excluir(id);
			
			Atividade b = aService.consultar(id);
			
			if (b == null)
			{
				response.sendRedirect("atividade.jsp?delete=true&login="+login+"&active="+active);
			}
			else
			{
				response.sendRedirect("atividade.jsp?delete=false&login="+login+"&active="+active);
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
		doGet(request, response);
	}

}
