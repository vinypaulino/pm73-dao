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

		Usuario novoUsuario = new Usuario("Jo�o da Silva", "jo�o@dasilva.com.br");
		usuarioDao.salvar(novoUsuario);

		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Jo�o da Silva", "jo�o@dasilva.com.br");

		assertEquals("Jo�o da Silva", usuarioDoBanco.getNome());
		assertEquals("jo�o@dasilva.com.br", usuarioDoBanco.getEmail());
	}

	@Test
	public void deRetornarNuloSeNaoEncontrarUsuario() {

		Usuario usuarioDoBanco = usuarioDao.porNomeEEmail("Jo�o Joaquim", "joao@joaquim.com.br");
		assertNull(usuarioDoBanco);
	}
}
