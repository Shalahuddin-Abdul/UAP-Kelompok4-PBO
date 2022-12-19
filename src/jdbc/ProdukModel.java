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
import uap.Classes.Produk;

/**
 *
 * @author Isal
 */
public class ProdukModel {
    private final Connection CONN;

    public ProdukModel() {
        this.CONN = DBHelper.getConnection();
    }
    
    public void addProduct(Produk pdk){
        String insert = "INSERT INTO pdk VALUES ('" + pdk.getNama_produk() + "', " + pdk.getHarga() + ", " + pdk.getJumlah() + ", " + pdk.getDiskon() + ");";
//        System.out.println(insert);
        try {
            if(CONN.createStatement().executeUpdate(insert)>0){
                System.out.println("Berhasil Memasukkan Data");
            }else{
                System.out.println("Gagal Memasukkan Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Memasukkan Data");
        }
    }
    public void dltProduk(Produk pdk){
        String delete = "DELETE FROM pdk WHERE pdk.nama_produk='" + pdk.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(delete)>0){
                System.out.println("Berhasil Menghapus Data");
            }else{
                System.out.println("Gagal Menghapus Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Menghapus Data");
        }
    }
    public void updProduk(Produk pdk){
        String update = "UPDATE pdk SET pdk.harga='" + pdk.getHarga()+ "' WHERE pdk.nama_produk='" + pdk.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(update)>0){
                System.out.println("Berhasil Update Data");
            }else{
                System.out.println("Gagal Update Data");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Gagal Update Data");
        }
    }
    public ArrayList<Produk> getProduk(){
        String query = "SELECT * FROM pdk";
        ArrayList<Produk> pdk = new ArrayList();
        
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Produk temp = new Produk(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"));
                pdk.add(temp);
            }
            System.out.println("Berhasil Mengambil Data");
        } catch (SQLException ex) {
            Logger.getLogger(ProdukModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Berhasil Mengambil Data");
        }
        return pdk;
    }
    
}

