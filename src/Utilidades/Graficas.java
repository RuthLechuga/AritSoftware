package Utilidades;

import java.util.LinkedList;
import org.jfree.chart.JFreeChart;

public class Graficas {

    private static Graficas stGraficas;
    LinkedList<JFreeChart> graficas;
    
    private Graficas(){
        graficas = new LinkedList<JFreeChart>();
    }
    
    public static Graficas getSingletonInstance() {

        if (stGraficas == null)
            stGraficas = new Graficas();
        
        return stGraficas;
    }
    
    public void reiniciar(){
        graficas = new LinkedList<JFreeChart>();
    }
    
    public void add_grafica(JFreeChart grafica){
        this.graficas.add(grafica);
    }
    
    public LinkedList<JFreeChart> getGraficas(){
        return this.graficas;
    }
}
