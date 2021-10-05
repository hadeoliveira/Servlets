package br.com.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciador.acao.Acao;
import br.com.gerenciador.acao.AlteraEmpresa;
import br.com.gerenciador.acao.ListaEmpresas;
import br.com.gerenciador.acao.MostraEmpresa;
import br.com.gerenciador.acao.NovaEmpresa;
import br.com.gerenciador.acao.NovaEmpresaForm;
import br.com.gerenciador.acao.RemoveEmpresa;

// @WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
//		
//		HttpSession sessao = request.getSession();
//		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
//		boolean ehAcaoProtegida = !(paramAcao.equals("LoginForm") || paramAcao.equals("Login"));
//		
//		if(ehAcaoProtegida & usuarioNaoEstaLogado) {
//			response.sendRedirect("entrada?acao=LoginForm");
//			return;
//		}
		
		String nomeDaClasse = "br.com.gerenciador.acao." + paramAcao;
		
		String nome = null;
		try {
			Class classe = Class.forName(nomeDaClasse); //carrega a classe com o nome da String
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException 
				| IllegalAccessException | ServletException
				| IOException e) {
			throw new ServletException(e);
		}
		
		String[] endereco = nome.split(":");
		if(endereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + endereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(endereco[1]);
		}
		
/*		
		String nome = null;
		if(paramAcao.equals("RemoveEmpresa")) {
			RemoveEmpresa acao = new RemoveEmpresa();
			nome = acao.executa(request, response);

		} else if(paramAcao.equals("ListaEmpresas")) {
			ListaEmpresas acao = new ListaEmpresas();
			nome = acao.executa(request, response);	
			
		} else if(paramAcao.equals("MostraEmpresa")) {
			MostraEmpresa acao = new MostraEmpresa();
			nome = acao.executa(request, response);			
		} else if(paramAcao.equals("AlteraEmpresa")) {
			AlteraEmpresa acao = new AlteraEmpresa();
			nome = acao.executa(request, response);			
		} else if(paramAcao.equals("NovaEmpresa")) {
			NovaEmpresa acao = new NovaEmpresa();
			nome = acao.executa(request, response);			
		}  else if(paramAcao.equals("NovaEmpresaForm")) {
			NovaEmpresaForm acao = new NovaEmpresaForm();
			nome = acao.executa(request, response);			
		} 
*/
		
	}

}
