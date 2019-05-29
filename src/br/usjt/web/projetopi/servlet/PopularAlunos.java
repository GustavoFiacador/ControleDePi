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
import br.usjt.web.projetopi.services.AlunoService;

/**
 * Servlet implementation class PopularAlunos
 */
@WebServlet({ "/PopularAlunos", "/PopularAlunos.do" })
public class PopularAlunos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularAlunos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int val = Integer.parseInt(request.getParameter("valor"));
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(val);
		
		PrintWriter out = response.getWriter();
		
		AlunoService aSer = new AlunoService();
		ArrayList<Aluno> lista = aSer.listarAlunosAlocados(val);
		
		for (Aluno aluno : lista)
		{
			out.print("<option value='"+ aluno.getId() + "'>"+aluno.getNome()+"</option>" 
					);
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
