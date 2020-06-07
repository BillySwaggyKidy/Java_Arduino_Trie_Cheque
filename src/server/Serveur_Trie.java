package server;

import cheque.Cheque;

import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

import com.fazecast.jSerialComm.*;

//javac -cp c:\../../classes:c:\../../classes/com/fazecast/jSerialComm-2.6.2.jar -d ../../classes Serveur_trie.java
//java -cp .:c:\../../classes:c:\../../classes/com/fazecast/jSerialComm-2.6.2.jar server.Serveur_trie

// ATTENTION: activer XAMPP avant de lancer le serveur pour que les clients se connectent

public class Serveur_trie extends Thread
{
    String name; // nom du client connecté au thread serveur
    Socket clientSocket;
    ObjectOutputStream out;
    private static HashMap<String, Serveur_trie> tableau_nom = new HashMap<String, Serveur_trie>(); // stocke les noms d'utilisateurs dans un tableau



    private int algorithme_trie(int num_donnee) // permet de trouver la case pour trier le cheque
    {
        return num_donnee;
    }

    private void display_port()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        for (int i = 0; i < ports.length; i++) 
        {
        System.out.println(ports[i].getSystemPortName());
        }
    }

    private void envoie_donnee_arduino(Integer num_led) throws Exception 
    // permet d'envoyer les données de l'algorithme à la partie Arduino13
    {
        display_port();
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem1411"); // port de la carte aruino Mega 2650
        System.out.println(sp.getDescriptivePortName());
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be written
    
        if (sp.openPort()) //ouvre le port en question en mode lecture et écriture
        {
            System.out.println("Port is open");
        }
        else 
        {
            System.out.println("Failed to open port");
            return;
        }
        
        System.out.println("thread sleep");
        Thread.sleep(2000);    
        System.out.println("byte value = " + num_led.byteValue());
        sp.write(num_led.byteValue()); // écrit les données en BYTE du serveur vers le port
        //sp.getOutputStream().flush(); // permet de forcet l'écriture des donnés du buffer  
          
        if (sp.closePort()) //ferme le port en question en mode lecture et écriture
        {
            System.out.println("Port is closed :)");
        } 
        else 
        {
            System.out.println("Failed to close port :(");
            return;
          
        }

    }

    public void run() // fonction du thread
    {

        try (
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        )
        {
            while(true)
            {
                Object o = in.readObject();
                if (o instanceof Cheque)
                {
                    Cheque cheque = (Cheque) o;
                    System.out.println("info: " + cheque.get_Num_case());
                    envoie_donnee_arduino(algorithme_trie(cheque.get_Num_case()));
                }
            }
           /* else 
            if(o instanceof String) 
            {
                if( ((String) o).equals("BYE") )
                {
                    break;
                }
            }*/
        }
        catch (IOException e)
        {
            System.err.println("Exception while closing connection!");
        }
        catch (Exception e)
        {

        }

    }

    public Serveur_trie(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) throws IOException // a executer dans un autre ordinateur
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue au serveur, Veillez saisir...");
        System.out.println("Votre numéro de port >"); // ATTENTION, si vous avez pas le root privilege alors prenez un numéro de port > 1024
        int num_port = scanner.nextInt(); // on enregistre le numéro du

        try (
        ServerSocket serverSocket = new ServerSocket(num_port); // on crée l'application serveur sur un port
        )
        {
            while (true)
            {
                Serveur_trie server = new Serveur_trie(serverSocket.accept()); // on attend et accepte la demande de connexion d'un client
                System.out.println("Client connecté");
                server.start();
            }
        }
        catch (IOException e)
        {
            System.err.println("Exception caught when trying to listen on port " + num_port);
            System.err.println(e.getMessage());
        }
        System.out.println("fermeture du serveur");
        scanner.close();

    }
}