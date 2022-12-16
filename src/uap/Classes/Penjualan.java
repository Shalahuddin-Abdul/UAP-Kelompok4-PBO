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
public class Penjualan {
    private ArrayList<Produk> listProduk;
    private int jumlahProduk;
    private int stok;

    public Penjualan(ArrayList<Produk> listProduk, int jumlahProduk, int stok) {
        this.listProduk = new ArrayList<>();
        this.jumlahProduk = jumlahProduk;
        this.stok = stok;
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
    
    public void getProduk(){
        
    }
}
