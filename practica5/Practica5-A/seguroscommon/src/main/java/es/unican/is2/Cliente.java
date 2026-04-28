package es.unican.is2;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un cliente de la empresa de seguros
 * Un cliente se identifica por su dni
 */
public class Cliente {

    private String dni;

    private String nombre;  
    
    private boolean minusvalia;

    private List<Seguro> seguros = new LinkedList<Seguro>();

    public class SeguroNoEncontradoException extends RuntimeException { // <--- CAMBIA ESTO
    public SeguroNoEncontradoException(String message) {
        super(message);
    }
}
    
	/**
     * Retorna los seguros del cliente 
     */
    public List<Seguro> getSeguros() {
        return seguros;
    }
    
    /**
     * Asigna la lista de seguros
     */
    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    /**
     * Retorna el nombre del cliente.   
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Retorna el dni del cliente.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Asigna el dni del cliente
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
    
    /**
     * Indica si el cliente es minusvalido
     */
    public boolean getMinusvalia() {
    	return minusvalia;
    }

    /**
     * Asigna la minusvalia del cliente
     * @param minusvalia
     */
     public void setMinusvalia(boolean minusvalia) {
        this.minusvalia = minusvalia;
    }

 /**
     * Asigna seguro a la lista
     * @param seguro
     * @throws NullPointerException si el seguro o la lista de seguros son null
     */
   public void anhadirSeguro(Seguro seguro) throws NullPointerException {
        if (seguros == null || seguro == null) {  
            throw new NullPointerException("El seguro o la lista de seguros no pueden ser null.");
        }
        if (seguros.contains(seguro)) {
            throw new RuntimeException("El seguro ya existe en la lista.");
        }
        // --------------------------------------------------------

        seguros.add(seguro);
    }

    /**
     * Elimina seguro de la lista
     * @param seguro
     * @throws NullPointerException si el seguro o la lista de seguros son null
     * @throws SeguroNoEncontradoException si el seguro no se encuentra en la lista de seguros del cliente
     */
    public void eliminarSeguro(Seguro seguro) {
         if (seguros == null || seguro == null) {
            throw new NullPointerException("El seguro o la lista de seguros no pueden ser null.");
        }
        if (!seguros.contains(seguro)) {
            throw new SeguroNoEncontradoException("El seguro no se encuentra en la lista de seguros del cliente.");
        }
        seguros.remove(seguro);
    }
    
    /**
     * Calcula el total a pagar por el cliente por 
     * todos los seguros a su nombre
     * @throws  NullPointerException si la lista de seguros es null
     */
    public double totalSeguros()  {
        double total = 0;
        for (Seguro seguro : seguros) {
            total += seguro.precio();
        }
        if (minusvalia) {
            total = total * 0.75; // Aplicamos el 25% de descuento
        }
        return total;
    }
}
