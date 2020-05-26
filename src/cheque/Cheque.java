package cheque;

public class Cheque 
{
    private String Trigramme; // permet d'identifié une copropriété par 3 lettre MAJ
    private String num_compte; // numéro de compte bancaire de la corpopriété

    public Cheque(String _Trigramme)
    {
        Trigramme = _Trigramme;
    }
}