import java.util.ArrayList;

public class Actividad {
    private float tiempoOptimista;
    private float tiempoProbable;
    private float tiempoPesimista;
    private String nombre;
    public Actividad(String nombre, float tiempoOptimista, float tiempoProbable, float tiempoPesimista){
        this.nombre = nombre;
        this.tiempoOptimista = tiempoOptimista;
        this.tiempoProbable = tiempoProbable;
        this.tiempoPesimista = tiempoPesimista;
    }
    public float getTiempoOptimista() {
        return tiempoOptimista;
    }

    public void setTiempoOptimista(float tiempoOptimista) {
        this.tiempoOptimista = tiempoOptimista;
    }

    public float getTiempoProbable() {
        return tiempoProbable;
    }

    public void setTiempoProbable(float tiempoProbable) {
        this.tiempoProbable = tiempoProbable;
    }

    public float getTiempoPesimista() {
        return tiempoPesimista;
    }

    public void setTiempoPesimista(float tiempoPesimista) {
        this.tiempoPesimista = tiempoPesimista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
