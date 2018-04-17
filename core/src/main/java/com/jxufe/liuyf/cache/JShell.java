package com.jxufe.liuyf.cache;

import com.jcraft.jsch.*;
import com.jxufe.liuyf.cache.redis.JedisClusterHolder;
import org.slf4j.*;

import java.io.*;


/**
 * description: 全量刷新缓存
 * author: Navy
 * create_date : 2018/4/15
 * create_time : 17:07
 */

public class JShell {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(JShell.class);
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String HOST = "192.168.85.2";
    private static final int DEFAULT_SSH_PORT = 22;
    private static final String SCRIPTLOCATION = "/home/liuyf/app/redis/flush.sh\n";
    private static final int RESTART_TIMEOUT = 60;


    /**
     * @param
     * @Description: 调用此方法  即可重启redis  全量刷新缓存
     * @author liuyf
     * @date 2018/4/15 19:58
     */
    public static void flushAllCache() {
        synchronized (JedisClusterHolder.LOCK) {
            Session session = null;
            Channel channel = null;
            try {
                JSch jsch = new JSch();

                session = jsch.getSession(USER, HOST, DEFAULT_SSH_PORT);
                session.setPassword(PASSWORD);
                session.setConfig("StrictHostKeyChecking", "no");
                session.setUserInfo(new UserInfo() {
                    @Override
                    public String getPassphrase() {
                        System.out.println("getPassphrase");
                        return null;
                    }

                    @Override
                    public String getPassword() {
                        System.out.println("getPassword");
                        return null;
                    }

                    @Override
                    public boolean promptPassword(String s) {
                        System.out.println("promptPassword:" + s);
                        return false;
                    }

                    @Override
                    public boolean promptPassphrase(String s) {
                        System.out.println("promptPassphrase:" + s);
                        return false;
                    }

                    @Override
                    public boolean promptYesNo(String s) {
                        System.out.println("promptYesNo:" + s);
                        return true;//notice here!
                    }

                    @Override
                    public void showMessage(String s) {
                        System.out.println("showMessage:" + s);
                    }
                });
                session.connect();
                String command = SCRIPTLOCATION;
                channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand(command);
                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);
                InputStream in = channel.getInputStream();
                channel.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String rtnString = null;
                for (int tryTime = 1; tryTime <= RESTART_TIMEOUT; tryTime++) {
                    log.info("尝试第" + tryTime + "次判断集群是否启动成功");
                    rtnString = bufferedReader.readLine();
                    if (rtnString != null) {
                        log.info(rtnString);
                        if (rtnString.equalsIgnoreCase("READY")) {
                            log.info("redis集群重启成功");
                            break;
                        }
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (channel.isConnected()) {
                    channel.disconnect();
                }
                if (session.isConnected()) {
                    session.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (channel != null && channel.isConnected()) {
                    channel.disconnect();
                }
                if (session != null && session.isConnected()) {
                    session.disconnect();
                }
            }
        }
    }
}