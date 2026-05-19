package es.unican.is2;

public class TransportePersonas extends Transporte {
    private static final int LIMITE_GRUPO_COLECTIVO = 10;
    private static final double EXTRA_NO_COLECTIVO = 0.5;
    private static final double EXTRA_COLECTIVO = 1.0;

    private int personas;

    public TransportePersonas(double horas, int personas) {
        // WMC = 1, CCog = 0
        super(horas);
        if (personas <= 0) { // WMC + 1 , CCog + 1
            throw new IllegalArgumentException("Personas no validas");
        }
        // WMC = 2, CCog = 1
        this.personas = personas;
    }

    public int getPersonas() {
        // WMC = 1, CCog = 0
        return personas;
    }

    @Override
    public double extra() {
       // WMC = 1, CCog = 0
        double sueldoExtraTransporte = 0;

        if (personas < LIMITE_GRUPO_COLECTIVO) { // WMC + 1, CCog + 1
            sueldoExtraTransporte = this.horas * EXTRA_NO_COLECTIVO; 
        } else { // WMC + 1, CCog + 1
            sueldoExtraTransporte = this.horas * EXTRA_COLECTIVO;
        }
        
        return sueldoExtraTransporte + super.extra();
        // WMC = 3, CCog = 2
    }
}
