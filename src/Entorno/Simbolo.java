package Entorno;
import static Entorno.Tipo.tipo_primitivo.*;

public class Simbolo {
    
    private Tipo tipo;
    private String identificador;
    private Object valor;
    
    public Simbolo(Tipo tipo, String identificador){
        this.tipo = tipo;
        this.identificador = identificador;
        
        switch(this.tipo.getTipo_primitivo()){
            case ENTERO: 
                this.valor = 0;
                break;
            case DECIMAL: 
                this.valor = 0.0;
                break;
            case BOOLEAN: 
                this.valor = false;
                break;
            case CADENA: 
            case LISTA: 
            case VECTOR: 
            case ARREGLO: 
            case MATRIZ: 
                this.valor = null;
                break;
        }
    }

    public Simbolo(String identificador, Object valor){
        this.identificador = identificador;
        this.valor = valor;
        
        if(valor instanceof Integer)
            this.tipo = new Tipo(ENTERO);
        
        else if(valor instanceof Double)
            this.tipo = new Tipo(DECIMAL);
        
        else if(valor instanceof String)
            this.tipo = new Tipo(CADENA);
        
        else if(valor instanceof Boolean)
            this.tipo = new Tipo(BOOLEAN);
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public Object getValor() {
        return valor;
    }
    public void setValor(Object valor) {
        this.valor = valor;
    }   
}
