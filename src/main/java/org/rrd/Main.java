package org.rrd;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String barcode1 = "74002314";
        String barcode2 = "74005364";
        System.out.println("output1 : " + DiscountedPrice.getDiscountedPrice(barcode1));
        System.out.println("output2 : " + DiscountedPrice.getDiscountedPrice(barcode2));
    }
}