package br.com.gerenciador.modelo;

import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

public class Banco {

	private static List<Empresa> listaEmpresas = new ArrayList<>(); 
	private static List<Usuario> listaUsuarios = new ArrayList<>(); 
	private static Integer chaveSequencial = 1;
	
	static {
		Empresa empresa = new Empresa();
		empresa.setId(chaveSequencial++);
		empresa.setNome("Alura");
		
		Empresa empresa2 = new Empresa();
		empresa2.setId(chaveSequencial++);
		empresa2.setNome("Caelum");
		
		listaEmpresas.add(empresa);
		listaEmpresas.add(empresa2);
		
		Usuario u1 = new Usuario();
		u1.setLogin("Henrique");
		u1.setSenha("12345");
		
		Usuario u2 = new Usuario();
		u2.setLogin("Carolina");
		u2.setSenha("54321");
		
		listaUsuarios.add(u1);
		listaUsuarios.add(u2);
	}
	
	
	public void adiociona(Empresa empresa) {
		System.out.println("A empresa " + empresa.getNome() + " foi adicionada.");
		empresa.setId(Banco.chaveSequencial++);
		Banco.listaEmpresas.add(empresa);
	}
	
	public List<Empresa> getEmpresas() {
		return Banco.listaEmpresas;
	}

	public void removeEmpresa(Integer id) {
		
		Iterator<Empresa> iterator = listaEmpresas.iterator();
		
		while(iterator.hasNext()) {
			Empresa empresa = iterator.next();
			if(empresa.getId() == id) {
				iterator.remove();
			}
		}
				
	}

	public Empresa buscaEmpresaPorId(Integer id) {
		
		for(Empresa empresa : Banco.listaEmpresas) {
			if(empresa.getId() == id) {
				return empresa;
			}
		}
		
		return null;
	}

	public Usuario existeUsuario(String login, String senha) {
		for(Usuario usuario : listaUsuarios) {
			if(usuario.ehIgual(login, senha)) {
				return usuario;
			}
		}
		return null;
	}

}
