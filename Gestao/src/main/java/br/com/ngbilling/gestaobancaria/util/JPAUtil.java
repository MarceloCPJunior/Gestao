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
	private static final String ENV_DEV = System.getenv().getOrDefault("ENV", "dev");
	private static final String ENV_PROD = System.getenv().getOrDefault("ENV", "prod");

	private static final EntityManagerFactory emf_dev = buildEntityManagerFactory(ENV_DEV);
	private static final EntityManagerFactory emf_prod = buildEntityManagerFactory(ENV_PROD);

	private static EntityManagerFactory buildEntityManagerFactory(String propertiesFile) {
		try {
			Properties props = new Properties();
			String nmArquivo = "db-" + propertiesFile + ".properties";
			try (InputStream in = JPAUtil.class.getClassLoader().getResourceAsStream(nmArquivo)) {
				if (in == null) {
					throw new RuntimeException("Arquivo de configuração não encontrado: " + propertiesFile);
				}
				props.load(in);
			}

			Map<String, String> properties = new HashMap<>();
			properties.put("jakarta.persistence.jdbc.url", props.getProperty("db.url"));
			properties.put("jakarta.persistence.jdbc.user", props.getProperty("db.user"));
			properties.put("jakarta.persistence.jdbc.password", props.getProperty("db.password"));
			properties.put("jakarta.persistence.jdbc.driver", props.getProperty("db.driver"));

			properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "true");
			properties.put("hibernate.hbm2ddl.auto", "update");

			properties.put("hibernate.hikari.maximumPoolSize", "10");
			properties.put("hibernate.hikari.minimumIdle", "2");
			properties.put("hibernate.hikari.idleTimeout", "30000");
			properties.put("hibernate.hikari.connectionTimeout", "20000");

			return Persistence.createEntityManagerFactory("Gestao", properties);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
		}
	}

	public static EntityManager getEntityManager(String env) {
		if (env != null && "prod".equalsIgnoreCase(env)) {
			return emf_prod.createEntityManager();
		} else {
			return emf_dev.createEntityManager();
		}
	}
}
