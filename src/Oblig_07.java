import java.util.*;

class Trenode
{
    int verdi;
    Trenode venstre, hoyre;
    int sum;
    Trenode forelder;

    public Trenode(int verdi, Trenode venstre, Trenode hoyre)
    {
        this.verdi = verdi;
        this.venstre = venstre; this.hoyre = hoyre;
        sum = 0;
        forelder = null;
    }
    public int ToString(){
        return verdi;
    }
}

public class Oblig_07
{
    // Oppgave 1
    static void settSum(Trenode rot)
    {
        //Dersom roten ikke har noen barn, er summen til roten lik dens verdi.
        if(rot.hoyre == null && rot.venstre == null){
            rot.sum = rot.verdi;
        }
        else {
            if(rot.hoyre != null){
                //kjører så lenge roten har en høyre subtre
                settSum(rot.hoyre);
                //legger til summen til høyre subtre til roten sin sum
                rot.sum += rot.hoyre.sum;
            }
            if(rot.venstre != null){
                //kjører så lenge roten har en venstre subtre
                settSum(rot.venstre);
                //legger til summen til venstre subtre til roten sin sum
                rot.sum += rot.venstre.sum;
            }
            //legger til rot-verdig etter barna er besøkt
            rot.sum += rot.verdi;
            }
    }


    // Oppgave 2
    static void settForelder(Trenode rot)
    {
        /* ser at det står i oppgaven at "Roten i treet skal få forelder satt lik null."
        Og jeg dobbelt sjekket med denne utskriften at den blir "automatisk" det, siden den ikke får
        noe foreldre referanse"
        if (rot.forelder == null){
        System.out.println(rot.ToString() + " Roten");
        }*/
        if(rot.hoyre != null){
            rot.hoyre.forelder = rot;
            settForelder(rot.hoyre);
        }
        if(rot.venstre!= null){
            rot.venstre.forelder = rot;
            settForelder(rot.venstre);
        }

    }

    // Oppgave 3
    static void skrivUt(Trenode rot)
    {
        Queue<Trenode> queue = new LinkedList<>();
        queue.add(rot);
        System.out.print("Verdi           Sum         Forelder\n");
        System.out.print("-------------------------------------\n");
        while (!queue.isEmpty()) {
            int nodesAtCurrentLevel = queue.size();
            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                Trenode current = queue.poll();
                String verdi = String.format("%-8s", current.verdi);
                String sum = String.format("%-8s", current.sum);
                if (current.forelder == null){
                    System.out.print("  " + verdi + "      " + sum + "        " + "*\n");
                } else {
                    String forelder = String.format("%-8s", current.forelder.ToString());
                    System.out.print("  " + verdi + "      " + sum+ "        " + forelder + "\n");
                }
                if (current.venstre != null) {
                    queue.add(current.venstre);
                }
                if (current.hoyre != null) {
                    queue.add(current.hoyre);
                }
            }
        }
    }


    // Testprogram
    public static void main(String args[])
    {
        Trenode rot =
                new Trenode(8,
                        new Trenode(4,
                                new Trenode(2, null, null),
                                new Trenode(6, null, null)),
                        new Trenode(16,
                                new Trenode(14,
                                        new Trenode(12, null, null),
                                        new Trenode(15, null, null)),
                                new Trenode(18, null,
                                        new Trenode(20, null, null))));

        settSum(rot);
        settForelder(rot);
        skrivUt(rot);
    }
}