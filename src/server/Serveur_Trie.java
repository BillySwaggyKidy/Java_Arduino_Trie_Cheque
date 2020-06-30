package server;

import cheque.Cheque;

import java.util.*;
import java.io.*;
import java.net.*; // permet de pouvoir faire du réseau avec java
import server.arduino.Com_arduino;
import server.meuble_classement.Meuble;

import com.fazecast.jSerialComm.*;

//javac -cp c:\../../classes:c:\../../classes/com/fazecast/jSerialComm-2.6.2.jar -d ../../classes Serveur_trie.java
//java -cp .:c:\../../classes:c:\../../classes/com/fazecast/jSerialComm-2.6.2.jar server.Serveur_trie

// ATTENTION: activer XAMPP avant de lancer le serveur pour que les clients se connectent

public class Serveur_trie extends Thread
{
    private String name; // nom du client connecté au thread serveur
    private Socket clientSocket;
    private ObjectOutputStream out;
    private static HashMap<String, Serveur_trie> tableauNom = new HashMap<String, Serveur_trie>(); // stocke les noms d'utilisateurs dans un tableau


    /*private void display_port()
    {
        SerialPort[] ports = SerialPort.getCommPorts();
        for (int i = 0; i < ports.length; i++) 
        {
        System.out.println(ports[i].getSystemPortName());
        }
    }*/

    private void envoie_donnee_arduino(Cheque _cheque, Meuble _meuble) throws Exception 
    // permet d'envoyer les données de l'algorithme à la partie Arduino13
    {
        int case_a_ranger = _meuble.putChequeToMeuble(_cheque);
        System.out.println("Il faut ranger le chèque dans la case: " + case_a_ranger);
        Com_arduino Arduino = new Com_arduino(25,2);
        Arduino.lightLed(case_a_ranger);

    }

    public void run() // fonction du thread
    {

        try (
			ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        )
        {
            Meuble meuble_test = new Meuble(25);
            while(true)
            {
                Object o = in.readObject();
                if (o instanceof Cheque)
                {
                    Cheque cheque = (Cheque) o;
                    System.out.println("info: " + cheque.get_trigramme());
                    envoie_donnee_arduino(cheque, meuble_test);
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

    public static void main(String[] args) throws IOException, Exception // a executer dans un autre ordinateur
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue au serveur, Veillez saisir...");
        System.out.println("Votre numéro de port >"); // ATTENTION, si vous avez pas le root privilege alors prenez un numéro de port > 1024
        int num_port = scanner.nextInt(); // on enregistre le numéro du port

        try (
        ServerSocket serverSocket = new ServerSocket(num_port); // on crée l'application serveur sur un port
        )
        {
            scanner.nextLine();
            Com_arduino test = new Com_arduino(25,2);
            Meuble Meuble_cheque = new Meuble(25);

            System.out.println("Voulez vous tester les LED? taper la commande: test");
            String cmd = scanner.nextLine();
            test.lightAllLed(cmd);
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

/*display_port();
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem1411"); // port de la carte aruino Mega 2650
        System.out.println(sp.getDescriptivePortName());
        sp.setComPortParameters(9600, 8, 1, 0); // default connection settings for Arduino
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0); // block until bytes can be writte
        PrintWriter diff_arduino = new PrintWriter(sp.getOutputStream());


        if (sp.openPort()) //ouvre le port en question en mode lecture et écriture
        {
            System.out.println("Port is open");
        }
        else 
        {
            System.out.println("Failed to open port");
            return;
        }

        sp.addDataListener(new SerialPortDataListener()  // on ajoute des listeners pour lire ce que renvoie la carte arduino
        {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] newData = new byte[sp.bytesAvailable()];
                sp.readBytes(newData, 7);
                String msg = new String(newData,sp.bytesAvailable());
                System.out.println("DATA FROM ARDUINO: " + msg);
            }
        });
        
        Thread.sleep(1000);
        byte[] writeBuffer = new byte[8];
        writeBuffer[0] = num_led.byteValue();

        System.out.println("byte value = " + writeBuffer[0]);

        diff_arduino.write(num_led.byteValue());
        diff_arduino.flush();
        //sp.writeBytes(writeBuffer,8); // écrit les données en BYTE du serveur vers le port
        //sp.getOutputStream().flush(); // permet de forcet l'écriture des donnés du bufferx
          
        if (sp.closePort()) //ferme le port en question en mode lecture et écriture
        {
            System.out.println("Port is closed :)");
        }
        else 
        {
            System.out.println("Failed to close port :(");
            return;
          
        }*/