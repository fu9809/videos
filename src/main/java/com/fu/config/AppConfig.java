package com.fu.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 *  spring 配置类
 * @author Administrator
 */
@Configuration
@ComponentScan(value = "com.fu", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})
@MapperScan("com.fu.dao")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {

    // 配置数据源
    @Bean
    public DataSource dataSource() {
        Properties properties = new Properties();
        try {
            Reader reader = Resources.getResourceAsReader("db.properties");
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(properties);
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        SqlSessionFactory sqlSessionFactory = null;
        // 数据源
        sqlSessionFactoryBean.setDataSource(dataSource());
        // 驼峰命名法
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        // 批量起别名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.fu.bean");
        // pageHelper 分页
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        try {
            // mapper映射文件
            PathMatchingResourcePatternResolver pr = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(pr.getResources("classpath:/mapper/*.xml"));
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    // 配置事务管理器
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager() {
        PlatformTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }

}