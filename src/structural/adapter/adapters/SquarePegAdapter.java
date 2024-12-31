package structural.adapter.adapters;

import structural.adapter.round.RoundPeg;
import structural.adapter.square.SquarePeg;

/**
 * Adapter allows fitting square pegs into round holes.
 */
public class SquarePegAdapter extends RoundPeg {
    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        // Calculate a minimum circle radius, which can fit this peg.
        double result = (peg.getWidth() / 2) * Math.sqrt(2);
        return result;
    }
}
