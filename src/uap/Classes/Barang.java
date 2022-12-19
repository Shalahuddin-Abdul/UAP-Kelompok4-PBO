/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.Classes;

import java.util.ArrayList;

/**
 *
 * @author Isal
 */
public class Barang extends Produk{
    private String barcode;
    private String expired;
//    private ArrayList<Kategori> kategori;
    private String kategori;

    public Barang(String nama_produk, double harga, int jumlah, double diskon, String barcode, String expired, String kategori) {
        super(nama_produk, harga, jumlah, diskon);
        this.barcode = barcode;
        this.expired = expired;
//        this.kategori = new ArrayList<>();
        this.kategori = kategori;
    }
    public Barang(String nama_produk, double harga, int jumlah, double diskon, String barcode, String expired) {
        super(nama_produk, harga, jumlah, diskon);
        this.barcode = barcode;
        this.expired = expired;
//        this.kategori = new ArrayList<>();
    }
    public Barang(String nama_produk, double harga, int jumlah, double diskon){
        super(nama_produk, harga, jumlah, diskon = 0);
        
    }
    public Barang(String nama_produk){
        super(nama_produk);
        
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

//    public ArrayList<Kategori> getKategori() {
//        return kategori;
//    }
//
//    public void setKategori(ArrayList<Kategori> kategori) {
//        this.kategori = new ArrayList<>(kategori);
//    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    
    public void isExpired(){
        
    }
    public void addKategori(){
        
    }
}
