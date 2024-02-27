import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;

public class Main {
    //Zeigt das Ergebnis an
    static JLabel anzeige = new JLabel("Ich gebe das Ergebnis aus");
    static String test = "";
    static ArrayList<String> gedrehteliste = new ArrayList<>();
    //Main Methode
    public static void main(String[] args) {
        openUI();
    }
    public static void openUI(){

        //Aufbau und Eigenschaften des Hauptfensters
        JFrame frame = new JFrame("Mein Umrechner");
        frame.setSize(350,200);
        frame.setLocation(100,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Intialisieren der Felder
        JLabel text_dezimalzahl = new JLabel("Dezimalzahl");
        JTextField dezimalzahl_eingabe = new JTextField();
        JLabel text_basis = new JLabel("Basis");
        JTextField basis_eingabe = new JTextField();
        JButton delete = new JButton("DEL");
        JLabel anzeige = new JLabel("Ich gebe das Ergebnis aus");
        JButton ergebnis = new JButton("OK");

        //Position der Felder
        text_dezimalzahl.setBounds(10,10,150,20);
        dezimalzahl_eingabe.setBounds(100,10,150,20);
        text_basis.setBounds(10,35,150,20);
        basis_eingabe.setBounds(100,35,150,20);
        anzeige.setBounds(87,75,200,20);
        delete.setBounds(268,58,56,60);
        ergebnis.setBounds(10,60,56,60);


        //Farbcodierung der Elemente
        frame.getContentPane().setBackground(new Color(0,206,190));
        text_dezimalzahl.setForeground(Color.black);
        text_dezimalzahl.setBackground(Color.green);
        text_basis.setForeground(Color.black);
        text_basis.setBackground(Color.green);
        anzeige.setForeground(Color.black);
        anzeige.setBackground(Color.green);
        ergebnis.setBackground(new Color(107,205,35));
        ergebnis.setForeground(Color.black);
        delete.setBackground(new Color(107,205,35));
        delete.setForeground(Color.black);

        //Felder antabbar machen
        basis_eingabe.setFocusable(true);
        dezimalzahl_eingabe.setFocusable(true);
        delete.setFocusable(true);
        ergebnis.setFocusable(true);

        //Parameter bei drücken der Buttons
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anzeige.setText("");
                dezimalzahl_eingabe.setText("");
                basis_eingabe.setText("");
            }
        });
        delete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER);{
                    delete.doClick();
                }
            }
        });
        //ergebnis.addKeyListener(new KeyAdapter() {
          //  @Override
           // public void keyTyped(KeyEvent e) {
             //   if(e.getKeyCode() == KeyEvent.VK_ENTER);{
               //     ergebnis.doClick();

        ergebnis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dezimalzahl_umwandlung = dezimalzahl_eingabe.getText();

                    String basis_umwandlung = basis_eingabe.getText();
                    Integer dezimalzahl = Integer.parseInt(dezimalzahl_umwandlung);
                    Integer basis = Integer.parseInt(basis_umwandlung);
                    dezimalzahl_zu_anderer_basis(dezimalzahl, basis);
                    anzeige.setText(test);
                    gedrehteliste.clear();
                    }
                catch (NumberFormatException z){
                    anzeige.setText("Du hast keine Zahl angegeben!");

                }
            }
        });
        //aufrufen der Felder im Interface
        frame.add(delete);
        frame.add(anzeige);
        frame.add(ergebnis);
        frame.add(text_basis);
        frame.add(basis_eingabe);
        frame.add(dezimalzahl_eingabe);
        frame.add(text_dezimalzahl);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    //Funktion zum Umrechnen
    public static ArrayList<String> dezimalzahl_zu_anderer_basis(int dezimalzahl, int basis) {
        ArrayList<String> ascii = new ArrayList<>();
        while (dezimalzahl > 0) { //ürsprüngliche Formel aus Python
            int rechnung = (int) (((dezimalzahl / (float) basis) - (int) (dezimalzahl / basis)) * basis);
            //Umwandeln des Ergebnis in Ascii wenn das Ergebnis höher 10
            if (rechnung > 10) {
                // Ergebnis wird +55 gerechnet und als String in die Liste eingetragen
                // +55 wird verwendet um einen Buchstaben aus der Ascii Liste zu nutzen
                ascii.add(Character.toString((char) (rechnung + 55)));
            } else {
                ascii.add(Integer.toString(rechnung));
            }
            //übertrag des Restes der durch die Schleife muss
            dezimalzahl = (int) (dezimalzahl / basis);
            // zum drehen der Liste
        }
        for (int i = ascii.size() - 1; i >= 0; i--) {
            gedrehteliste.add(ascii.get(i));
        }
        //test = gedrehteliste.toString();
        test = gedrehteliste.toString().replaceAll("[\\[\\],]","");
        return gedrehteliste;
        }
    }