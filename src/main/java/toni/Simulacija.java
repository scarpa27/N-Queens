package toni;

import java.util.ArrayList;
import java.util.Arrays;

public class Simulacija {
    private ArrayList<Generacija> generacije;
    private static int maxGen;
    private static int zadPop;

    private ArrayList<ArrayList<Integer>> kolizije;
    private ArrayList<Integer> preostali;
    public int gotovo;
    long vrijeme_izvodenja;

    public Simulacija() {
        kolizije=new ArrayList<ArrayList<Integer>>();
        preostali=new ArrayList<Integer>();
        pokreniSimulaciju();
    }

    public ArrayList<Integer> getPreostali() {
        return preostali;
    }

    static public void inicijalizirajSimulaciju(int maxGen, int zadPop) {
        Simulacija.maxGen=maxGen;
        Simulacija.zadPop=zadPop;
    }

    public int getGeneracijeBroj() {
        return generacije.size();
    }

    public void pokreniSimulaciju() {
        long tpoc=System.currentTimeMillis();

        generacije = new ArrayList<>();
        generacije.add(new Generacija());
        for (int i=1; i<maxGen; i++) {
            generacije.add(new Generacija(generacije.get(i-1)));
            dodajStatistiku(generacije.get(i-1));
            if (generacije.get(i).getPopulacija().get(0).getKolizije()==0) {
                this.vrijeme_izvodenja=System.currentTimeMillis()-tpoc;
                gotovo = i;
                break;
            }
        }
    }

    public void dodajStatistiku (Generacija g) {
        kolizije.add(new ArrayList<>(Arrays.asList(g.getPopulacija().get(0).getKolizije(),g.getPopulacija().get(g.getPopulacija().size()-1).getKolizije())));
        preostali.add(g.getPopulacija().size());
    }
}
