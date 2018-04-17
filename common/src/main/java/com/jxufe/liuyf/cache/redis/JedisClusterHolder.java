package com.jxufe.liuyf.cache.redis;

import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * description:  初始化并持有jedisCluster对象
 * author: LYF
 * create_date : 2018/3/16
 * create_time : 21:10
 */
@Component
@Scope("singleton")
public class JedisClusterHolder {
    @Autowired
    private JedisSubscriber jedisSubscriber;

    private final static Logger log = LoggerFactory.getLogger(JedisClusterHolder.class);

    @Value("${redis.hostAndPort}")
    private String host_and_ports;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.maxWait}")
    private String max_wait;
    @Value("${redis.timeOut}")
    private String time_out;


    public static final Object LOCK = new Object();
    private JedisCluster mainCluster = null;  //主集群

    private Set<HostAndPort> configSet = new HashSet<HostAndPort>();

    public JedisClusterHolder() {

    }


    public JedisCluster getJedisCluster() {

        if (this.mainCluster == null) {
            synchronized (LOCK) {
                init();
            }
        }
        return mainCluster;
    }


    /**
     * 　* @Description: 初始化持有的jedisCluter对象
     * 　* @author liuyf
     * 　* @date 2018/3/18 11:50
     */
    private void init() {
        if (StringUtils.isNullOrEmpty(host_and_ports)) {
            try {
                throw new Exception("初始化jedisShardPool失败: hostAndPort为空");
            } catch (Exception e) {
                log.error("初始化jedisPoll失败" + e);
            }
        } else {
            String hostAndPorts[] = host_and_ports.split(";");
            for (String hostAndPort : hostAndPorts) {
                String hs_ps[] = hostAndPort.split(":");
                configSet.add(new HostAndPort(hs_ps[0], Integer.valueOf(hs_ps[1])));
                log.info("jedis cluster info :  host " + hs_ps[0] + "port   " + hs_ps[1]);
            }
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setTestOnBorrow(true);
            if (!StringUtils.isNullOrEmpty(max_wait)) {
                poolConfig.setMaxWaitMillis(Long.valueOf(max_wait));
                log.info("jedis cluster 连接池最大阻塞等待时间: " + max_wait);
            }
            try {
                this.mainCluster = new JedisCluster(configSet, 10000, 5000, 10, poolConfig);
            } catch (Exception e) {
                log.error("初始化 jedisCluster失败....");
                e.printStackTrace();
            }

        }
        jedisSubscriber.startLister();

    }

    public void resetCluser() {
        synchronized (LOCK) {
            this.mainCluster = null;
            init();
        }

    }


}
