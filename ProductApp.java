/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.productapp;

/**
 *
 * @author Student
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ProductApp extends JFrame implements ActionListener {

    // GUI Components
    private JTextField txtName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    private JMenuBar menuBar;
    private JMenu menuFile, menuTools;
    private JMenuItem miExit, miProcess, miClear;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ===== Menu Bar =====
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuTools = new JMenu("Tools");

        miExit = new JMenuItem("Exit");
        miProcess = new JMenuItem("Process");
        miClear = new JMenuItem("Clear");

        menuFile.add(miExit);
        menuTools.add(miProcess);
        menuTools.add(miClear);

        menuBar.add(menuFile);
        menuBar.add(menuTools);
        setJMenuBar(menuBar);

        // ===== Input Panel =====
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Customer Name:"));
        txtName = new JTextField();
        panel.add(txtName);

        panel.add(new JLabel("Select Product:"));
        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        cmbProduct = new JComboBox<>(products);
        panel.add(cmbProduct);

        panel.add(new JLabel("Enter Price:"));
        txtPrice = new JTextField();
        panel.add(txtPrice);

        panel.add(new JLabel("")); // empty cell
        btnProcess = new JButton("PROCESS");
        panel.add(btnProcess);

        add(panel, BorderLayout.CENTER);

        // ===== Event Listeners =====
        btnProcess.addActionListener(this);
        miProcess.addActionListener(this);
        miClear.addActionListener(this);
        miExit.addActionListener(this);
    }
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnProcess || e.getSource() == miProcess) {
        processSale();
    } else if (e.getSource() == miClear) {
        clearForm();
    } else if (e.getSource() == miExit) {
        System.exit(0);
    }
}
    private void processSale() {
        try {
            String customer = txtName.getText().trim();
            String product = (String) cmbProduct.getSelectedItem();
            String priceText = txtPrice.getText().trim();

            if (customer.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter customer name and price.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double price = Double.parseDouble(priceText);

            if (price <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Price must be greater than zero.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            String message = "Customer Name: " + customer + "\n"
                    + "Product: " + product + "\n"
                    + "VAT: R " + vat + "\n"
                    + "COMMISSION: R " + commission + "\n"
                    + "TOTAL: R " + total;

            JOptionPane.showMessageDialog(this, message, "Message",
                    JOptionPane.INFORMATION_MESSAGE);

            saveToFile(customer, product, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid number for the price.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String customer, String product,
                            double vat, double commission, double total) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("data.txt", true))) {
            pw.println("DATA LOG");
            pw.println("*********************************");
            pw.println("Customer Name: " + customer);
            pw.println("Product: " + product);
            pw.println("VAT: R " + vat);
            pw.println("COMMISSION: R " + commission);
            pw.println("TOTAL: R " + total);
            pw.println("*********************************");
            pw.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error writing to data.txt: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtName.setText("");
        cmbProduct.setSelectedIndex(0);
        txtPrice.setText("");
        txtName.requestFocus();
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductApp().setVisible(true);
        });
    }
}