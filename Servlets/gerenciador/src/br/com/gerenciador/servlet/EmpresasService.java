package br.com.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.gerenciador.modelo.Banco;
import br.com.gerenciador.modelo.Empresa;

@WebServlet("/empresas")
public class EmpresasService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> listaEmpresas = new Banco().getEmpresas();
		
		String contentType = request.getHeader("Accept");
		
		if(contentType.contains("xml")) {
			XStream xml = new XStream();
			xml.alias("Empresa", Empresa.class);
			String xmlOut = xml.toXML(listaEmpresas);
			
			response.setContentType("application/xml");
			response.getWriter().print(xmlOut);

		} else if(contentType.contains("json")) {
			Gson gson = new Gson();
			String json = gson.toJson(listaEmpresas);
			
			response.setContentType("application/json");
			response.getWriter().print(json);
		} else {
			response.getWriter().print("{'Message':'No content'}");
		}
	}

}
