/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uap.Classes.Makanan;

/**
 *
 * @author Isal
 */
public class DBHelper {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB = "uappbo";
    private static final String MYCONN = "jdbc:mysql://localhost/"+DB;
    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(MYCONN, USERNAME, PASSWORD);
            System.out.println("Koneksi Berhasil");
        }catch(SQLException ex){
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Koneksi Gagal");
        }
        return conn;
    }
    
    public static ObservableList<Makanan> getMakananMenu(){
        ObservableList<Makanan> list = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                list.add(new Makanan(rs.getString("nama_produk"), Double.parseDouble("harga"), Integer.parseInt("jumlah"), Double.parseDouble("diskon"), Integer.parseInt("daya_tahan")));
            }
        }catch(Exception e){
            
        }
        return list;
        
    }
    
}
