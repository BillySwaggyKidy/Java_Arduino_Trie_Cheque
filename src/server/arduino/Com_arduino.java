package server.arduino;
import java.io.*;

// ATTENTION: cette classe utilise la librairie Ardulink qui est supprimé



// javac -d ../../../classes Com_arduino.java

public class Com_arduino 
{
    private String[] cmd = new String[3]; // correspond au chemin pour lancer le programme python
    private int nbr_Led; // nombre de pin utilisé pour les LED.
    private int ref_pin; // pin de départ jusqu'au dernier pin pour les LED.

    public Com_arduino(int _nbr_Led, int _ref_pin) throws Exception
    {
        nbr_Led = _nbr_Led;
        ref_pin = _ref_pin;
        cmd[0] = "python3";
        cmd[1] = "arduino/Python_arduino.py";
        System.out.println("Arduino possédant: " + nbr_Led + " et commençant par la Led: " + ref_pin);
    }

    public void lightLed(int num_led) throws IOException // permet d'allumer un led de 
    {
        if (num_led >= 1 && num_led <= nbr_Led)
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