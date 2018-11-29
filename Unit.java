package tugaspd2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unit {
    
    static Scanner in = new Scanner (System.in);
    static Main m = new Main();
    
    static void mainUnit() {
        do {
            System.out.println("|============================|");
            System.out.println("|           MENU UNIT        |");
            System.out.println("|----------------------------|");
            System.out.println("|   1. Tambah Unit           |");
            System.out.println("|   2. Lihat Data Unit       |");
            System.out.println("|   3. Ubah Data Unit        |");
            System.out.println("|   4. Hapus Unit            |");
            System.out.println("|   0. Kembali               |");
            System.out.println("|============================|");
            System.out.print("=> ");
            int x = in.nextInt();
            System.out.println();
            switch(x) {
                case 1:
                    System.out.println("TAMBAH UNIT");
                    getUnit();
                    setUnit();
                    break;
                case 2:
                    System.out.println("DATA UNIT\n");
                    getUnit();
                    break;
                case 3:
                    System.out.println("UBAH DATA UNIT\n");
                    getUnit();
                    editUnit();
                    break;
                case 4:
                    System.out.println("HAPUS DATA UNIT\n");
                    getUnit();
                    hapusUnit();
                    getUnit();
                    break;
                case 0:
                    Main.menu();
                    break;
                default:
                    System.out.println("Maaf inputan salah!");
                    break;
            }
        } while(true);
    }
    
    public static void setUnit() {
        System.out.print("ID UNIT     : ");
        int id = in.nextInt();
        System.out.print("Nama Jenis  : ");
        String jenis = in.next();
        System.out.print("Nama Unit   : ");
        String unit = in.next();
        System.out.print("Tahun       : ");
        String tahun = in.next();
        
        String sql = "INSERT INTO unit (ID_UNIT,ID_JENIS,NAMA_UNIT,TAHUN) "
                + "VALUES ('" + id + "', (SELECT ID_JENIS FROM merk_unit where NAMA_JENIS='" + jenis + "'), '" + unit + "',"
                + "'" + tahun + "')";
        int status = Koneksi.execute(sql);
        if (status == 1) {
            System.out.println("\nData berhasil ditambahkan... (UNIT)");
        } else {
            System.out.println("\nData gagal ditambahkan... (UNIT)");
        }
        System.out.println();
    }
    
    public static void getUnit() {
        String sql = "SELECT * FROM unit";
        ResultSet rs = Koneksi.executeQuery(sql);
        try {
            System.out.println("ID\tJENIS\tUNIT\tTAHUN");
            while (rs.next()) {
                String ID = rs.getString(1);
                String Jenis = rs.getString(2);
                String Unit = rs.getString(3);
                String Tahun = rs.getString(4);
                System.out.println(ID+"\t"+Jenis+"\t"+Unit+"\t"+Tahun);
            }
        } catch (SQLException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println();
    }
    public static void editUnit() {
        System.out.print("ID Unit     : ");
        int id = in.nextInt();
        System.out.print("Nama Jenis  : ");
        String jenis = in.next();
        System.out.print("Nama Unit   : ");
        String unit = in.next();
        System.out.print("Tahun       : ");
        String tahun = in.next();
        
        String sql = "UPDATE unit SET "
                + "ID_UNIT='"+id+"',"
                + "ID_JENIS=(SELECT ID_JENIS FROM merk_unit where NAMA_JENIS='" + jenis + "'), "
                + "NAMA_UNIT='"+unit+"',"
                + "TAHUN='"+tahun+"' "
                + "WHERE ID_UNIT='"+id+"'";
        int status = Koneksi.execute(sql);
        if (status == 1) {
            System.out.println("\nData berhasil diubah... (UNIT)");
        } else {
            System.out.println("\nData gagal diubah... (UNIT)");
        }
        System.out.println();
    }
    
    public static void hapusUnit() {
        System.out.print("ID Unit      : ");
        int id = in.nextInt();
        String sql = "DELETE FROM unit Where ID_UNIT='"+id+"';";
        int status = Koneksi.execute(sql);
        if(status == 1) {
            System.out.println("\nData berhasil dihapus... (UNIT)");
        } else {
            System.out.println("\nData gagal dihapus... (UNIT)");
        }
        System.out.println();
    }
}