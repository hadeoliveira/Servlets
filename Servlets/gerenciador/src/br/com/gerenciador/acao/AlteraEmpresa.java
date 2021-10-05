package br.com.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gerenciador.modelo.Banco;
import br.com.gerenciador.modelo.Empresa;

public class AlteraEmpresa implements Acao {
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		System.out.println("Alterando empresa...");
		
		String nomeEmpresa = request.getParameter("nome");
		String dataAbertura = request.getParameter("data");
		String idEmpresa = request.getParameter("id");
		
		Integer id = Integer.valueOf(idEmpresa);
		
		Date data = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			data = sdf.parse(dataAbertura);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		Banco banco = new Banco();
		Empresa empresa = banco.buscaEmpresaPorId(id);
		
		empresa.setNome(nomeEmpresa);
		empresa.setDataAbertura(data);
		
		return "redirect:entrada?acao=ListaEmpresas";
	}
}
