package client;
import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

import cheque.Cheque;

// javac -cp c:\../../classes  -d ../../classes Client.java
// java -cp .:c:\../../classes:c:\../../classes/com/fazecast/jSerialComm-2.6.2.jar client.Client localhost 1300 

public class Client 
{
    private static String hostName; // nom de l'hôte du serveur (147.94.7.152)
    private static int numPort; // le numéro du port que vous voulez utilisez pour établir la connexion avec le serveur
    private static ObjectOutputStream out;
    private static String nomUtil;
    private static Scanner userInput = new Scanner(System.in);




    public void run() // fonction activé par le thread.
    {

    }


    public static void main(String [] args) throws Exception 
    {
        if (args.length < 2 && args.length > 3) 
        {
            System.err.println(
                "Usage: java Client <host name> <port number> [mode]");
            System.exit(1);
        }

        System.out.println("Lancement du client");
        hostName = args[0]; // on met le paramettre 1 de la ligne de commande dans la valeur hostName
        numPort = Integer.parseInt(args[1]); // on met le paramettre 2 de la ligne de commande dans la valeur num_port
        System.out.println("Veillez saisir votre nom: ");
        nomUtil = userInput.nextLine();
        System.out.println("Connection au Serveur [" + "hôte: " + hostName + ", port: " + numPort + "] sous le nom: " + nomUtil);


        try 
        (
            Socket clientSocket = new Socket(hostName,numPort); // on crée un objet clientSocket qui communique avec un serveur
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); // le out devient le out du clientSOcket pour parler avec le serveur
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); // le int devient le int du clientSOcket pour recevoir les réponses du serveur
        )
        {
            while (true)
            {
                System.out.println("Veillez mettre le Trigramme du chèque: ");
                String trigramme = userInput.nextLine();
                Cheque test_cheque = new Cheque(trigramme);
                out.writeObject(test_cheque); // on écrie un objet Cheque dans le out
                Client.out = out; // on envoie un message de type Cheque au serveurs
                System.out.println("Chargement...");
                Thread.sleep(1000);
            }



        }
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } 
        /*catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }*/
    }

}