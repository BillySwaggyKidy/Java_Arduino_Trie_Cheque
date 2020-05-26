package server;


import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java

import com.fazecast.jSerialComm.*;

public class Thread_serveur extends Thread
{
    private int num_cheque; // TEMPORAIRE: pour tester l'envoie de donnee du client -> serveur -> arduino
    private ObjectOutputStream out_thread; // on crée un objet capable de contenir un message que on va envoyer au serveur
    private ObjectInputStream in_thread; //  on crée un objet capable de contenir la réponse du serveur

    public Thread_serveur(int num)
    {
        num_cheque = num;
    }


    public int algorithme_trie(int num_donnee) // permet de trouver la case pour trier le cheque
    {
        return num_donnee;
    }



    public void envoie_donnee_arduino(int num_led) // permet d'envoyer les données de l'algorithme à la partie Arduino
    {
        SerialPort sp = SerialPort.getCommPort("/dev/ttyACM1");

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
        
        for (Integer i = 0; i < 5; ++i) 
        {            
            sp.getOutputStream().write(i.byteValue()); // écrit les données en BYTE du serveur vers le port
            sp.getOutputStream().flush(); // permet de forcet l'écriture des donnés du buffer
            Thread.sleep(1000);
        }        
          
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

    public void run()
    {
        envoie_donnee_arduino(algorithme_trie(num_cheque));
    }

}