package server.meuble_classement;

import cheque.Cheque;

public class Case 
{
    private Cheque [] tab_Cheque;

    public Case()
    {
        tab_Cheque = new Cheque[50]; // on crée un case pouvant contenir 50 chéques.
    }

    public Cheque[] get_tab_Cheque()
    {
        return tab_Cheque;
    }

}