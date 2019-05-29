package br.usjt.web.projetopi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.usjt.web.projetopi.dao.AlunoDao;
import br.usjt.web.projetopi.model.Aluno;
import br.usjt.web.projetopi.model.Professor;
import br.usjt.web.projetopi.model.Usuario;
import br.usjt.web.projetopi.services.AlunoService;
import br.usjt.web.projetopi.services.ProfessorService;
import br.usjt.web.projetopi.services.UsuarioService;
import br.usjt.web.projetopi.util.Criptografia;

/**
 * Servlet implementation class UsuarioLogin
 */
@WebServlet({ "/UsuarioLogin", "/UsuarioLogin.do" })
public class UsuarioLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		System.out.println(email);
		
		
		Usuario user = new Usuario();
		UsuarioService userService = new UsuarioService();
		Criptografia cripto = new Criptografia();

		user.setEmail(email);
		Usuario userAutentica = userService.consultaLogin(user);

		if (request.getParameter("login") != null) {
			String login = request.getParameter("login");
			String teste = null;
			if (userAutentica != null) {
				boolean senhaCorreta = cripto.matching(userAutentica.getSenha(), senha);
				if (email.equals(userAutentica.getEmail()) && senhaCorreta == true) {
					HttpSession session = request.getSession();
					session.setAttribute("user", userAutentica);
					if (login.equals("administrador") || login.equals("professor")) {
						ProfessorService pADM = new ProfessorService();
						Professor p = pADM.consultar(userAutentica.getId());
						if (p != null) {
							if (login.equals("administrador")) {
								if (p.getAdministrador()) {
									System.out.println(p.getMatricula());
									session.setAttribute("testeAux", "adm");
									response.sendRedirect("index.jsp");
								
				
								} else {
									response.sendRedirect("login.jsp?erro=admnull");
								}
							}

							else if (login.equals("professor")) {
								session.setAttribute("testeAux", "prof");
								response.sendRedirect("index.jsp");
								
					
							}
						} else {
							response.sendRedirect("login.jsp?erro=profnull");
						}

					} else if (login.equals("aluno")) {
						AlunoService aD = new AlunoService();
						Aluno a = aD.consultar(userAutentica.getId());

						if (a != null) {
							session.setAttribute("testeAux", "aluno");
							response.sendRedirect("index.jsp");			
			
						} else {
							response.sendRedirect("login.jsp?erro=alunonull");
						}
					}

				} else {
					response.sendRedirect("login.jsp?login=" + login + "&erro=loginfail");
				}
			} else {
				response.sendRedirect("login.jsp?login=" + login + "&erro=emailnull");
			}
		} else {
			response.sendRedirect("pagprincipal.html");
		}
	}

}
