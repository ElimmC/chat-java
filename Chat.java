/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Sonia
 */
public class Chat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String deci = "no";
        Scanner leer = new Scanner(System.in);

//        Cambiar puerto segun se necesite
        int estePuerto = 8080;
        final int MAX_LON = 10;
        int puertoReceptor = 8080;
        String ip = "";

        System.out.println("IP de la maquina receptora");
        ip = leer.nextLine();
        System.out.println("puerto de esta maquina");
        estePuerto = Integer.parseInt(leer.nextLine());
        System.out.println("puerto de la otra maquina ");
        puertoReceptor = Integer.parseInt(leer.nextLine());
        
        try {
            InetAddress maquinaReceptora = InetAddress.getByName(ip);
            DatagramSocket miSocket = new DatagramSocket(estePuerto);

            while (deci.equalsIgnoreCase("si")) {

                try {
                    System.out.println("Te toca: ");
                    String mensaje = leer.nextLine();
                    byte[] almacén = mensaje.getBytes();
                    DatagramPacket datagrama = new DatagramPacket(almacén, almacén.length, maquinaReceptora, puertoReceptor);
                    miSocket.send(datagrama);
                    System.out.println("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    byte[] almacénR = new byte[MAX_LON];
                    DatagramPacket datagramaR = new DatagramPacket(almacénR, MAX_LON);
                    miSocket.receive(datagramaR);
                    String mensajeR = new String(almacénR);
                    System.out.println("El dice:");
                    System.out.println(mensajeR);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                System.out.println("Continuar? si/no");
                deci = leer.nextLine();

            }
            miSocket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
