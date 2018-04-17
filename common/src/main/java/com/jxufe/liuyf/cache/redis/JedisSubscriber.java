package com.jxufe.liuyf.cache.redis;

import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.HashSet;
import java.util.Set;

/**
 * description: please add the description
 * author: Navy
 * create_date : 2018/4/17
 * create_time : 20:24
 */
@Component
@Scope("singleton")
public class JedisSubscriber {
    private static final Logger log = LoggerFactory.getLogger(JedisSubscriber.class);

    public final static String SUB_CHANNEL="CAN_BE_ACCESS";  //被监听的频道

    @Value("${redis.hostAndPort}")
    private String host_and_ports;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.maxWait}")
    private String max_wait;
    @Value("${redis.timeOut}")
    private String time_out;

    private JedisCluster jedisCluster;
    private Set<HostAndPort> configSet = new HashSet<HostAndPort>();

    public JedisSubscriber() {

    }

    /**
     * @param
     * @Description: 开始监听redis集群的状态
     * @author liuyf
     * @date 2018/4/17 20:39
     */
    public void startLister() {
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                jedisCluster.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        super.onMessage(channel, message);
                        log.info("redis 状态改变" + message);
                        if (message.trim().equalsIgnoreCase("TRUE")) {
                            JedisController.IS_CACHE_READY = Boolean.TRUE;
                        } else if (message.trim().equalsIgnoreCase("FALSE")) {
                            JedisController.IS_CACHE_READY = Boolean.FALSE;
                        }
                    }
                }, SUB_CHANNEL);
            }
        }).start();

    }

    private void init() {
        if (StringUtils.isNullOrEmpty(host_and_ports)) {
            try {
                throw new Exception("初始化jedisCluster失败: hostAndPort为空");
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
                this.jedisCluster = new JedisCluster(configSet, 10000, 5000, 10, poolConfig);
            } catch (Exception e) {
                log.error("初始化 jedisCluster失败....");
                e.printStackTrace();
            }

        }
    }
}
