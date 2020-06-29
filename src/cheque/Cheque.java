package cheque;

import java.io.Serializable;

public class Cheque implements Serializable, java.lang.Comparable<Cheque>
{
    private static final long serialVersionUID = 1574334521640694846L;
    private String trigramme; // permet d'identifié une copropriété par 3 lettre MAJ
    private String numCompte; // numéro de compte bancaire de la corpopriété

    // javac -d ../../classes Cheque.java


    public Cheque(String _trigramme) // constructeur de la classe Cheque.
    {
        trigramme = _trigramme;
    }

    public String get_trigramme()
    {
        return trigramme;
    }

    public void set_trigramme(String _trigramme)
    {
        trigramme = _trigramme;
    }

    public String get_num_Compte()
    {
        return numCompte;
    }

    public void set_num_Compte(String _numCompte)
    {
        numCompte = _numCompte;
    }

    public int compareTo(Cheque _cheque) // méthode qui permet de trier les élèments de la liste
    {
        if (Character.isDigit(_cheque.trigramme.charAt(0))) // si le trigramme commence par un chiffre
        {
            String s1 = this.trigramme.replaceAll("\\D", ""); // on enlève toutes les lettres de la chaîne pour garder les chiffres
            String s2 = _cheque.trigramme.replaceAll("\\D", "");
            return Integer.parseInt(s1) - Integer.parseInt(s2);
        }
        else // sinon c'est une chaine de caractère
        {
            return this.trigramme.compareTo(_cheque.trigramme);
        }
    }
}