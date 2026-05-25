package es.unican.is2;


public abstract class Transporte {
    private static final int PRECIO_HORA_CONDUCIDA = 5;
    protected double horas;

    public Transporte(double horas) {
        // WMC = 1, CCog = 0
        if (horas <= 0) { // WMC + 1 , CCog + 1
            throw new IllegalArgumentException("Las horas deben ser mayores a 0");
        }
        // WMC = 2, CCog = 1
        this.horas = horas;
    }
    public double getHoras() { 
        // WMC = 1, CCog = 0
		return horas;
	}

    public double extra() { 
        // WMC = 1, CCog = 0
        return horas * PRECIO_HORA_CONDUCIDA;
    }
}
