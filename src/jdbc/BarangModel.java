/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import db.DBHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uap.Classes.Barang;
import uap.Classes.Makanan;

/**
 *
 * @author Isal
 */
public class BarangModel {
    private final Connection CONN;
    public boolean status;

    public BarangModel() {
        this.CONN = DBHelper.getConnection();
    }
    //addMakanan
    public void addBarangSQL(Barang brg){
        String insert = "INSERT INTO brg VALUES ('" + brg.getNama_produk() + "', " + brg.getHarga() + ", " + brg.getJumlah() + ", " + brg.getDiskon() + ", null, '" + brg.getBarcode() + "', '" + brg.getExpired() + "');";
//        System.out.println(insert);
        try {
            if(CONN.createStatement().executeUpdate(insert)>0){
                System.out.println("Berhasil Memasukkan Data");
                status = true;
            }else{
                System.out.println("Gagal Memasukkan Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Memasukkan Data");
            status = false;
        }
    }
    
    public void dltMakananSQL(Barang brg){
        String delete = "DELETE FROM brg WHERE brg.nama_produk='" + brg.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(delete)>0){
                System.out.println("Berhasil Menghapus Data");
                status = true;
            }else{
                System.out.println("Gagal Menghapus Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Menghapus Data");
            status = false;
        }
    }
    public void updHargaMakanan(Barang brg){
        String update = "UPDATE brg SET brg.harga='" + brg.getHarga()+ "' WHERE brg.nama_produk='" + brg.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(update)>0){
                System.out.println("Berhasil Update Data");
            }else{
                System.out.println("Gagal Update Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Update Data");
        }
    }
    
    
    public ArrayList<Makanan> getMakanan(){
        String query = "SELECT * FROM brg";
        ArrayList<Makanan> brg = new ArrayList();
        
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Makanan temp = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getInt("daya_tahan"));
                brg.add(temp);
            }
            System.out.println("Berhasil Mengambil Data");
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Berhasil Mengambil Data");
        }
        return brg;
    }
}

