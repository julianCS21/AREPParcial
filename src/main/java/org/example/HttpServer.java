package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class HttpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        ServerSocket serverSocket = null;
        Page page = new Page();
        consultaService cs = new consultaService();
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        while(!serverSocket.isClosed()){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            String path = null;
            boolean firstline = true;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Recibí: " + inputLine);
                if(firstline){
                    path = inputLine.split("/")[1].split(" ")[0];
                    firstline = false;
                }
                if (!in.ready()) {break; }
            }
            if(path.startsWith("consulta")) {
                String request = path.split("\\?")[1];
                System.out.println(request);
                String ClassforSearch = null;
                String response = null;
                String comando = request.split("=")[1].split("\\(")[0];
                if(comando.equals("Class")){
                    ClassforSearch = request.split("=")[1].split("\\(")[1].split("\\)")[0];
                    response = cs.getClass(ClassforSearch);
                }
                String header = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n";
                outputLine = header + response;

            }
            else{
                String header = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n";
                String pageGPT = page.getPage();
                outputLine = header +  pageGPT;


            }

            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();


        }
        serverSocket.close();
    }
}