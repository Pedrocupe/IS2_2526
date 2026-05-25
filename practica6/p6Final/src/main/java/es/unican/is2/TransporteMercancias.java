package es.unican.is2;

public class TransporteMercancias extends Transporte {
	private static final int EXTRA_POR_TONELADA = 2;

    private int toneladas;

    public TransporteMercancias(double horas, int toneladas) {
        // WMC = 1, CCog = 0
        super(horas);
        if (toneladas <= 0) { // WMC + 1 , CCog + 1
            throw new IllegalArgumentException("Toneladas no validas");
        }
        // WMC =21, CCog = 1
        this.toneladas = toneladas;
    }

    public int getToneladas() {
        // WMC = 1, CCog = 0
        return toneladas;
    }

    @Override
    public double extra() {
        // WMC = 1, CCog = 0
        return (toneladas * EXTRA_POR_TONELADA) + super.extra();
    }


}
