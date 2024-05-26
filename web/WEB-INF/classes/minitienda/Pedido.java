package minitienda;

// Importamos las librerias necesarias
import java.io.Serializable;

// Clase Pedido
public class Pedido implements Serializable {

    // Atributos
    private Integer id;
    private Float importeTotal;

    // Constructor sin argumentos
    public Pedido() {
    }

    // Constructor
    public Pedido(Integer id, Float importeTotal) {
        this.id = id;
        this.importeTotal = importeTotal;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getImporteTotal() {
        return this.importeTotal;
    }

    public void setImporteTotal(Float importeTotal) {
        this.importeTotal = importeTotal;
    }

}
