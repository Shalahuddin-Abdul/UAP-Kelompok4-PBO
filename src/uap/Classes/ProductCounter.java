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
interface ProductCounter {
    double TAX = 0.10;

    public double getTAX();

    public void setTAX(double TAX);
    
    public int hitungJumlahProduk();
    public double hitungHargaProduk();
}
