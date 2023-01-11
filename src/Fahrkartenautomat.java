import java.util.Scanner;

class Fahrkartenautomat {
    public static void main(String[] args) {

        double zuZahlenderBetrag;
        double eingezahlterGesamtbetrag;
        Scanner tastatur = new Scanner(System.in);

        begreussung();
        zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
        eingezahlterGesamtbetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
        fahrkartenAusgeben();
        rueckgeldAusgeben(zuZahlenderBetrag, eingezahlterGesamtbetrag);

        tastatur.close();
    }
    public static void begreussung(){
        String gruss = "Herrzlich Willkommen!";
        System.out.println(gruss+"\n");
    }
    public static double fahrkartenbestellungErfassen(Scanner tastatur){
        int ticketWahl = 0;
        int anzahlTickets = 0;
        double ticketPreis = 0.0;
        double zuZahlenderBetrag = 0.0;

        System.out.println("Fahrkartebestellvorgang");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");

        while (ticketWahl != 9){

            System.out.println("Wählen Sie ihre Wunschfahrkarte für Berlin AB aus: ");
            System.out.println("Kurzstrecke AB[2,00 EUR] (1)");
            System.out.println("Einzelfahrschein AB[3,00 EUR] (2)");
            System.out.println("Tageskarte AB[8,80 EUR] (3)");
            System.out.println("4-Fahrten-Karte AB[9,40 EUR] (4)");
            System.out.println("Bezahlen (9)\n");
            System.out.print("Ihre Wahl:" );
            ticketWahl = tastatur.nextInt();

            while (ticketWahl != 1 && ticketWahl != 2 && ticketWahl != 3 && ticketWahl != 4 && ticketWahl != 9){
                System.out.println(">>Falsche Eingabe<<");
                System.out.print("Ihre Wahl: ");
                ticketWahl = tastatur.nextInt();
            }

            if (ticketWahl != 9){
                System.out.print("Anzahl der Tickets: ");
                anzahlTickets = tastatur.nextInt();
                while (anzahlTickets > 10 || anzahlTickets < 1){
                    System.out.println(">>Wählen Sie bitte eine Anzahl von 1 bis 10 Tikcets aus<<\n");
                    System.out.println("Anzahl der Tickets: ");
                    anzahlTickets = tastatur.nextInt();
                }
            }

            if (ticketWahl == 1){
                ticketPreis = 2;
            }
            if (ticketWahl == 2){
                ticketPreis = 3;
            }
            if (ticketWahl == 3){
                ticketPreis = 8.80;
            }
            if (ticketWahl == 4){
                ticketPreis = 9.40;
            }
            if (ticketWahl == 9){
                break;
            }

            zuZahlenderBetrag = zuZahlenderBetrag + (ticketPreis * anzahlTickets);
            System.out.println("\nZwischensumme: "+zuZahlenderBetrag+"\n");
        }
        return zuZahlenderBetrag;
    }
    public static double fahrkartenBezahlen(Scanner tastatur, double zuZahlenderBetrag){
        double eingezahlterGesamtbetrag = 0.0;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            double nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
            System.out.printf("Noch zu zahlen: " + "%.2f" + "  Euro", nochZuZahlen);
            System.out.print("\n");
            System.out.print("Eingabe (mind. 0.05 Cent, höchstens 20.00 Euro): ");
            double eingeworfeneMuenze = tastatur.nextDouble();
            //Akzeptable Münzen - Switch Funktion
            if (eingeworfeneMuenze != 0.05 && eingeworfeneMuenze != 0.10 && eingeworfeneMuenze != 0.20 && eingeworfeneMuenze != 0.50 && eingeworfeneMuenze != 1.00 && eingeworfeneMuenze != 2.00 && eingeworfeneMuenze != 5.00 && eingeworfeneMuenze != 10.00 && eingeworfeneMuenze != 20.00 ){
                System.out.println(">> Kein gültiges Zahlungsmittel");
                continue;
            }
            eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
        }
        return eingezahlterGesamtbetrag;
    }
    public static void fahrkartenAusgeben(){
        // Fahrscheinausgabe
        String ausgabe = "\nFahrschein wird ausgegeben";
        System.out.println(ausgabe);
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");
    }
    public static void rueckgeldAusgeben(double zuZahlenderBetrag, double eingezahlterGesamtbetrag){
        // Rückgeldberechnung und ausgabe
        double rueckgabebetrag;
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.printf("Der Rückgabebetrag in Höhe von " + "%.2f" + " Euro\n", rueckgabebetrag);
            System.out.println("wird in folgenden Münzen ausgezahlt:");
            while (rueckgabebetrag >= 2.00) { // 2-Euro-Münzen
                System.out.println("2 Euro");
                rueckgabebetrag = rueckgabebetrag - 2.00;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
            }
            while (rueckgabebetrag >= 1.00) { // 1-Euro-Münzen
                System.out.println("1 Euro");
                rueckgabebetrag = rueckgabebetrag - 1.00;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
            }
            while (rueckgabebetrag >= 0.50) { // 50-Cent-Münzen
                System.out.println("50 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.50;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
            }
            while (rueckgabebetrag >= 0.20) { // 20-Cent-Münzen
                System.out.println("20 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.20;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;

            }
            while (rueckgabebetrag >= 0.10) { // 10-Cent-Münzen
                System.out.println("10 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.10;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
            }
            while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
                System.out.println("5 Cent");
                rueckgabebetrag = rueckgabebetrag - 0.05;
                rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
            }
        }
        System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
                + "Wir wünschen Ihnen eine gute Fahrt.");
    }

    public static void muenzRueckgabe(double rueckgabebetrag, double nummerEuro ){
        while (rueckgabebetrag >= nummerEuro) { // 2-Euro-Münzen
            System.out.println("2 Euro");
            rueckgabebetrag = rueckgabebetrag - nummerEuro;
            rueckgabebetrag = Math.round(rueckgabebetrag*100)/100.00;
        }
    }
}
