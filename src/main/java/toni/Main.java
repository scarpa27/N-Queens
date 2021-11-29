package toni;

import java.util.ArrayList;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        //prvo je potrebno pozvati jednu od funkcija pitaj() nepitaj(), razlika je što pitaj u konzoli pita specifikacije
        //drugo se poziva ili statistika() ili jedna()
        //za poziv backtrack(int dimenzija) nije potrebno prije zvati funkcije iz prvog koraka

        nepitaj(10000,400,16,0.9,0.21);
        statistika(30);
    }

    public static void statistika(int BROJ_SIMULACIJA) {
        ArrayList<Simulacija> statistika= new ArrayList<>();
        ArrayList<Integer> brGen = new ArrayList<>();

        for (int i=0; i<BROJ_SIMULACIJA; i++) {
            statistika.add(new Simulacija());
        }

        statistika.removeIf(sim -> sim.gotovo==0);

        double uspjeh=((double)statistika.size()/(double)BROJ_SIMULACIJA);

        for (Simulacija sim: statistika) {
            brGen.add(sim.getGeneracijeBroj());
        }

        double std = F.stdev(brGen);
        double vrime = statistika.stream().mapToLong(s -> s.vrijeme_izvodenja).average().getAsDouble();
        double avG = statistika.stream().mapToDouble(sim -> sim.gotovo).average().getAsDouble();

       // System.out.println("avgGen= "+avG+"  t= "+vrime+"  std= "+std+"  uspjeh= "+uspjeh);
        System.out.printf("avgGen= %5.1f  t= %6.1f  std= %7.2f  uspjeh= %3.2f\n",avG,vrime,std,uspjeh);
    }

    public static void pitaj () {
        Scanner in = new Scanner(System.in);

        System.out.println("Unesi željenu dimenziju ploče"); int x = in.nextInt();
        System.out.println("Unesi željeni broj ploča po generaciji"); int y = in.nextInt();
        System.out.println("Unesi gornji limit broja generacija ako ne dođe do rješenja"); int z = in.nextInt();
        System.out.println("Unesi stopu mutacije (oko 0.5)"); double m = in.nextDouble();
        System.out.println("Unesi stopu preživljelih (oko 0.35)"); double p = in.nextDouble();

        Simulacija.inicijalizirajSimulaciju(z,y);
        Generacija.inicijalizirajGeneraciju(x,y,m,p);
        Ploca.inicijalizirajPlocu(x);
    }

    public static void nepitaj (int maxGen, int zadPop, int dimenzija, double stopaMut, double stopaPrez) {
        System.out.print("maxGen= "+maxGen+"  zadPop= "+zadPop+"  dim= "+dimenzija+"  mut= "+stopaMut+"  prez= "+stopaPrez+"   ");
        Simulacija.inicijalizirajSimulaciju(maxGen,zadPop);
        Generacija.inicijalizirajGeneraciju(dimenzija,zadPop,stopaMut,stopaPrez);
        Ploca.inicijalizirajPlocu(dimenzija);
    }

    public static void jedna() {
        Ploca.inicijalizirajPlocu(8);
        Ploca ploca = new Ploca();
        ploca.ispisi();
        ploca.generiraj();
        ploca.ispisi();
    }

    public static void backtrack(int N) {
        Backtrack bktrk = new Backtrack(N);
        bktrk.main();
    }
}
