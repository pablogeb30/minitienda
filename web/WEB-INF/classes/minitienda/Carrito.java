package minitienda;

// Importamos las librerias necesarias
import java.io.Serializable;
import java.util.ArrayList;

// Clase Carrito
public class Carrito implements Serializable {

    // Atributos
    private float importeTotal;
    private ArrayList<Disco> discos;

    // Constructor
    public Carrito() {
        this.importeTotal = 0.0f;
        this.discos = new ArrayList<>();
    }

    // Getters y setters
    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }

}
