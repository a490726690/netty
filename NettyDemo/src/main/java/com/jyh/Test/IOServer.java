package com.jyh.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jyh-mac on 2018/9/26.
 * 接收线程
 */
public class IOServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        // (1) 接收新连接线程
        new Thread(()->{
            while (true){
                try {
                    // (1) 阻塞方法获取新的连接
                    Socket socket = serverSocket.accept();
                    // (2) 每一个新的连接都创建一个线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                /**
                                 *通过使用平台的默认字符集编码指定的字节子阵列来构造新的String
                                 * bytes 要解码为字符的字节
                                 * offset 要解码的第一个字节的索引
                                 */
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {

                        }
                    }).start();

                }catch (IOException e){

                }

            }
        }).start();

    }
}
