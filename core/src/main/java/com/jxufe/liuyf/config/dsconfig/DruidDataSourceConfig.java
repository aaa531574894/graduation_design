package com.jxufe.liuyf.config.dsconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * description: please add the description
 * author: LYF
 * create_date : 2018/3/21
 * create_time : 16:20
 */
@Configuration
public class DruidDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "druid.datasource")
    public DataSource getDruidDataSource() {
        return new DruidDataSource();
    }
}
