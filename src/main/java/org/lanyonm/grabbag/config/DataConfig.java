package org.lanyonm.grabbag.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.lanyonm.grabbag.persistence.IngredientMapper;
import org.lanyonm.grabbag.persistence.RecipeMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
//@Profile({"dev", "test"})
public class DataConfig {

	@Bean
	public javax.sql.DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("database/schema.sql")
				.addScript("database/init.sql")
				.build();
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("org.lanyonm.grabbag.domain");
		return sessionFactory.getObject();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private MapperFactoryBean getMapperFactoryBean(Class c) throws Exception {
		MapperFactoryBean bean = new MapperFactoryBean();
		bean.setSqlSessionFactory(sqlSessionFactory());
		bean.setMapperInterface(c);
		bean.afterPropertiesSet();
		return bean;
	}

	@Bean
	public RecipeMapper recipeMapper() throws Exception {
		return (RecipeMapper) getMapperFactoryBean(RecipeMapper.class).getObject();
	}

	@Bean
	public IngredientMapper ingredientMapper() throws Exception {
		return (IngredientMapper) getMapperFactoryBean(IngredientMapper.class).getObject();
	}

}
