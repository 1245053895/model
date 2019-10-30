package com.xd.zt.util.data;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * java 远程读取linux文件
 * @author zhaochong
 */
public class ShellUtil {
    private static JSch jsch;
    private static Session session;

    /**
     * 配置连接
     *
     * @param user
     * @param KEYPATH
     * @param host
     * @param post
     * @throws Exception
     */
    public static void connect(String user, String KEYPATH, String host, int post) throws Exception {
        jsch = new JSch();
        session = jsch.getSession(user, host, post);
        if (session == null) {
            throw new Exception("session is null");
        }
//        session.setPassword(passwd);
        jsch.addIdentity(KEYPATH);
        java.util.Properties config = new java.util.Properties();
        //第一次登陆
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        try {
            session.connect(30000);
        } catch (Exception e) {
            throw new Exception("连接远程端口无效或用户名密码错误");
        }

    }

    public static String[] getArr(String str) {
        String[] arr = str.split(":");
        return arr;
    }

    /**
     * @param command shell 命令
     * @param user    用户名
     * @param KEYPATH  密码
     * @param host    ip地址
     * @param post    端口号
     * @throws Exception
     */
    public static void execCmd(String command, String user, String KEYPATH, String host, int post) throws Exception {
        System.out.println(command);
        connect(user, KEYPATH, host, post);
        BufferedReader reader = null;
        Channel channel = null;
        try {
            channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();
            InputStream in = channel.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
//            List<LoggerForm> list = new ArrayList<LoggerForm>();
            //返回数据
            while ((buf = reader.readLine()) != null) {
                String[] arr = buf.split("/|");
                for (int i = 0; i < arr.length; i++) {
                    System.out.println("异常时间:" + arr[1] + "错误信息:" + arr[2] + "异常类型:" + getArr(arr[0])[2] + "异常信息所在文件行数:" + getArr(arr[0])[1] + "日志文件名称:" + getArr(arr[0])[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel.disconnect();
            session.disconnect();
        }
    }
}