package cheque;

public class Cheque 
{
    private String trigramme; // permet d'identifié une copropriété par 3 lettre MAJ
    private String numCompte; // numéro de compte bancaire de la corpopriété
    private int num_Case; // TEMPORAIRE




    public Cheque(int num) // constructeur de la classe Cheque.
    {
        num_Case = num;
    }


    public int get_Num_case()
    {
        return num_Case;
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
}