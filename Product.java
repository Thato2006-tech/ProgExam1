/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salesreport;

/**
 *
 * @author Student
 */

 public class Product implements IProduct {

    private final String[] productNames;   // single-dimensional array
    private final int[][] sales;           // 2D array: [product][month]

    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    // Total of every sale across all products and all months
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }
        return total;
    }

    // Total sold divided by the number of entries (3 products x 3 months = 9)
    @Override
    public double GetAverageProductSold() {
        int count = 0;
        for (int i = 0; i < sales.length; i++) {
            count += sales[i].length;
        }
        if (count == 0) {
            return 0;
        }
        return (double) GetTotalProductsSold() / count;
    }

    // Product with the highest total sales over the three months
    @Override
    public String GetMostPopularProduct() {
        int bestIndex = 0;
        int bestTotal = -1;
        for (int i = 0; i < sales.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
            }
            if (productTotal > bestTotal) {
                bestTotal = productTotal;
                bestIndex = i;
            }
        }
        return productNames[bestIndex];
    }
} 

