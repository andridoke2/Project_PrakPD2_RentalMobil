
package tugaspd2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sewa {
    static Scanner in = new Scanner(System.in);
    static Main m = new Main();
    static Queue q = new Queue();
    static double hrg_sewa = 250000;
    
    static void mainSewa() {
        do {
            System.out.println("|============================|");
            System.out.println("|         MENU SEWA          |");
            System.out.println("|----------------------------|");
            System.out.println("|   1. Sewa Unit             |");
            System.out.println("|   2. Lihat Data Sewa       |");
            System.out.println("|   3. Pra Sewa              |");
            System.out.println("|   0. Kembali               |");
            System.out.println("|============================|");
            System.out.print("=> ");
            int x = in.nextInt();
            System.out.println();
            switch (x) {
                case 1:
                    System.out.println("SEWA UNIT");
                    cetak();
                    sewaUnit();
                    break;
                case 2:
                    System.out.println("DATA SEWA");
                    cetak();
                    break;
                case 3:
                    System.out.println("PRA SEWA");
                    cetak();
                    praSewa();
                    break;
                case 0:
                    Main.menu();
                    break;
                default:
                    System.out.println("Maaf inputan salah");
                    break;
            }
        } while (true);
    }
    
    public static void sewaUnit() {
        int id_uni;
        System.out.print("Nomor Sewa            : ");
        String no = in.next();
        System.out.print("ID Customer           : ");
        String id_cus = in.next();
        do {
            System.out.print("ID Unit               : ");
            id_uni = in.nextInt();
        } while(q.cek(id_uni) !=false);
        System.out.print("Lama Sewa (Hari)      : ");
        int hari = in.nextInt();
        double total = hrg_sewa * hari;
        System.out.print("Jaminan               : ");
        String jaminan = in.next();
        
        String sql = "INSERT INTO penyewaan (NO_SEWA,ID_CUSTOMER,JML_HARI,JAMINAN) "
                + "VALUES ('" + no + "', '" + id_cus + "', '" + hari + "', '"+ jaminan +"')";
        int status = Koneksi.execute(sql);
        sql = "INSERT INTO detail_penyewaan (ID_UNIT,NO_SEWA,HARGA_SEWA) "
                + "VALUES (" + id_uni + ",'" + no + "'," + total + ")";
        status = Koneksi.execute(sql);
        if (status ==  1) {
            q.push(id_uni);
            cetak(no);
        } else {
            System.out.println("Datal gagal ditambahkan");
            }
        System.out.println();
        }
    
    public static void cetak() {
        String sql = "select p.NO_SEWA, c.NAMA_DPN, c.NAMA_BLKG, m.NAMA_JENIS, u.NAMA_UNIT, "
                + "p.JML_HARI, p.JAMINAN, d.HARGA_SEWA from penyewaan p "
                + "inner join customer c on p.ID_CUSTOMER=c.ID_CUSTOMER "
                + "right join detail_penyewaan d on p.NO_SEWA=d.NO_SEWA "
                + "inner join unit u on d.ID_UNIT=u.ID_UNIT "
                + "inner join merk_unit m on u.ID_JENIS=m.ID_JENIS "
                + "order by p.NO_SEWA";
        ResultSet rs = Koneksi.executeQuery(sql);
        try {
            while (rs.next()) {
                System.out.println("Nomor Sewa          : " + rs.getString(1));
                System.out.println("Penyewa             : " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Unit                : " + rs.getString(4) + " " + rs.getString(5));
                System.out.println("Lama Sewa           : " + rs.getString(6));
                System.out.println("Jaminan             : " + rs.getString(7));
                System.out.println("Harga Sewa/Hari     : Rp. " + hrg_sewa);
                System.out.println("Total Harga         : Rp. " + rs.getString(8));
                System.out.println();
            }
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println();
    }
    
    public static void cetak(String x) {
        String sql = "select p.NO_SEWA, c.NAMA_DPN, c.NAMA_BLKG, m.NAMA_JENIS, u.NAMA_UNIT,"
                + "p.JML_HARI, p.JAMINAN, d.HARGA_SEWA from penyewaan p "
                + "inner join customer c on p.ID_CUSTOMER=c.ID_CUSTOMER "
                + "right join detail_penyewaan d on p.NO_SEWA=d.NO_SEWA "
                + "inner join unit u on d.ID_UNIT=u.ID_UNIT "
                + "inner join merk_unit m on u.ID_JENIS=m.ID_JENIS "
                + "where p.NO_SEWA='" + x + "'";
        ResultSet rs = Koneksi.executeQuery(sql);
        try {
            while (rs.next()) {
                System.out.println("PRINT");
                System.out.println("Nomor Sewa          : " + rs.getString(1));
                System.out.println("Penyewa             : " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Unit                : " + rs.getString(4) + " " + rs.getString(5));
                System.out.println("Lama Sewa           : " + rs.getString(6));
                System.out.println("Jaminan             : " + rs.getString(7));
                System.out.println("Harga Sewa/Hari     : Rp. " + hrg_sewa);
                System.out.println("Total Harga         : Rp. " + rs.getString(8));
            }
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println();
    }
    
    public static void praSewa() {
        System.out.print("ID Unit     : ");
        int id = in.nextInt();
        q.pop(id);
    }
}