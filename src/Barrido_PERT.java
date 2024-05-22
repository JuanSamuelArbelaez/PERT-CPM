import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Barrido_PERT {
    public float getTiempoEsperado() {
        return tiempoEsperado;
    }

    private float[][] normalDistribution;
    private float desviacionEstandar = 0;
    private float varianzaCritica = 0;
    private float tiempoEsperado = 0;
    private ArrayList<PERT_Cell> celdas;
    private ArrayList<PERT_Cell> initialCells = new ArrayList<>();
    private ArrayList<PERT_Cell> finalCells = new ArrayList<>();
    private ArrayList<PERT_Cell> actividadesCriticas = new ArrayList<>();
    public Barrido_PERT(ArrayList<PERT_Cell> celdas){
        setNormalDistribution();
        this.celdas = celdas;

        setInitialCells();
        barrido_Cercano(this.initialCells, 0);

        setFinalCells();
        barrido_Lejano(this.finalCells, this.tiempoEsperado);

        setCaminoCritico();

        calcularVarianza();
        calcularDesviacionEstandar();

    }

    private void setNormalDistribution() {
        normalDistribution = new float[][]{
                {0.5000f, 0.5040f, 0.5080f, 0.5120f, 0.5160f, 0.5199f, 0.5239f, 0.5279f, 0.5319f, 0.5359f},
                {0.5398f, 0.5438f, 0.5478f, 0.5517f, 0.5557f, 0.5596f, 0.5636f, 0.5675f, 0.5714f, 0.5753f},
                {0.5793f, 0.5832f, 0.5871f, 0.5910f, 0.5948f, 0.5987f, 0.6026f, 0.6064f, 0.6103f, 0.6141f},
                {0.6179f, 0.6217f, 0.6255f, 0.6293f, 0.6331f, 0.6368f, 0.6406f, 0.6443f, 0.6480f, 0.6517f},
                {0.6554f, 0.6591f, 0.6628f, 0.6664f, 0.6700f, 0.6736f, 0.6772f, 0.6808f, 0.6844f, 0.6879f},
                {0.6915f, 0.6950f, 0.6985f, 0.7019f, 0.7054f, 0.7088f, 0.7123f, 0.7157f, 0.7190f, 0.7224f},
                {0.7257f, 0.7291f, 0.7324f, 0.7357f, 0.7389f, 0.7422f, 0.7454f, 0.7486f, 0.7517f, 0.7549f},
                {0.7580f, 0.7611f, 0.7642f, 0.7673f, 0.7704f, 0.7734f, 0.7764f, 0.7794f, 0.7823f, 0.7852f},
                {0.7881f, 0.7910f, 0.7939f, 0.7967f, 0.7995f, 0.8023f, 0.8051f, 0.8078f, 0.8106f, 0.8133f},
                {0.8159f, 0.8186f, 0.8212f, 0.8238f, 0.8264f, 0.8289f, 0.8315f, 0.8340f, 0.8365f, 0.8389f},
                {0.8413f, 0.8438f, 0.8461f, 0.8485f, 0.8508f, 0.8531f, 0.8554f, 0.8577f, 0.8599f, 0.8621f},
                {0.8643f, 0.8665f, 0.8686f, 0.8708f, 0.8729f, 0.8749f, 0.8770f, 0.8790f, 0.8810f, 0.8830f},
                {0.8849f, 0.8869f, 0.8888f, 0.8907f, 0.8925f, 0.8944f, 0.8962f, 0.8980f, 0.8997f, 0.9015f},
                {0.9032f, 0.9049f, 0.9066f, 0.9082f, 0.9099f, 0.9115f, 0.9131f, 0.9147f, 0.9162f, 0.9177f},
                {0.9192f, 0.9207f, 0.9222f, 0.9236f, 0.9251f, 0.9265f, 0.9279f, 0.9292f, 0.9306f, 0.9319f},
                {0.9332f, 0.9345f, 0.9357f, 0.9370f, 0.9382f, 0.9394f, 0.9406f, 0.9418f, 0.9429f, 0.9441f},
                {0.9452f, 0.9463f, 0.9474f, 0.9484f, 0.9495f, 0.9505f, 0.9515f, 0.9525f, 0.9535f, 0.9545f},
                {0.9554f, 0.9564f, 0.9573f, 0.9582f, 0.9591f, 0.9599f, 0.9608f, 0.9616f, 0.9625f, 0.9633f},
                {0.9641f, 0.9649f, 0.9656f, 0.9664f, 0.9671f, 0.9678f, 0.9686f, 0.9693f, 0.9699f, 0.9706f},
                {0.9713f, 0.9719f, 0.9726f, 0.9732f, 0.9738f, 0.9744f, 0.9750f, 0.9756f, 0.9761f, 0.9767f},
                {0.9772f, 0.9778f, 0.9783f, 0.9788f, 0.9793f, 0.9798f, 0.9803f, 0.9808f, 0.9812f, 0.9817f},
                {0.9821f, 0.9826f, 0.9830f, 0.9834f, 0.9838f, 0.9842f, 0.9846f, 0.9850f, 0.9854f, 0.9857f},
                {0.9861f, 0.9864f, 0.9868f, 0.9871f, 0.9875f, 0.9878f, 0.9881f, 0.9884f, 0.9887f, 0.9890f},
                {0.9893f, 0.9896f, 0.9898f, 0.9901f, 0.9904f, 0.9906f, 0.9909f, 0.9911f, 0.9913f, 0.9916f},
                {0.9918f, 0.9920f, 0.9922f, 0.9925f, 0.9927f, 0.9929f, 0.9931f, 0.9932f, 0.9934f, 0.9936f},
                {0.9938f, 0.9940f, 0.9941f, 0.9943f, 0.9945f, 0.9946f, 0.9948f, 0.9949f, 0.9951f, 0.9952f},
                {0.9953f, 0.9955f, 0.9956f, 0.9957f, 0.9959f, 0.9960f, 0.9961f, 0.9962f, 0.9963f, 0.9964f},
                {0.9965f, 0.9966f, 0.9967f, 0.9968f, 0.9969f, 0.9970f, 0.9971f, 0.9972f, 0.9973f, 0.9974f},
                {0.9974f, 0.9975f, 0.9976f, 0.9977f, 0.9977f, 0.9978f, 0.9979f, 0.9979f, 0.9980f, 0.9981f},
                {0.9981f, 0.9982f, 0.9982f, 0.9983f, 0.9984f, 0.9984f, 0.9985f, 0.9985f, 0.9986f, 0.9986f},
                {0.9987f, 0.9987f, 0.9987f, 0.9988f, 0.9988f, 0.9989f, 0.9989f, 0.9989f, 0.9990f, 0.9990f},
                {0.9990f, 0.9991f, 0.9991f, 0.9991f, 0.9992f, 0.9992f, 0.9992f, 0.9992f, 0.9993f, 0.9993f},
                {0.9993f, 0.9993f, 0.9994f, 0.9994f, 0.9994f, 0.9994f, 0.9994f, 0.9995f, 0.9995f, 0.9995f},
                {0.9995f, 0.9995f, 0.9995f, 0.9996f, 0.9996f, 0.9996f, 0.9996f, 0.9996f, 0.9996f, 0.9997f},
                {0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f, 0.9997f},
                {0.9997f, 0.9997f, 0.9997f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f},
                {0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f, 0.9998f}
        };
    }

    private void calcularVarianza(){
        this.varianzaCritica = 0;
        for(PERT_Cell c : celdas){
            if(c.isCritical()) this.varianzaCritica+=c.getVarianza();
        }
    }

    private void calcularDesviacionEstandar(){
        this.desviacionEstandar = (float) Math.sqrt(this.varianzaCritica);
    }
    public float calcularProbabilidad_TiempoEstimado(float tiempoEstimado){
        double z = (tiempoEstimado-tiempoEsperado)/desviacionEstandar;
        double prob = Math.abs(z);
        boolean positive = z >= 0;

        String probStr = Double.toString(prob);
        int x, y, point = probStr.indexOf(".");

        try {
            x = Character.getNumericValue(probStr.charAt(point - 1))*10;
        } catch (Exception e){
            x = 0;
        }

        try{
            x += Character.getNumericValue(probStr.charAt(point + 1));
        } catch (Exception e){
            x += 0;
        }

        try{
            y = Character.getNumericValue(probStr.charAt(point + 2));
        } catch (Exception e){
            y = 0;
        }
        if(x>=37){
            x = 36;
            y = 9;
        }
        if(!positive) return 1- normalDistribution[x][y];
        return normalDistribution[x][y];
    }
    public float calcularTiempoEstimado_Probabilidad(float probabilidad){
        return probabilidad*desviacionEstandar + tiempoEsperado;
    }
    public float getDesviacionEstandar(){
        return this.desviacionEstandar;
    }
    public float getVarianza_ActividadesCriticas(){
        return this.varianzaCritica;
    }
    private void barrido_Lejano(ArrayList<PERT_Cell> celdas, float count){
        DecimalFormatSymbols formatoSymbols = new DecimalFormatSymbols(Locale.US);
        formatoSymbols.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##", formatoSymbols);
        if(celdas.isEmpty()) return;

        for(PERT_Cell c:celdas){
            c.setTerminacionLejana(Math.min(c.getTerminacionLejana(), count));

            c.setInicioLejano(Float.parseFloat(formato.format(c.getTerminacionLejana()-c.getTiempoEsperado())));
            c.getHolgura();
            barrido_Lejano(c.getPredecesoresInmediatos(), c.getInicioLejano());
        }
    }
    private void barrido_Cercano(ArrayList<PERT_Cell> celdas, float count){
        if(celdas.isEmpty()) return;

        for(PERT_Cell c : celdas){
            c.setInicioCercano(Math.max(c.getInicioCercano(), count));
            c.setTerminacionCercana(c.getInicioCercano()+c.getTiempoEsperado());
            barrido_Cercano(c.getAperturas(), c.getTerminacionCercana());
        }
    }
    private void setCaminoCritico(){
        for(PERT_Cell c : celdas){
            if(c.isCritical()) actividadesCriticas.add(c);
        }
    }
    public ArrayList<PERT_Cell> getCaminoCritico(){
        return this.actividadesCriticas;
    }
    public void printCells(ArrayList<PERT_Cell> celdas){
        for(PERT_Cell cell: celdas){
            System.out.print("Nombre: "+cell.getActividad().getNombre()+ " | t: "+cell.getTiempoEsperado());
            System.out.print(" | IC: "+cell.getInicioCercano()+" | TC: "+cell.getTerminacionCercana());
            System.out.print(" | IL: "+cell.getInicioLejano()+" | TL: "+cell.getTerminacionLejana());
            System.out.println(" | Holgura: "+cell.getHolgura()+" | Critico: "+cell.isCritical());
            System.out.println("------------------------------------------------------------------------------------------------");
        }
    }
    private  void setInitialCells(){
        for(PERT_Cell cell: celdas){
            cell.setInicioCercano(0);
            if(cell.getPredecesoresInmediatos().isEmpty()) initialCells.add(cell);
        }
    }
    private void setFinalCells(){
        setTiempoEsperado();
        for(PERT_Cell cell: celdas){
            cell.setTerminacionLejana(this.tiempoEsperado);
            if(cell.getAperturas().isEmpty()) finalCells.add(cell);
        }
    }
    private void setTiempoEsperado(){
        DecimalFormatSymbols formatoSymbols = new DecimalFormatSymbols(Locale.US);
        formatoSymbols.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.##", formatoSymbols);
        for(PERT_Cell cell: celdas){
            tiempoEsperado = Float.parseFloat(formato.format(Math.max(cell.getTerminacionCercana(), tiempoEsperado)));
        }
    }
}
