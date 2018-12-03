
package tugaspd2;
import java.util.Scanner;

public class Main {
    
    static Scanner in = new Scanner(System.in);
    static Unit u = new Unit();
    static Customer c = new Customer();
    static Sewa s = new Sewa();
    static Queue q = new Queue();
    
    static void menu() {
        do {
            System.out.println("|============================|");
            System.out.println("|    APLIKASI SEWA MOBIL     |");
            System.out.println("|----------------------------|");
            System.out.println("|   1. Merk                  |");
            System.out.println("|   2. Unit                  |");
            System.out.println("|   3. Customer              |");
            System.out.println("|   4. Sewa                  |");
            System.out.println("|   0. Keluar                |");
            System.out.println("|============================|");
            System.out.print("=> ");
            int x = in.nextInt();
            System.out.println();
            switch(x) {
                case 1:
                    Merk.mainMerk();
                    break;
                case 2:
                    Unit.mainUnit();
                    break;
                case 3:
                    Customer.mainCustomer();
                case 4:
                    Sewa.mainSewa();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Maaf inputan salah...!");
                    break;
            }
        } while(true);
    }
    public static void main(String[] args) {
        menu();
    }
}