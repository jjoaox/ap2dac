package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidade.Jogo;
import util.JPAUtil;

public class JogoDao {
	
		public static void salvar(Jogo j) {
			EntityManager em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			em.persist(j);
			em.getTransaction().commit();
			em.close();
		}
		
		
		public static void atualizar(Jogo j) {
			EntityManager em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			em.merge(j);
			em.getTransaction().commit();
			em.close();
		}
		
		public static List<Jogo> listarJogos(){
			EntityManager em = JPAUtil.criarEntityManager();
			Query q = em.createQuery("select j from Jogo j");
			List<Jogo> retorno = q.getResultList();
			em.close();
			return retorno;
		}
		
		public static void deletar(Jogo j) {
			EntityManager em = JPAUtil.criarEntityManager();
			em.getTransaction().begin();
			j = em.find(Jogo.class, j.getId());
			em.remove(j);
			em.getTransaction().commit();
			em.close();
		}
		
		public static Jogo buscarPorId(Integer id) {
			EntityManager em = JPAUtil.criarEntityManager();
			Jogo j1 = em.find(Jogo.class, id);
			em.close();
			return j1;
		}
	
}
