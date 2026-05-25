package es.unican.is2;

public class TransporteMercanciasPeligrosas extends TransporteMercancias {
    private static final int EXTRA_PELIGROSIDAD = 50;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) {
        // WMC = 1, CCog = 0
        super(horas, toneladas);
    }

    @Override
    public double extra() {
        // WMC = 1, CCog = 0
        return EXTRA_PELIGROSIDAD + super.extra();
    }

}
