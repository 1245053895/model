//package com.xd.zt.util.analyse;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import java.util.Vector;
//
//public class ReadLinuxFile {
//    public void readFile() {
//        //创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
//        //new Connection(ip, port)创建对象
//        String ip = "";
//        String usr = "";
//        String pwd = "";
//        //int     port=22;
//        Connection conn = null;
//        String date = "";
//        String path="";
//        try {
//            //连接远程服务器
//            // 连接部署服务器
//            conn = new Connection(ip);
//            conn.connect();
//            //使用用户名和密码登录
//            boolean b = conn.authenticateWithPassword(usr, pwd);
//            if (!b) {
//                throw new IOException("Authentication failed.");
//            } else {
//                SFTPv3Client sft = new SFTPv3Client(conn);
//                Vector<?> v = sft.ls("path");
//                for (int i = 0; i < v.size(); i++) {
//                    SFTPv3DirectoryEntry s = new SFTPv3DirectoryEntry();
//                    s = (SFTPv3DirectoryEntry) v.get(i);
//                    //文件名
//                    String filename = s.filename;
//                    if (filename.length() > 17) {
//                        String substring = filename.substring(7, 17);
//                        if (substring.equals(date)) {
//                            System.out.println(filename);
//
//                            Session ss=null;
//                            ss=conn.openSession();
//                            ss.execCommand("cat ".concat("path"+filename));
//                            InputStream    is = new StreamGobbler(ss.getStdout());
//                            BufferedReader bs = new BufferedReader(new InputStreamReader(is));
//                            while(true){
//                                String line = bs.readLine();
//                                if(line==null){
//                                    break;
//                                }else{
//                                    System.out.println("abc........................"+line);
//                                }
//                            }
//                            bs.close();
//                            ss.close();
//                            conn.close();
//                        }
//                    }
//                }
//
//            }
//        } catch (IOException e) {
//            System.err.printf("用户%s密码%s登录服务器%s失败！", usr, pwd, ip);
//            e.printStackTrace();
//        }
//
//    }
//}
