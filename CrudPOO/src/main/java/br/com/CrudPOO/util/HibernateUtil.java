package br.com.CrudPOO.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory fabrica = criarFabricaDeSessoes();

	public static SessionFactory getFabrica() {
		return fabrica;
	}

	public static void setFabrica(SessionFactory fabrica) {
		HibernateUtil.fabrica = fabrica;
	}

	public static SessionFactory criarFabricaDeSessoes() {

		try {
			Configuration config = new Configuration().configure("Hibernate.cfg.xml");
			SessionFactory fb = config.buildSessionFactory();
			return fb;

		} catch (Throwable e) {
			System.err.println("A conexão com o banco nao pode ser estabelecida " + e);
			throw new ExceptionInInitializerError(e);

		}
	}

}
