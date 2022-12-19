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
public class Penjualan implements ProductCounter{
    private ArrayList<Produk> listProduk;
    private int jumlahProduk;
    private double totalHarga;
//    private int stok;

    public Penjualan(ArrayList<Produk> listProduk, int jumlahProduk) {
        this.listProduk = new ArrayList<>();
        this.jumlahProduk = jumlahProduk;
//        this.stok = stok;
    }

    public Penjualan(ArrayList<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    public Penjualan(ArrayList<Produk> listProduk, int jumlahProduk, double totalHarga) {
        this.listProduk = listProduk;
        this.jumlahProduk = jumlahProduk;
        this.totalHarga = totalHarga;
    }
    
    

    public ArrayList<Produk> getListProduk() {
        return listProduk;
    }

    public void setListProduk(ArrayList<Produk> listProduk) {
        this.listProduk = listProduk;
    }
    
    

    public int getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(int jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

//    public int getStok() {
//        return stok;
//    }
//
//    public void setStok(int stok) {
//        this.stok = stok;
//    }
    
//    public void atcProduk(Produk pdk){
//        listProduk.add(pdk);
//        pdk.setNama_produk(pdk.getNama_produk());
//        
//    }
    
    
    public void getProduk(){
        
    }

    
    @Override
    public double getTAX() {
        return TAX;
    }

    @Override
    public void setTAX(double TAX) {
//        ProductCounter.TAX = TAX;
    }

    @Override
    public int hitungJumlahProduk() {
        return this.listProduk.size();        
    }

    @Override
    public double hitungHargaProduk() {
        double totalHarga = 0;
        for (Produk listProduk1 : this.listProduk) {
            totalHarga += listProduk1.getHarga();
        }
        return totalHarga;
    }
}
