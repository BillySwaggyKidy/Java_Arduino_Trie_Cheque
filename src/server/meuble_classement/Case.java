package server.meuble_classement;

import java.util.ArrayList;
import cheque.Cheque;

// javac -cp c:\../../../classes -d ../../../classes Case.java

public class Case
{
    private ArrayList<Cheque> tabCheque; // tableau d'objet Cheque que peut contenir une case dans le Meuble
    private String etiquette = ""; // permet de donner un attribut à la case pour la classification

    public Case()
    {
        tabCheque = new ArrayList<>();
    }

    public ArrayList<Cheque> getTabCheque()
    {
        return tabCheque;
    }

    public void displayListCheque()
    {
        System.out.println("La Case " + etiquette + " contient: ");
        for (int i = 0 ; i < tabCheque.size() ; i++)
        {
            System.out.print(" " + tabCheque.get(i).get_trigramme());
        }
        System.out.println("");
    }

    public void putChequeToCase(Cheque cheque) // on met l'objet chèque dans le ArrayList
    {
        tabCheque.add(cheque);
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
        return tabCheque.isEmpty();
    }

}