package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Tema;
import br.usjt.web.projetopi.model.Turma;
import br.usjt.web.projetopi.services.TemaService;
import br.usjt.web.projetopi.services.TurmaService;

/**
 * Servlet implementation class AtualizaTurma
 */
@WebServlet({ "/AtualizaTurma", "/AtualizaTurma.do" })
public class AtualizaTurma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizaTurma() {
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
		String login = request.getParameter("login");
		String active = request.getParameter("active");
		
		String semestre = request.getParameter("txtSemestre");
		String ano = request.getParameter("txtAno");
		String sigla = request.getParameter("txtSigla");
		String idTema = request.getParameter("selectTema");
		String idAuto = request.getParameter("id");
		
		Turma tur = new Turma();
		TurmaService turS = new TurmaService();
		
		try
		{
			Tema t = new Tema();
			t.setId(Integer.parseInt(idTema));
			tur.setAnoLetivo(Integer.parseInt(ano));
			tur.setSemestreLetivo(Integer.parseInt(semestre));
			tur.setSigla(sigla);
			tur.setId(Integer.parseInt(idAuto));
			tur.setTema(t);
			
			turS.alterar(tur);

			
		
			response.sendRedirect("turma.jsp?cadastro=true&login="+login+"&active="+active);
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}

}
