package br.com.caelum.pm73.dao;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {

	@Test
	public void deveEncontrarPeloNomeEEmail() {
		Session session = (Session) new CriadorDeSessao().getSession();
		UsuarioDao usuarioDao = new UsuarioDao(session);

		Usuario novoUsuario = new Usuario("João da Silva", "joão@dasilva.com.br");
		usuarioDao.salvar(novoUsuario);

		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("João da Silva", "joão@dasilva.com.br");

		assertEquals("João da Silva", usuarioDoBanco.getNome());
		assertEquals("joão@dasilva.com.br", usuarioDoBanco.getEmail());

		session.close();
	}

}
