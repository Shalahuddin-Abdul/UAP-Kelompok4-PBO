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
import uap.Classes.Kategori;

/**
 *
 * @author Isal
 */
public class KategoriModel {
    private final Connection CONN;
    public boolean status;

    public KategoriModel() {
        this.CONN = DBHelper.getConnection();
    }
    //addKategori
    public void addKategoriSQL(Kategori ktg){
        String insert = "INSERT INTO ktg VALUES ('" + ktg.getNama_kategori() + "');";
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
    
    public void dltKategoriSQL(Kategori ktg){
        String delete = "DELETE FROM ktg WHERE ktg.nama_produk='" + ktg.getNama_kategori() + "';";
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
    public void updNamaKtgSQL(Kategori ktg){
        String update = "UPDATE ktg SET ktg.nama_kategori='" + ktg.getNama_kategori()+ "' WHERE ktg.id='" + ktg.getId() + "';";
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
    
    
    public ArrayList<Kategori> getKategori(){
        String query = "SELECT * FROM ktg";
        ArrayList<Kategori> ktg = new ArrayList();
        
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Kategori temp = new Kategori(rs.getString("nama_kategori"));
                ktg.add(temp);
            }
            System.out.println("Berhasil Mengambil Data");
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Berhasil Mengambil Data");
        }
        return ktg;
    }
    
    
    
}

