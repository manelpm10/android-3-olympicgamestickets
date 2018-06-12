package es.pue.android.olympicgamestickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import es.pue.android.olympicgamestickets.model.Sport;
import es.pue.android.olympicgamestickets.service.TicketsCalculator;

public class ShowTicketsSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tickets_summary);

        TicketsCalculator calculator = new TicketsCalculator();

        Intent i = getIntent();
        Sport sport = (Sport) i.getSerializableExtra(Constants.SPORT);
        int numTicketsAdult = i.getIntExtra(Constants.NUM_TICKETS_ADULT, 0);
        int numTicketsChildren = i.getIntExtra(Constants.NUM_TICKETS_CHILD, 0);
        double totalPrice = calculator.calculte(numTicketsAdult, numTicketsChildren, sport);

        TextView tvSportName = findViewById(R.id.tvSportName);
        TextView tvNumTicketsAdult = findViewById(R.id.tvNumTicketsAdult);
        TextView tvNumTicketsChildren = findViewById(R.id.tvNumTicketsChildren);
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);

        tvSportName.setText(sport.getName());
        tvNumTicketsAdult.setText(""+numTicketsAdult);
        tvNumTicketsChildren.setText(""+numTicketsChildren);
        tvTotalPrice.setText(String.format("%s€", totalPrice));
    }
}
