package tugaspd2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
    
    static Scanner in = new Scanner (System.in);
    static Main m = new Main();
    
    static void mainCustomer() {
        do {
            System.out.println("|===========================|");
            System.out.println("|        Menu Customer      |");
            System.out.println("|---------------------------|");
            System.out.println("|   1. Tambah Customer      |");
            System.out.println("|   2. Lihat Data Customer  |");
            System.out.println("|   3. Ubah Data Customer   |");
            System.out.println("|   4. Hapus Data Customer  |");
            System.out.println("|   0. Kembali              |");
            System.out.println("|===========================|");
            System.out.print("=> ");
            int x = in.nextInt();
            System.out.println();
            switch(x) {
                case 1:
                    System.out.println("TAMBAH CUSTOMER");
                    getCustomer();
                    setCustomer();
                    getCustomer();
                    break;
                case 2:
                    System.out.println("DATA CUSTOMER");
                    getCustomer();
                    break;
                case 3:
                    System.out.println("UBAH DATA CUSTOMER");
                    getCustomer();
                    editCustomer();
                    getCustomer();
                    break;
                case 4:
                    System.out.println("HAPUS DATA CUSTOMER");
                    getCustomer();
                    hapusCustomer();
                    getCustomer();
                    break;
                case 0:
                    Main.menu();
                    break;
                default:
                    System.out.println("Maaf inputan salah!");
                    break;
            }
        } while (true);
    }
    
    public static void setCustomer() {
        System.out.print("ID Customer         : ");
        int id = in.nextInt();
        System.out.print("Nama Depan          : ");
        String namad = in.next();
        System.out.print("Nama Belakang       : ");
        String namab = in.next();
        System.out.print("Jenis Kelamin (L/P) : ");
        char jk = in.next().charAt(0);
        System.out.print("Alamat              : ");
        String alamat = in.next();
        System.out.print("Telepon             : ");
        String telp = in.next();
        String sql = "INSERT INTO customer (ID_CUSTOMER,NAMA_DPN,NAMA_BLKG,JK,ALAMAT,TELP_CUSTOMER)"
                + "VALUES ('" + id + "', '" + namad + "', '" + namab + "', '" + jk + "',"
                + "'" + alamat + "', '" + telp + "')";
        int status = Koneksi.execute(sql);
        if (status == 1) {
            System.out.println("\nData berhasil ditambahkan... (CUSTOMER)");
        } else {
            System.out.println("\nData gagal ditambahkan... (CUSTOMER)");
        }
        System.out.println();
    }
    
    public static void getCustomer() {
        String sql = "SELECT * FROM customer";
        ResultSet rs = Koneksi.executeQuery(sql);
        try {
            System.out.println("ID\tNama D\tNama B\tJK\tAlamat\tTelepon");
            while (rs.next()) {
                String ID = rs.getString(1);
                String Namad = rs.getString(2);
                String Namab = rs.getString(3);
                String JK = "";
                if ("L".equals(rs.getString(4))) {
                    JK = "Male";
                } else {
                    JK = "Female";
                }
                String Alamat = rs.getString(5);
                String Telepon = rs.getString(6);
                System.out.println(ID+"\t"+Namad+"\t"+Namab+"\t"+JK+"\t"+Alamat+"\t"+Telepon);
            }
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println();
    }
    
    public static void editCustomer() {
        System.out.print("ID Customer         : ");
        int id = in.nextInt();
        System.out.print("Nama Depan          : ");
        String namad = in.next();
        System.out.print("Nama Belakang       : ");
        String namab = in.next();
        System.out.print("Jenis Kelamin (L/P) : ");
        char jk = in.next().charAt(0);
        System.out.print("Alamat              : ");
        String alamat = in.next();
        System.out.print("Telepon             : ");
        String telp = in.next();
        String sql = "UPDATE customer SET "
                +"ID_CUSTOMER='"+id+"',"
                +"NAMA_DPN='"+namad+"',"
                +"NAMA_BLKG='"+namab+"',"
                +"JK='"+jk+"',"
                +"ALAMAT='"+alamat+"',"
                +"TELP_CUSTOMER='"+telp+"' "
                +"WHERE ID_CUSTOMER='"+id+"'";
        int status = Koneksi.execute(sql);
        if (status == 1) {
            System.out.println("\nData berhasil diubah... (CUSTOMER)");
        } else {
            System.out.println("\nData gagal diubah... (CUSTOMER)");
        }
        System.out.println();
    }
    
    public static void hapusCustomer() {
        System.out.print("ID Customer     : ");
        int id = in.nextInt();
        String sql = "DELETE FROM customer WHERE ID_CUSTOMER='"+id+"'";
        int status = Koneksi.execute(sql);
        if(status == 1) {
            System.out.println("\nData berhasil dihapus... (CUSTOMER)");
        } else {
            System.out.println("\nData gagal dihapus... (CUSTOMER)");
            
        }
        System.out.println();
    }
}