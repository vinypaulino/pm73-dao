package br.com.caelum.pm73.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.pm73.dominio.Usuario;

public class UsuarioDaoTest {

	private Session session;
	private UsuarioDao usuarioDao;

	@Before
	public void setUp() {
		session = (Session) new CriadorDeSessao().getSession();
		usuarioDao = new UsuarioDao(session);
	}
	
	@After
	public void endSession() {
		session.close();
	}

	@Test
	public void deveEncontrarPeloNomeEEmail() {

		Usuario novoUsuario = new Usuario("João da Silva", "joão@dasilva.com.br");
		usuarioDao.salvar(novoUsuario);

		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("João da Silva", "joão@dasilva.com.br");

		assertEquals("João da Silva", usuarioDoBanco.getNome());
		assertEquals("joão@dasilva.com.br", usuarioDoBanco.getEmail());
	}

	@Test
	public void deRetornarNuloSeNaoEncontrarUsuario() {

		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("João Joaquim", "joao@joaquim.com.br");
		assertNull(usuarioDoBanco);
	}
}
