package com.xd.zt.util.analyse;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

import java.io.IOException;

public class DownloadFileUtil {

        public static void downloadFromLinux(String IP,Integer PORT,String USER ,String PASSWORD,String fileResourcePath,String fileTargetPath){
            Connection connection = new Connection(IP,PORT);
            try {
                connection.connect();
                boolean isAuthed = connection.authenticateWithPassword(USER,PASSWORD);
                if (isAuthed == true){
                    System.out.printf("连接成功");

                    SCPClient scpClient = connection.createSCPClient();

                    scpClient.get(fileResourcePath, fileTargetPath);
                }
                else {
                    System.out.printf("连接失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.printf("下载失败");
            }

        }

    }

