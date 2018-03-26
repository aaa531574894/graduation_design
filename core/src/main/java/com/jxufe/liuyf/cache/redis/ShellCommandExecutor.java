package com.jxufe.liuyf.cache.redis;

import com.jcraft.jsch.*;
import com.jxufe.lyf.utils.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * description:
 * author: LYF
 * create_date : 2018/3/22
 * create_time : 14:42
 */

@Component
public class ShellCommandExecutor {
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(ShellCommandExecutor.class);

    private final static String FLUSH_REDIS_COMMAND = "/home/liuyf/app/redis/flushall.sh";

    private JSch jSch = null;
    private Session session;
    private ChannelExec  channelExec;

    @Value("${vitural.machine1.user}")
    private String user;
    @Value("${vitural.machine1.host}")
    private String host;
    @Value("${vitural.machine1.port}")
    private String port;
    @Value("${vitural.machine1.password}")
    private String password;

    public ShellCommandExecutor() {
    }

    private void connectAndFlushDB() {
        if (StringUtils.isNullOrEmpty(user) || StringUtils.isNullOrEmpty(host) || StringUtils.isNullOrEmpty(port)) {
            throw new NullPointerException("初始化ShellCommandExecutor失败 , 因为host 或 port 或 user 为空, 请检查");
        }
        jSch = new JSch();
        String result = "";
        UserInfo userInfo = new UserInfo() {
            @Override
            public String getPassphrase() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public boolean promptPassword(String message) {
                log.info(message);
                return false;
            }

            @Override
            public boolean promptPassphrase(String message) {
                log.info(message);
                return false;
            }

            @Override
            public boolean promptYesNo(String message) {
                log.info(message);
                return false;
            }

            @Override
            public void showMessage(String message) {
                log.info(message);
            }
        };
        try {

            // 调用openChannel(String type)
            // 可以在session上打开指定类型的channel。该channel只是被初始化，使用前需要先调用connect()进行连接。
            //   Channel的类型可以为如下类型：
            //   shell - ChannelShell
            //   exec - ChannelExec
            //   direct-tcpip - ChannelDirectTCPIP
            //   sftp - ChannelSftp
            //   subsystem - ChannelSubsystem
            // 其中，ChannelShell和ChannelExec比较类似，都可以作为执行Shell脚本的Channel类型。它们有一个比较重要的区别：ChannelShell可以看作是执行一个交互式的Shell，而ChannelExec是执行一个Shell脚本。
            session = jSch.getSession(user, host, Integer.valueOf(port));
            session.setUserInfo(userInfo);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(20*1000);


            channelExec =(ChannelExec) session.openChannel("exec");
            channelExec.setCommand(FLUSH_REDIS_COMMAND.getBytes());
            int exitStatus = channelExec.getExitStatus();
            log.info(String.valueOf(exitStatus));
            channelExec.setInputStream(null);
            channelExec.connect();

        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            if (session.isConnected()) {
                session.disconnect();
            }
            if (channelExec.isConnected()) {
                channelExec.disconnect();
            }
        }
    }

    public void flushDB() {
        connectAndFlushDB();
    }


}
