/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.BufferedInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 *
 * @author ARJoshi0
 */
public class Server {
    
    public static void main(String[] args) throws Exception {
        //This will initialize the sockets
        ServerSocket ss1 = new ServerSocket(4500);
        Socket sock1 = ss1.accept();
        
        //This will specify InetAddress
        InetAddress IA = InetAddress.getByName("localhost");
        
        //Identifies which file the client wants
        File f1 = new File("e://data1.bin");
        FileInputStream fis1 = new FileInputStream(f1);
        BufferedInputStream bis1 = new BufferedInputStream(fis1);
        
        //Retrieves the socket output
        OutputStream out1 = sock1.getOutputStream();
        
        //Read the file
        byte[] contents;
        long fLength = f1.length();
        long current = 0;
        while(current!=fLength)
        {
            int size = 10000;
            if(fLength - current >= size)
                current += size;
            else
            {
                size = (int)(fLength - current);
                current = fLength;
            }
            contents = new byte[size];
            bis1.read(contents, 0, size);
            out1.write(contents);
            System.out.print("Sending file: " + (current*100)/fLength + "%");
        }
        //Once file has been sent, socket should disconnect
        out1.flush();
        sock1.close();
        ss1.close();
        System.out.println("File has been sent");
    }
}
