package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Tema;
import br.usjt.web.projetopi.services.TemaService;

/**
 * Servlet implementation class DeletaTema
 */
@WebServlet({ "/DeletaTema", "/DeletaTema.do" })
public class DeletaTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletaTema() {
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

		TemaService tService = new TemaService();
		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		try
		{
			tService.excluir(id);
			
			Tema b = tService.consultar(id);
			
			if (b == null)
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
