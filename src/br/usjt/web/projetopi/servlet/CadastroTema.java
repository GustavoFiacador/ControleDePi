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
 * Servlet implementation class CadastroTema
 */
@WebServlet({ "/CadastroTema", "/CadastroTema.do" })
public class CadastroTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroTema() {
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
		String data = request.getParameter("txtData");
		String titulo = request.getParameter("txtTitulo");
		String requisitos = request.getParameter("txtRequisitos");
		String introducao = request.getParameter("txtIntroducao");
		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		
		Tema t = new Tema();
		TemaService tService = new TemaService();
		
		try
		{
			t.setDtCadastro(data);
			t.setTitulo(titulo);
			t.setRequisitos(requisitos);
			t.setIntroducao(introducao);
			
			int idAuto = tService.cadastrar(t);
			System.out.println(idAuto);
			Tema tNovo = tService.consultar(idAuto);
			if (idAuto == tNovo.getId())
			{
				response.sendRedirect("tema.jsp?cadastro=true&login="+login+"&active="+active);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
