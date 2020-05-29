package client;
import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

public class Client_cheque 
{
    private static String hostName; // nom de l'hôte du serveur (147.94.7.152)
    private static int numPort; // le numéro du port que vous voulez utilisez pour établir la connexion avec le serveur
    private static ObjectOutputStream out;
    private static String nom_util;
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
        hostName = args[1]; // on met le paramettre 1 de la ligne de commande dans la valeur hostName
        numPort = Integer.parseInt(args[2]); // on met le paramettre 2 de la ligne de commande dans la valeur num_port
        System.out.println("Veillez saisir votre nom: ");
        nom_util = userInput.nextLine();
        System.out.println("Connection au Serveur [" + "hôte: " + hostName + ", port: " + numPort + "] sous le nom: " + nom_util);


        try 
        (
            Socket clientSocket = new Socket(hostName,numPort); // on crée un objet clientSocket qui communique avec un serveur
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); // le out devient le out du clientSOcket pour parler avec le serveur
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream()); // le int devient le int du clientSOcket pour recevoir les réponses du serveur
        )
        {
            out.writeObject(nom_util);
            Client_cheque.out = out; // on envoie un message au serveurs
            System.out.println("Chargement...");
            Thread.sleep(1000);


        }
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
        } 
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } 
    }

}