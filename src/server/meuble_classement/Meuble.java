package server.meuble_classement;

// javac -cp c:\../../../classes -d ../../../classes Meuble.java

public class Meuble 
{
    private Case [] tab_Case;

    public Meuble(int nbr_case)
    {
        tab_Case = new Case[nbr_case]; 
        
    }

    public Case[] get_tab_Case()
    {
        return tab_Case;
    }
}