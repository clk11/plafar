package Entitati;

// Declaram o clasa care contine informatiile referitoare la fiecare planta medicinala

public class PlantaMedicinala {
    public String denumire; 
    public int cantitate; 
    public double pret; 

    public PlantaMedicinala(String denumire, int cantitate, double pret) {
        this.denumire = denumire;
        this.cantitate = cantitate;
        this.pret = pret;
    }
}
