/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.salesreport;

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class SalesReport {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] productNames = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        int[][] sales = new int[3][3];

        for (int p = 0; p < productNames.length; p++) {
            for (int m = 0; m < 3; m++) {
                System.out.print("Enter number of sales for month " + (m + 1)
                        + " for " + productNames[p] + " mattress: ");
                sales[p][m] = input.nextInt();
            }
            System.out.println();
        }

        Product product = new Product(productNames, sales);
        PrintProduct printer = new PrintProduct(product);

        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("*****************************************");
        printer.PrintTotalProductSales();
        printer.PrintAverageProductSales();
        printer.PrintMostPopularProduct();

        input.close();
    }
    
}
