package server.arduino;
import java.io.*;

// ATTENTION: cette classe utilise la librairie Ardulink qui est supprimé



// javac -d ../../../classes Com_arduino.java

public class Com_arduino 
{
    private String[] cmd = new String[3]; // correspond au chemin pour lancer le programme python
    private int nbrLed; // nombre de pin utilisé pour les LED.
    private int refPin; // pin de départ jusqu'au dernier pin pour les LED.

    public Com_arduino(int _nbrLed, int _refpin) throws Exception
    {
        nbrLed = _nbrLed;
        refPin = _refpin;
        cmd[0] = "python3";
        cmd[1] = "arduino/Python_arduino.py";
        System.out.println("Arduino possédant: " + nbrLed + " et commençant par la Led: " + refPin);
    }

    public void lightLed(int num_led) throws IOException // permet d'allumer un led de 
    {
        if (num_led >= 1 && num_led <= nbrLed)
        {
            System.out.println("on allume la led: " + num_led);
            cmd[2] = Integer.toString(num_led);
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);
        }
        else
        {
            System.out.println("Erreur: numéro de LED invalide");
        }
    }

    public void lightAllLed(String cmd_test) throws IOException
    {
        if (cmd_test.equals("test"))
        {
            cmd[2] = cmd_test;
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);
        }
        else
        {
            System.out.println("Erreur: commande invalide");
        }
    }



}