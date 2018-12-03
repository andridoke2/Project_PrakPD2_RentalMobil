/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugaspd2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Merk {
    
    static Scanner in = new Scanner(System.in);
    static Main m = new Main();
    static Queue q = new Queue();
    
    static void mainMerk(){
        do{
            System.out.println("|============================|");
            System.out.println("|           Menu Merk        |");
            System.out.println("|----------------------------|");
            System.out.println("|     1. Tambah Merk         |");
            System.out.println("|     2. Lihat Merk          |");
            System.out.println("|     3. Ubah Merk           |");
            System.out.println("|     4. Hapus Merk          |");
            System.out.println("|     0. Kembali             |");
            System.out.println("|============================|");
            System.out.print("=> ");
            int x = in.nextInt();
            switch(x){
                case 1:
                    lihatMerk();
                    tambahMerk();
                    break;
                case 2:
                    lihatMerk();
                    break;
                case 3:
                    lihatMerk();
                    editMerk();
                    break;
                case 4:
                    lihatMerk();
                    hapusMerk();
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
//test    
    public static void tambahMerk(){
        System.out.print("ID Jenis         : ");
        int id = in.nextInt();
        System.out.print("Nama Jenis       : ");
        String nama = in.next();
        System.out.print("Keterangan Jenis : ");
        String ket = in.next();
        
        String sql = "INSERT INTO merk_unit VALUES('"+id+"','"+nama+"','"+ket+"')";
        int status = 0;
        status = Koneksi.execute(sql);
        if(status == 1){
            System.out.println("\nData berhasil ditambah... (MERK)");
        } else {
            System.out.println("\nData gagal ditambah... (MERK)");
        }
    }
    
    public static void lihatMerk(){
        String sql = "SELECT * FROM merk_unit";
        ResultSet rs = Koneksi.executeQuery(sql);
        try{
            System.out.println("ID Jenis\tNama Jenis\tKeterangan");
            while(rs.next()){
                String id = rs.getString(1);
                String nama = rs.getString(2);
                String ket = rs.getString(3);
                System.out.println(id+"\t\t"+nama+"\t\t"+ket);
            }
        } catch(SQLException ex){
            Logger.getLogger(Merk.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
    }
    
    public static void editMerk(){
        System.out.print("ID Jenis         : ");
        int id = in.nextInt();
        System.out.print("Nama Jenis       : ");
        String nama = in.next();
        System.out.print("Keterangan Jenis : ");
        String ket = in.next();
        String sql = "UPDATE merk_unit SET "
                + "ID_JENIS='"+id+"',"
                + "NAMA_JENIS='"+nama+"',"
                + "KET_JENIS='"+ket+"' "
                + "WHERE ID_JENIS='"+id+"'";
        int status = 0;
        status = Koneksi.execute(sql);
        if(status == 1){
            System.out.println("\nData berhasil diubah... (MERK)");
        } else {
            System.out.println("\nData gagal diubah... (MERK)");
        }
    }
    
    public static void hapusMerk(){
        System.out.print("ID Jenis : ");
        int id = in.nextInt();
        String sql = "DELETE FROM merk_unit WHERE ID_JENIS='"+id+"'";
        int status = 0;
        status = Koneksi.execute(sql);
        if(status == 1){
            System.out.println("\nData berhasil dihapus... (MERK)");
        } else {
            System.out.println("\nData gagal dihapus... (MERK)");
        }
    }
}
