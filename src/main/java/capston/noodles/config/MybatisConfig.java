package capston.noodles.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@MapperScan(basePackages = {"capston.noodles"})
public class MybatisConfig {
    private ApplicationContext applicationContext;

    @Value("com.mysql.cj.jdbc.Driver")
    private String driverClassName;
    @Value("jdbc:mysql://localhost:3306/Noodles?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul")
    private String url;
    @Value("root")
    private String username;
    @Value("1100")
    private String password;

    public MybatisConfig(ApplicationContext applicationContext) {this.applicationContext = applicationContext;}

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        Resource[] resources = applicationContext.getResources("classpath:/mybatis/mappers/*.xml");
        factoryBean.setMapperLocations(resources);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public TransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

}
