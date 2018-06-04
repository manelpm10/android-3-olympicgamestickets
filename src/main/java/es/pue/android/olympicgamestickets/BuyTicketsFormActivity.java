package es.pue.android.olympicgamestickets;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuyTicketsFormActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private Spinner spNumTicketsAdult;
    private Spinner spNumTicketsChildren;
    private Spinner spSport;
    private ArrayList<Double> ticketPricesAdult;
    private ArrayList<Double> ticketPricesChildren;
    /**
     * Better way to use sports array (res/values/sports.xml) from code.
     */
    //final List<String> sports = Arrays.asList(getResources().getStringArray(R.array.sports));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets_form);

        this.initTicketPrices();

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        spNumTicketsAdult = findViewById(R.id.spNumTicketsAdult);
        spNumTicketsAdult.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spNumTicketsChildren = findViewById(R.id.spNumTicketsChildren);
        spNumTicketsChildren.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spSport = findViewById(R.id.spSport);
        spSport.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());


    }

    private void initTicketPrices() {
        ticketPricesAdult = new ArrayList<>();
        ticketPricesChildren = new ArrayList<>();

        ticketPricesAdult.add(0d);
        ticketPricesAdult.add(10.00d);
        ticketPricesAdult.add(20.00d);
        ticketPricesAdult.add(30.00d);

        ticketPricesChildren.add(0d);
        ticketPricesChildren.add(5.00d);
        ticketPricesChildren.add(10.00d);
        ticketPricesChildren.add(15.00d);
    }

    @NonNull
    private AdapterView.OnItemSelectedListener getOnSpSportItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tvTotalPrice.setText(String.format("%s€", getTotalPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    private double getTotalPrice() {
        int numTicketsAdult = spNumTicketsAdult.getSelectedItemPosition();
        int numTicketsChildren = spNumTicketsChildren.getSelectedItemPosition();
        double ticketPriceAdult = ticketPricesAdult.get(spSport.getSelectedItemPosition());
        double ticketPriceChildren = ticketPricesChildren.get(spSport.getSelectedItemPosition());
        return (numTicketsAdult * ticketPriceAdult) + (numTicketsChildren * ticketPriceChildren);
    }

    public void showInfo(View view) {
        Intent i = new Intent(BuyTicketsFormActivity.this, ShowTicketsSummaryActivity.class);
        i.putExtra("numTicketsAdult", spNumTicketsAdult.getSelectedItemPosition());
        i.putExtra("numTicketsChildren", spNumTicketsChildren.getSelectedItemPosition());
        i.putExtra("totalPrice", getTotalPrice());
        i.putExtra("sportName", spSport.getSelectedItem().toString());
        startActivity(i);
    }
}
