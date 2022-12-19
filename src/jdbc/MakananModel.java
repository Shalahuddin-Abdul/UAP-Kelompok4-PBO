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
import javax.swing.JOptionPane;
import uap.Classes.Makanan;

/**
 *
 * @author Isal
 */
public class MakananModel {
    private final Connection CONN;
    public boolean status;

    public MakananModel() {
        this.CONN = DBHelper.getConnection();
    }
    //addMakanan
    public void addMakananSQL(Makanan mkn){
        String insert = "INSERT INTO mkn VALUES ('" + mkn.getNama_produk() + "', " + mkn.getHarga() + ", " + mkn.getJumlah() + ", " + mkn.getDiskon() + ", null, " + mkn.getDaya_tahan() + ");";
//        System.out.println(insert);
        try {
            if(CONN.createStatement().executeUpdate(insert)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Memasukkan Data");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Memasukkan Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Memasukkan Data");
            status = false;
        }
    }
    
    public void dltMakananSQL(Makanan mkn){
        String delete = "DELETE FROM mkn WHERE mkn.nama_produk='" + mkn.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(delete)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Menghapus Data");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data");
            status = true;
        }
    }
    public void updMakananSQL(Makanan mkn){
        String update = "UPDATE `mkn` SET `harga`=" + mkn.getHarga() + 
                ", `jumlah`=" + mkn.getJumlah() + 
                ", `diskon`=" + mkn.getDiskon() + 
                ", `daya_tahan`=" + mkn.getDaya_tahan() +
                " WHERE `nama_produk`='" + mkn.getNama_produk() + "';";
        try {
            if(CONN.createStatement().executeUpdate(update)>0){
                JOptionPane.showMessageDialog(null, "Berhasil Update Data");
                status = true;
            }else{
                JOptionPane.showMessageDialog(null, "Gagal Update Data");
                status = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Gagal Update Data");
            status = false;
        }
    }
    
    
    public ArrayList<Makanan> getMakanan(){
        String query = "SELECT * FROM mkn";
        ArrayList<Makanan> mkn = new ArrayList();
        
        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);
            while(rs.next()){
                Makanan temp = new Makanan(rs.getString("nama_produk"), rs.getDouble("harga"), rs.getInt("jumlah"), rs.getDouble("diskon"), rs.getInt("daya_tahan"));
                mkn.add(temp);
            }
            System.out.println("Berhasil Mengambil Data");
        } catch (SQLException ex) {
            Logger.getLogger(MakananModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Berhasil Mengambil Data");
        }
        return mkn;
    }
}
