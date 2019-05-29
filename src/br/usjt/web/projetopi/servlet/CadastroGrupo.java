package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Grupo;
import br.usjt.web.projetopi.model.Professor;
import br.usjt.web.projetopi.model.Tema;
import br.usjt.web.projetopi.model.Turma;
import br.usjt.web.projetopi.services.GrupoService;
import br.usjt.web.projetopi.services.TurmaService;

/**
 * Servlet implementation class CadastroGrupo
 */
@WebServlet({ "/CadastroGrupo", "/CadastroGrupo.do" })
public class CadastroGrupo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroGrupo() {
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
		
		String numero = request.getParameter("txtNumero");
		String nome = request.getParameter("txtNome");
		String idProfessor = request.getParameter("selectProfessor");
		String idTurma = request.getParameter("selectTurma");
		String idAlunos[] = request.getParameterValues("selectAlunos");
	
		
		Grupo g = new Grupo();
		GrupoService gService = new GrupoService();
		try
		{
		
			Professor p = new Professor();
			p.setId(Integer.parseInt(idProfessor));
			
			Turma t = new Turma();
			t.setId(Integer.parseInt(idTurma));
			
			g.setNome(nome);
			g.setNumero(Integer.parseInt(numero));
			g.setOrientador(p);
			g.setTurma(t);
			int idAuto = gService.cadastrar(g);
			g.setId(idAuto);
			
			
			ArrayList<Aluno> alunos = new ArrayList<>();
			
			for (int i = 0; i <= idAlunos.length - 1; i++ )
			{
				Aluno a = new Aluno();
				a.setId(Integer.parseInt(idAlunos[i]));
				alunos.add(a);
			}
			g.setAlunos(alunos);

			
			gService.alocarAluno(g);
			
			Grupo gTeste = gService.consultar(idAuto);
			
			if (idAuto == gTeste.getId())
			{
				response.sendRedirect("grupos.jsp?cadastro=true&active="+active);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
