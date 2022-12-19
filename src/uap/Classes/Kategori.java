/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uap.Classes;

/**
 *
 * @author Isal
 */
public class Kategori {
    private String nama_kategori;
    private int id;

    
    //untuk change nama makanan
    public Kategori(String nama_kategori, int id) {
        this.nama_kategori = nama_kategori;
        this.id = id;
    }

    //untuk input makanan baru
    public Kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void getProduk(){
        
    }
}
