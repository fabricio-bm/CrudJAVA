package br.com.CrudPOO.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.CrudPOO.util.HibernateUtil;

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;
	
	public GenericDAO() {
		this.classe = (Class<Entidade>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabrica().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}

	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabrica().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}

	public void editar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabrica().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
		}

	}
	public ArrayList<Entidade> listar(){
		Session sessao = HibernateUtil.getFabrica().openSession();		
		try {
			CriteriaBuilder builder = sessao.getCriteriaBuilder();
			CriteriaQuery<Entidade> consulta = builder.createQuery(classe);
			consulta.from(classe);
			ArrayList<Entidade> resultado = (ArrayList<Entidade>) sessao.createQuery(consulta).getResultList();
			return resultado;

		} catch (RuntimeException e) {
				throw e;
				
		} finally {
			sessao.close();
		}

	}
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabrica().openSession();		
		Entidade resultado = null;
		try {
			resultado = sessao.find(classe, codigo);
			return resultado;
			

		} catch (RuntimeException e) {
				throw e;
				
		}finally {
			sessao.close();
		}
	}
	public void Novo (Entidade entidade) {
		Session sessao = HibernateUtil.getFabrica().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();

		} catch (RuntimeException e) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw e;
		} finally {
			sessao.close();
}
	}
}
