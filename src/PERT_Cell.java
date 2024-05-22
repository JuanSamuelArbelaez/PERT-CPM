import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class PERT_Cell {
    private float varianza = 0;
    private float holgura = 0;
    private boolean critical = false;
    private float tiempoEsperado = 0;
    private float inicioCercano = 0;
    private float inicioLejano = 0;
    private float terminacionCercana = 0;
    private float terminacionLejana = 0;
    private final ArrayList<PERT_Cell> predecesoresInmediatos = new ArrayList<>();
    private final ArrayList<PERT_Cell> aperturas = new ArrayList<>();
    private Actividad actividad;
    public PERT_Cell(Actividad actividad){
        this.actividad = actividad;
        this.tiempoEsperado = calcularTiempoEsperado(actividad);
        this.varianza = calcularVarianza(actividad);
    }

    private float calcularVarianza(Actividad actividad){
        float varianza = (float) (Math.pow(((actividad.getTiempoPesimista()- actividad.getTiempoOptimista())/6.0), 2));

        DecimalFormatSymbols formatoSymbols = new DecimalFormatSymbols(Locale.US);
        formatoSymbols.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##", formatoSymbols);
        return Float.parseFloat(formato.format(varianza));
    }

    private float calcularTiempoEsperado(Actividad actividad) {
        float tEsperado = (float) ((actividad.getTiempoOptimista() + (4.0*actividad.getTiempoProbable()) + actividad.getTiempoPesimista())/6.0);

        DecimalFormatSymbols formatoSymbols = new DecimalFormatSymbols(Locale.US);
        formatoSymbols.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##", formatoSymbols);
        return Float.parseFloat(formato.format(tEsperado));
    }

    public float getTiempoEsperado() {
        return tiempoEsperado;
    }

    public void setTiempoEsperado(float tiempoEsperado) {
        this.tiempoEsperado = tiempoEsperado;
    }

    public float getInicioCercano() {
        return inicioCercano;
    }

    public void setInicioCercano(float inicioCercano) {
        this.inicioCercano = inicioCercano;
    }

    public float getInicioLejano() {
        return inicioLejano;
    }

    public void setInicioLejano(float inicioLejano) {
        this.inicioLejano = inicioLejano;
    }

    public float getTerminacionCercana() {
        return terminacionCercana;
    }

    public void setTerminacionCercana(float terminacionCercana) {
        this.terminacionCercana = terminacionCercana;
    }

    public float getTerminacionLejana() {
        return terminacionLejana;
    }

    public void setTerminacionLejana(float terminacionLejana) {
        this.terminacionLejana = terminacionLejana;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public ArrayList<PERT_Cell> getPredecesoresInmediatos() {
        return predecesoresInmediatos;
    }

    public ArrayList<PERT_Cell> getAperturas() {
        return aperturas;
    }

    public void setHolgura(float holgura) {
        this.holgura = holgura;
    }

    public float getHolgura(){
        DecimalFormatSymbols formatoSymbols = new DecimalFormatSymbols(Locale.US);
        formatoSymbols.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##", formatoSymbols);
        setHolgura(Float.parseFloat(formato.format(getInicioLejano()-getInicioCercano())));
        critical = (holgura ==0);
        return holgura;
    }

    public float getVarianza() {
        return varianza;
    }

    public boolean isCritical() {
        return critical;
    }
}
