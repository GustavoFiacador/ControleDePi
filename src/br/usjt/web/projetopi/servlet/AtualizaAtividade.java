package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Atividade;
import br.usjt.web.projetopi.model.Tema;
import br.usjt.web.projetopi.services.AtividadeService;
import br.usjt.web.projetopi.services.TemaService;

/**
 * Servlet implementation class AtualizaAtividade
 */
@WebServlet("/AtualizaAtividade.do")
public class AtualizaAtividade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaAtividade() {
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
		int numero = Integer.parseInt(request.getParameter("txtNumero"));
		String descricao = request.getParameter("txtDescricao");
		String formatoEntrega = request.getParameter("txtFormatoEntrega");
		String dtInicio = request.getParameter("txtDtInicio");
		String dtFim = request.getParameter("txtDtFim");
		int temaId = Integer.parseInt(request.getParameter("txtTemaId"));

		String id = request.getParameter("id");
		
		
		Atividade a = new Atividade();
		AtividadeService aService = new AtividadeService();
		TemaService tService = new TemaService();
		
		Tema t = tService.consultar(temaId);

		
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		
		
		try
		{
			a.setNumero(numero);
			a.setDescricao(descricao);
			a.setFormatoEntrega(formatoEntrega);
			a.setDtInicio(dtInicio);
			a.setDtFim(dtFim);
			a.setTemaId(t);
			
			a.setId(Integer.parseInt(id));
			
			aService.alterar(a);
			
			Atividade aNovo = aService.consultar(Integer.parseInt(id));
				
			if (id.equals(aNovo.getId()))
			{
				response.sendRedirect("atividade.jsp?cadastro=true&login="+login+"&active="+active);
			}
			else
			{
				response.sendRedirect("atividade.jsp?cadastro=false&login="+login+"&active="+active);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
