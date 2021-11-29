package toni;

public class Ploca {
    private static int dimenzija;
    private int kolizije;

    private int[] red;

    static public void inicijalizirajPlocu(int dimenzija) {
        Ploca.dimenzija = dimenzija;
    }

    public Ploca() {
        this.red = new int[dimenzija];
    }

    public Ploca(int[] red) {
        this.red=red;
    }

    public int[] getRed() {return this.red;}

    public int getKolizije() {
        return kolizije;
    }

    public void generiraj() {
        int[] stup = new int[dimenzija];
        for (int i=0; i<dimenzija; i++) {
            stup[i]=i;
        }
        F.izmjesaj(stup);
        for (int i=0; i<dimenzija; i++) {
            this.red[i] = stup[i];
        }
    }

    public void ispisi() {
        for (int i = 0; i < dimenzija; i++) {
            for (int j = 0; j < dimenzija; j++) {
                if (red[i]==j) System.out.print(1+" ");
                else System.out.print(0+" ");
            }
            System.out.println();
        }
        for (int i=0; i<dimenzija; i++) System.out.print(red[i]+" ");
        System.out.println();
    }

    public void izbrojiKolizije() {
        int kol = 0;
        for (int i = 0; i < dimenzija; i++)
            for (int j = i + 1; j < dimenzija; j++)
                if (red[i] == red[j] || Math.abs(red[i] - red[j]) == j - i)
                    kol += 1;
        this.kolizije = kol;
    }
}
