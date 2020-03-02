package Entorno;

public class Tipo {
  
    private tipo_primitivo tipo_primitivo;
    private tipo_primitivo tipo_vector;
    
    public Tipo(tipo_primitivo tipo){
        this.tipo_primitivo = tipo;
    }
    
    public Tipo(tipo_primitivo vector, tipo_primitivo tipo_vector){
        this.tipo_primitivo = vector;
        this.tipo_vector = tipo_vector;
    }

    public tipo_primitivo getTipo_primitivo() {
        return tipo_primitivo;
    }
    public void setTipo_primitivo(tipo_primitivo tipo_primitivo) {
        this.tipo_primitivo = tipo_primitivo;
    }
    public tipo_primitivo getTipo_vector() {
        return tipo_vector;
    }
    
    public enum tipo_primitivo
    {
        ENTERO, DECIMAL, BOOLEAN, CADENA, LISTA, VECTOR, MATRIZ, ARREGLO
    }
}
