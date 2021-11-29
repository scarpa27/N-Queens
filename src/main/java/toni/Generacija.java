package toni;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Generacija {
    private static int zadPop;
    private static int dimenzija;
    private static double stopamutacije;
    private static double stopaprezivljelih;
    private ArrayList<Ploca> populacija;


    public Generacija() {
        this.populacija=repopuliraj(new ArrayList<Ploca>());
        izbrojiKolizije();
        this.populacija.sort(Comparator.comparingInt(Ploca::getKolizije));
    }

    public Generacija(Generacija prosla) {
        prosla.ubij();
        this.populacija = repopuliraj(prosla.populacija);
        izbrojiKolizije();
        this.populacija.sort(Comparator.comparingInt(Ploca::getKolizije));
    }

    static public void inicijalizirajGeneraciju(int dimenzija, int populacija, double stopamutacije, double stopaprezivljelih) {
        Generacija.zadPop = populacija;
        Generacija.dimenzija = dimenzija;
        Generacija.stopamutacije = stopamutacije;
        Generacija.stopaprezivljelih = stopaprezivljelih;
    }

    public ArrayList<Ploca> getPopulacija() {
        return populacija;
    }

    public void izbrojiKolizije() {
        populacija.forEach(Ploca::izbrojiKolizije);
    }

    private ArrayList<Ploca> repopuliraj(ArrayList<Ploca> ploca) {
        if (ploca.isEmpty()) ploca = populiraj();
        while (ploca.size() < zadPop) {

            int i1, i2;
            do {
                i1 = F.rnd.nextInt((int)(ploca.size()*0.5));
                i2 = F.rnd.nextInt((int)(ploca.size()*0.6));
                if (i1 != i2) break;
            } while (true);
            ploca.add(parenje(ploca.get(i1), ploca.get(i2)));
        }
        return ploca;
    }

    private ArrayList<Ploca> populiraj() {
        ArrayList<Ploca> x = new ArrayList<>(zadPop);
        for (int i = 0; i < zadPop; i++) {
            x.add(new Ploca());
        }
        x.forEach(Ploca::generiraj);
        return x;
    }

    public void ubij() {
        this.getPopulacija().subList((int) (zadPop*stopaprezivljelih),zadPop).clear();
    }

    private Ploca parenje(Ploca p1, Ploca p2) {

        int rekomb1 = F.rnd.nextInt(dimenzija);
        int rekomb2 = F.rnd.nextInt(dimenzija);
        int[] pozicije1 = Arrays.copyOf(p1.getRed(),dimenzija);
        int[] pozicije2 = Arrays.copyOf(p2.getRed(),dimenzija);

        for (int i=F.intMin(rekomb1,rekomb2); i<=F.intMax(rekomb1,rekomb2); i++) {
            int tmp = pozicije1[i];
            pozicije1[i] = pozicije2[i];
            pozicije2[i] = tmp;
        }

        F.makniDuple(pozicije1);
        F.makniDuple(pozicije2);

        //mutacija se događa u x% slučajeva. nasumično mijenja poziciju dviju kraljica
        if (F.rnd.nextDouble() < stopamutacije) {
            int a = F.rnd.nextInt(dimenzija);
            int b = F.rnd.nextInt(dimenzija);
            int tmp = pozicije1[a];
            pozicije1[a] = pozicije1[b];
            pozicije1[b] = tmp;
            tmp = pozicije2[a];
            pozicije2[a] = pozicije2[b];
            pozicije2[b] = tmp;
        }



        return new Ploca((F.rnd.nextDouble() > 0.5) ? pozicije1 : pozicije2);
    }
}
