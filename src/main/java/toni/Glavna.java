package toni;

public class Glavna {
    public static void main(String[] args) {
        int[] tabla= {0,1,9,9,5,6,8,8,9};
        System.out.println(F.izbrojiDuple(tabla)+" br dupl");

        F.makniDuple(tabla);
        for (int i : tabla) System.out.print(" "+i);
//        Arrays.stream(tabla).forEach(System.out::print);
//        F.izmjesaj(tabla);
//        System.out.println();
//        Arrays.stream(tabla).forEach(System.out::print);
//
//        ArrayList<Integer> statistika = new ArrayList<>();
//        for (long c= 0; c<100000000; c++) {
//            int[] mutacije = new int[8];
//            int brojac=0;
//            while (F.rnd.nextDouble() < 0.35) {
//                mutacije[F.rnd.nextInt(mutacije.length)] = 1;
//            }
//            for (int b : mutacije) {
//                if (b==1) brojac++;
//            }
//            statistika.add(brojac);
//        }
//        System.out.println("\nProsjek "+statistika.stream().mapToDouble(a->a).average().getAsDouble());

    }

}
