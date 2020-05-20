package client;
import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

public class Client_Cheque 
{
    private static String hostName; // nom de l'hôte du serveur (147.94.7.152)
    private static int num_port; // le numéro du port que vous voulez utilisez pour établir la connexion avec le serveur

    public static void main(String [] args) throws Exception 
    {
        System.out.println("Lancement du client");
        hostName = args[1]; // on met le paramettre 1 de la ligne de commande dans la valeur hostName
        num_port = Integer.parseInt(args[2]); // on met le paramettre 2 de la ligne de commande dans la valeur num_port
        System.out.println("Connection au Serveur...");
    }

}