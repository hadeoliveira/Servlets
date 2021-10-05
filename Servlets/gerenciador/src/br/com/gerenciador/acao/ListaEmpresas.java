package br.com.gerenciador.acao;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciador.modelo.Banco;
import br.com.gerenciador.modelo.Empresa;

public class ListaEmpresas implements Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Listando empresas...");
		
		Banco banco = new Banco();
		List<Empresa> listaEmpresas = banco.getEmpresas();
		
		request.setAttribute("empresas", listaEmpresas);
		return "forward:listaEmpresas.jsp";
	}
}
