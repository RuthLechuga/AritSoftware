package Entorno;

public class Tipo {
  
    private tipo_primitivo tipo_primitivo;
    
    public Tipo(tipo_primitivo tipo){
        this.tipo_primitivo = tipo;
    }
    
    public tipo_primitivo getTipo_primitivo() {
        return tipo_primitivo;
    }
    public void setTipo_primitivo(tipo_primitivo tipo_primitivo) {
        this.tipo_primitivo = tipo_primitivo;
    }
    
    public enum tipo_primitivo
    {
        ENTERO, DECIMAL, BOOLEAN, CADENA, LISTA, VECTOR, MATRIZ, ARREGLO
    }
}
