package server.meuble_classement;

import java.util.ArrayList;
import cheque.Cheque;

// javac -cp c:\../../../classes -d ../../../classes Case.java

public class Case
{
    private ArrayList<Cheque> tab_Cheque; // tableau d'objet Cheque que peut contenir une case dans le Meuble
    private String etiquette = ""; // permet de donner un attribut à la case pour la classification

    public Case()
    {
        tab_Cheque = new ArrayList<>();
    }

    public ArrayList<Cheque> getTabCheque()
    {
        return tab_Cheque;
    }

    public void displayListCheque()
    {
        System.out.println("La Case " + etiquette + " contient: ");
        for (int i = 0 ; i < tab_Cheque.size() ; i++)
        {
            System.out.print(" " + tab_Cheque.get(i).get_trigramme());
        }
        System.out.println("");
    }

    public void putChequeToCase(Cheque cheque) // on met l'objet chèque dans le ArrayList
    {
        tab_Cheque.add(cheque);
    }

    public String getEtiquette()
    {
        return etiquette;
    }

    public void setEtiquette(String _etiquette)
    {
        etiquette = _etiquette;
    }

    public boolean caseEmpty() // vérifie si la case n'a pas de chèque
    {
        return tab_Cheque.isEmpty();
    }

}