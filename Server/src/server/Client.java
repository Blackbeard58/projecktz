/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.InetAddress;
import java.net.Socket;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
/**
 *
 * @author ARJoshi0
 */
public class Client {
    public static void main(String[] args) throws Exception
    {
        //This should initialize the server socket
        Socket sock1 = new Socket(InetAddress.getByName("localhost"), 4500);
        byte[] contents = new byte[10000];
        //This should set the output's path for transmission
        FileOutputStream fos1 = new FileOutputStream("e://data2.bin");
        BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
        InputStream in1 = sock1.getInputStream();
        int nobytes = 0;
        while((nobytes=in1.read(contents))!=-1)
        {
            bos1.write(contents, 0, nobytes);
        }
        bos1.flush();
        sock1.close();
        System.out.println("Your file has been received");
    }
}
