package server;

import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

public class Serveur_Trie 
{
    private static int num_port;
    private static boolean is_running = true;



    public static void main(String[] args) throws IOException // a executer dans un autre ordinateur
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue au serveur, Veillez saisir...");
        System.out.println("Votre numéro de port >");
        num_port = scanner.nextInt();

        try (
        ServerSocket serverSocket = new ServerSocket(num_port); // on crée l'application serveur sur un port
        )
        {
            while (is_running == true)
            {
                Thread_serveur thread_serveur = new Thread_serveur(serverSocket.accept(), tableau_nom); // on attend et accepte la demande de connexion d'un client
                thread_serveur.start();
            }
        }
        catch (IOException e)
        {

        }

    }
}