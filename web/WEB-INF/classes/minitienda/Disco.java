package minitienda;

// Importamos la libreria necesaria
import java.io.Serializable;

// Clase Disco
public class Disco implements Serializable {

    // Atributos
    private String info;
    private float precio;
    private int cantidad;

    // Constructor
    public Disco(String info, float precio, int cantidad) {
        this.info = info;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
