package br.com.ngbilling.gestaobancaria.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static final EntityManagerFactory emf = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			// Carregar arquivo properties
			Properties props = new Properties();
			try (InputStream in = JPAUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
				props.load(in);
			}

			Map<String, String> properties = new HashMap<>();
			properties.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));
			properties.put("jakarta.persistence.jdbc.user", props.getProperty("db.user"));
			properties.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));
			properties.put("jakarta.persistence.jdbc.driver", props.getProperty("db.driver"));

			// Configurações do hibernate
			properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // Dialeto
			properties.put("hibernate.show_sql", "true"); // mostrar sql no console - prefiro que mostre
			properties.put("hibernate.format_sql", "true"); // formatar o sql, prefiro assim, mas para não puluir muito o console é bom deixar false
			properties.put("hibernate.hbm2ddl.auto", "update");

			// Pool Hikari
			properties.put("hibernate.hikari.maximumPoolSize", "10");
			properties.put("hibernate.hikari.minimumIdle", "2");
			properties.put("hibernate.hikari.idleTimeout", "30000");
			properties.put("hibernate.hikari.connectionTimeout", "20000");

			return Persistence.createEntityManagerFactory("Gestao", properties);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
		}
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
