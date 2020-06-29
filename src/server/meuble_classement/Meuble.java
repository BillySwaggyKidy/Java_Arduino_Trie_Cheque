package server.meuble_classement;

import java.util.Collections;
import cheque.Cheque;
// javac -cp c:\../../../classes -d ../../../classes Meuble.java

public class Meuble 
{
    private Case [] tab_Case;
    private boolean mode_Alphabet = false;

    public Meuble(int nbr_case)
    {
        tab_Case = new Case[nbr_case];
        for (int i = 0; i < tab_Case.length ; i++)
        {
            tab_Case[i] = new Case();
        }
        modeAlphabétique();
        displayEtiquette();
    }

    public Case[] get_tab_Case()
    {
        return tab_Case;
    }

    public int putChequeToMeuble(Cheque cheque)
    {
        return algorithme_Alphabétique(cheque);
    }

    public void displayEtiquette() // permet d'afficher toutes les etiquettes des cases du meuble
    {
        for (int i = 0; i < tab_Case.length ; i++)
        {   
            int ii = i + 1;
            System.out.println("Case numéro: " + ii + " etiquette: " + tab_Case[i].getEtiquette());
        }
    }

    public boolean meubleEmpty() // vérifie si le meuble ne possède plus de chèque.
    {
        for (int i = 0; i < tab_Case.length ; i++)
        {
            if (tab_Case[i].caseEmpty() == false) // si la case a encore des chèques.
            {
                return false;
            }
        }

        return true;
    }

    private void modeAlphabétique() 
    // donne à chaque case du meuble une lettre majuscule de l'alphabet sauf la première case qui est réservé pour les chiffres et la dernière case qui en contient trois.
    {
        if (meubleEmpty()) // vérifie en premier avant de changer le classement du meuble si il est vide
        {
            char[] alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray(); // on crée le tableau de caractère de composée des lettres de l'alphabet
            for (int i = 0; i < alphabet.length ; i++)
            {
                if (i < alphabet.length - 3)
                {
                    tab_Case[i].setEtiquette(String.valueOf(alphabet[i])); // on met la lettre dans la variable étiquette de la case
                }
                else
                {
                    tab_Case[i].setEtiquette(String.valueOf(alphabet[i]) + String.valueOf(alphabet[i+1]) + String.valueOf(alphabet[i+2]));
                    // on met les trois dernière lettres dans l'étiquette de la dernière case.
                    break;
                }
            }
            mode_Alphabet = true;
        }
        else
        {
            System.out.println("ATTENTION: le meuble n'est pas vide");
        }
    }

    private void modeCaseCopropriété()
    {

    }

    private int algorithme_Alphabétique(Cheque cheque) // algorithme stockant un chèque dans le meuble et retournant le numéro de la case où allumer la LED
    {   
        if (mode_Alphabet == true)
        {
            for (int i = 0; i < tab_Case.length; i++)
            {   
                if (Character.isDigit(cheque.get_trigramme().charAt(0))) // si la première lettre du trigramme est un chiffre
                {
                    tab_Case[0].putChequeToCase(cheque); // on met le cheque dans la case des nombres
                    Collections.sort(tab_Case[i].getTabCheque());
                    tab_Case[0].displayListCheque();
                    return 0 + 1;
                }
                else // sinon dans tous les cas, c'est une lettre alors
                {
                    for (int b = 0; b < tab_Case[i].getEtiquette().length(); b++)
                    {
                        if (cheque.get_trigramme().charAt(0) == tab_Case[i].getEtiquette().charAt(b))
                        {
                            tab_Case[i].putChequeToCase(cheque); // on met le cheque dans la case.
                            Collections.sort(tab_Case[i].getTabCheque());
                            tab_Case[i].displayListCheque();
                            return i + 1;
                        }
                    }
                }

            }
        }
        System.out.println("BYE");
        return 0;
    }

    private void algorithme_CaseCopropriété()
    {

    }
}