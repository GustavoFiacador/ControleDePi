package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Turma;
import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.services.AlunoService;
import br.usjt.web.projetopi.services.UsuarioService;
import br.usjt.web.projetopi.util.Criptografia;

/**
 * Servlet implementation class CadastroAluno
 */
@WebServlet({ "/CadastroAluno", "/CadastroAluno.do" })
public class CadastroAluno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroAluno() {
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
		String ra = request.getParameter("txtRa");
		String turmaID = request.getParameter("selectTurma");
		
		Usuario user = new Usuario();
		UsuarioService userService = new UsuarioService();
		Criptografia cripto = new Criptografia();

		Aluno a = new Aluno();
		AlunoService aService = new AlunoService();
		

		String active = request.getParameter("active");
		
		
		try
		{
			Turma t = new Turma();
			t.setId(Integer.parseInt(turmaID));
			
			
			user.setNome(nome);
			user.setEmail(email);
			user.setSenha(cripto.convertToMD5(senha));
	
			int idAuto = userService.cadastrar(user);
			
			
			

				a.setId(idAuto);
				a.setRa(Integer.parseInt(ra));
				a.setTurma(t);
				aService.cadastrar(a);
				
				Aluno aTeste = aService.consultar(idAuto);
				
			if (idAuto == aTeste.getId())
			{
				aService.alocarAlunoaTurma(a);
				response.sendRedirect("listarusuarios.jsp?cadastro=true&active="+active);
			}
			else
			{
				response.sendRedirect("listarusuarios.jsp?cadastro=false&active="+active);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	}


