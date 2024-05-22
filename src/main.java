import javax.sound.midi.SysexMessage;
import java.util.ArrayList;

public class main {
    public static void main(String[] args){
        ejercicio1();
    }
    public static void ejercicio2(){
        ArrayList<PERT_Cell> cells = new ArrayList<PERT_Cell>();

        PERT_Cell c1 = new PERT_Cell(new Actividad("Tarea 1", 1, 5, 4));
        PERT_Cell c2 = new PERT_Cell(new Actividad("Tarea 2", 4, 7, 4));
        PERT_Cell c3 = new PERT_Cell(new Actividad("Tarea 3", 10, 12, 13));
        PERT_Cell c4 = new PERT_Cell(new Actividad("Tarea 4", 6, 8, 10));
        PERT_Cell c5 = new PERT_Cell(new Actividad("Tarea 5",  5, 7, 7));
        PERT_Cell c6 = new PERT_Cell(new Actividad("Tarea 6", 8, 7, 9));
        PERT_Cell c7 = new PERT_Cell(new Actividad("Tarea 7", 5, 8, 10));
        PERT_Cell c8 = new PERT_Cell(new Actividad("Tarea 8", 4, 10, 9));
        PERT_Cell c9 = new PERT_Cell(new Actividad("Tarea 9", 12, 13, 15));
        PERT_Cell c10 = new PERT_Cell(new Actividad("Tarea 10", 5, 7, 9));
        PERT_Cell c11 = new PERT_Cell(new Actividad("Tarea 11", 9, 10, 10));
        PERT_Cell c12 = new PERT_Cell(new Actividad("Tarea 12", 5, 8, 15));
        PERT_Cell c13 = new PERT_Cell(new Actividad("Tarea 13", 8, 10, 12));
        PERT_Cell c14 = new PERT_Cell(new Actividad("Tarea 14", 10, 12, 5));
        PERT_Cell c15 = new PERT_Cell(new Actividad("Tarea 15",  7, 10, 11));
        PERT_Cell c16 = new PERT_Cell(new Actividad("Tarea 16", 5, 7, 9));

        cells.add(c1);
        cells.add(c2);
        cells.add(c3);
        cells.add(c4);
        cells.add(c5);
        cells.add(c6);
        cells.add(c7);
        cells.add(c8);
        cells.add(c9);
        cells.add(c10);
        cells.add(c11);
        cells.add(c12);
        cells.add(c13);
        cells.add(c14);
        cells.add(c15);
        cells.add(c16);


        link(c1, c6);
        link(c2, c7);
        link(c3, c8);
        link(c4, c9);
        link(c4, c10);
        link(c5, c10);
        link(c4, c11);
        link(c5, c12);
        link(c6, c13);
        link(c7, c13);
        link(c8, c13);
        link(c10, c14);
        link(c11, c14);
        link(c12, c14);
        link(c9, c15);
        link(c10, c15);
        link(c13, c15);
        link(c14, c16);


        Barrido_PERT barrido = new Barrido_PERT(cells);

        ArrayList<PERT_Cell> critics = barrido.getCaminoCritico();
        barrido.printCells(cells);
        String cr="Ruta Critica: ";
        for(PERT_Cell cell: critics){
            cr+=cell.getActividad().getNombre();
            if(critics.indexOf(cell)<critics.size()-1) cr+=" - ";
        }
        System.out.println(cr);
        System.out.println("Tiempo de Terminacion: "+barrido.getTiempoEsperado());
        System.out.println("Varianza de la Ruta Critica: "+barrido.getVarianza_ActividadesCriticas());
        System.out.println("Desviacion Estandar: "+barrido.getDesviacionEstandar());
        System.out.println("Probabilidad de Terminacion del Proyecto en (42) días: "+barrido.calcularProbabilidad_TiempoEstimado(42)*100+"%");
        System.out.println("Probabilidad de Terminacion del Proyecto en (45) días: "+barrido.calcularProbabilidad_TiempoEstimado(45)*100+"%");
        //System.out.println("Dias para tener (64.43%) de Probabilidad de Terminacion de Proyecto : "+barrido.calcularTiempoEstimado_Probabilidad((float)0.6443));
    }
    public static void ejercicio1(){
        ArrayList<PERT_Cell> cells = new ArrayList<PERT_Cell>();

        PERT_Cell a = new PERT_Cell(new Actividad("Tarea A", 8, 10, 12));
        PERT_Cell b = new PERT_Cell(new Actividad("Tarea B", 6, 7, 9));
        PERT_Cell c = new PERT_Cell(new Actividad("Tarea C", 3, 3, 4));
        PERT_Cell d = new PERT_Cell(new Actividad("Tarea D", 10, 20, 30));
        PERT_Cell e = new PERT_Cell(new Actividad("Tarea E",  6, 7, 8));
        PERT_Cell f = new PERT_Cell(new Actividad("Tarea F", 9, 10, 11));
        PERT_Cell g = new PERT_Cell(new Actividad("Tarea G", 6, 7, 10));
        PERT_Cell h = new PERT_Cell(new Actividad("Tarea H", 14, 15, 16));
        PERT_Cell i = new PERT_Cell(new Actividad("Tarea I", 10, 11, 13));
        PERT_Cell j = new PERT_Cell(new Actividad("Tarea J", 6, 7, 8));
        PERT_Cell k = new PERT_Cell(new Actividad("Tarea K", 4, 7, 8));
        PERT_Cell l = new PERT_Cell(new Actividad("Tarea L", 1, 2, 4));

        cells.add(a);
        cells.add(b);
        cells.add(c);
        cells.add(d);
        cells.add(e);
        cells.add(f);
        cells.add(g);
        cells.add(h);
        cells.add(i);
        cells.add(j);
        cells.add(k);
        cells.add(l);


        link(a, d);
        link(c, e);
        link(b, f);
        link(d, f);
        link(e, f);
        link(b, g);
        link(d, g);
        link(e, g);
        link(f, h);
        link(f, i);
        link(g, j);
        link(h, j);
        link(i, k);
        link(j, k);
        link(g, l);
        link(h, l);


        Barrido_PERT barrido = new Barrido_PERT(cells);

        ArrayList<PERT_Cell> critics = barrido.getCaminoCritico();
        barrido.printCells(cells);
        String cr="Ruta Critica: ";
        for(PERT_Cell cell: critics){
            cr+=cell.getActividad().getNombre();
            if(critics.indexOf(cell)<critics.size()-1) cr+=" - ";
        }
        System.out.println(cr);
        System.out.println("Tiempo de Terminacion: "+barrido.getTiempoEsperado());
        //System.out.println("Varianza de la Ruta Critica: "+barrido.getVarianza_ActividadesCriticas());
        //System.out.println("Desviacion Estandar: "+barrido.getDesviacionEstandar());
        //System.out.println("Probabilidad de Terminacion del Proyecto en (42) días: "+barrido.calcularProbabilidad_TiempoEstimado(42)*100+"%");
        //System.out.println("Probabilidad de Terminacion del Proyecto en (45) días: "+barrido.calcularProbabilidad_TiempoEstimado(45)*100+"%");
        //System.out.println("Dias para tener (64.43%) de Probabilidad de Terminacion de Proyecto : "+barrido.calcularTiempoEstimado_Probabilidad((float)0.6443));
    }
    public static void link(PERT_Cell origen, PERT_Cell destino){
        origen.getAperturas().add(destino);
        destino.getPredecesoresInmediatos().add(origen);
    }
}
