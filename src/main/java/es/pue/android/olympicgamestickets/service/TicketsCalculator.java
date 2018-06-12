package es.pue.android.olympicgamestickets.service;

import es.pue.android.olympicgamestickets.model.Sport;

public class TicketsCalculator {

    public double calculte(int numTicketsAdult, int numTicketsChildren, Sport sport) {

        double ticketPriceAdult = sport.getPriceAdult();
        double ticketPriceChildren = sport.getPriceChild();

        return (numTicketsAdult * ticketPriceAdult) + (numTicketsChildren * ticketPriceChildren);
    }
}
