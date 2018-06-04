package es.pue.android.olympicgamestickets;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTicketsSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tickets_summary);

        Intent i = getIntent();
        String sportName = i.getStringExtra("sportName");
        int numTicketsAdult = i.getIntExtra("numTicketsAdult", 0);
        int numTicketsChildren = i.getIntExtra("numTicketsChildren", 0);
        double totalPrice = i.getDoubleExtra("totalPrice", 0d);

        TextView tvSportName = findViewById(R.id.tvSportName);
        TextView tvNumTicketsAdult = findViewById(R.id.tvNumTicketsAdult);
        TextView tvNumTicketsChildren = findViewById(R.id.tvNumTicketsChildren);
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);

        tvSportName.setText(sportName);
        tvNumTicketsAdult.setText(""+numTicketsAdult);
        tvNumTicketsChildren.setText(""+numTicketsChildren);
        tvTotalPrice.setText(String.format("%s€", totalPrice));
    }
}
