package com.graduation.renthouse.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sessionFactory")
public class DataSourceConfig {

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com/graduation/house/rent/**/**Dao";
    static final String MAPPER_LOCATION = "classpath:mapper/**/*Mapper.xml";

    @Value("spring.datasource.url")
    private String url;
    @Value("spring.datasource.driver-class-name")
    private String driver;

    @Value("spring.datasource.username")
    private String name;

    @Value("spring.datasource.password")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration(){
        return new org.apache.ibatis.session.Configuration();
    }


    @Bean("secondDataSource")
    @Primary
    @Qualifier("DataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        DruidDataSource dataSource= new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setName(name);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean("sessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("DataSource") DataSource dataSource) throws Exception {
       final SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfiguration(configuration());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean("TransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
