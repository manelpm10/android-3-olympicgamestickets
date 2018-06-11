package es.pue.android.olympicgamestickets;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.pue.android.olympicgamestickets.model.Sport;

public class BuyTicketsFormActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private Spinner spNumTicketsAdult;
    private Spinner spNumTicketsChildren;
    private Spinner spSport;
    private List<Sport> sports;
    /**
     * Better way to use sports array (res/values/sports.xml) from code.
     */
    //final List<String> sports = Arrays.asList(getResources().getStringArray(R.array.sports));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets_form);

        this.init();

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        spNumTicketsAdult = findViewById(R.id.spNumTicketsAdult);
        spNumTicketsAdult.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spNumTicketsChildren = findViewById(R.id.spNumTicketsChildren);
        spNumTicketsChildren.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spSport = findViewById(R.id.spSport);
        spSport.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        //this.printSports();
    }

    private void printSports() {
        ArrayAdapter spinnerAdapter = (ArrayAdapter) spSport.getAdapter();
        for (Sport sport: sports) {
            spinnerAdapter.add(sport.getName());
        }
        spinnerAdapter.notifyDataSetChanged();
    }

    private void init() {
        sports = new ArrayList<>();

        sports.add(new Sport(1, getString(R.string.litsArchery), 10.00d, 5.00d));
        sports.add(new Sport(2, getString(R.string.litsJabalin), 20.00d, 10.00d));
        sports.add(new Sport(3, getString(R.string.litsThrowHammer), 30.00d, 15.00d));
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
        int selectedSport = spSport.getSelectedItemPosition();
        if (0 >= selectedSport) {
            return 0;
        }

        int numTicketsAdult = spNumTicketsAdult.getSelectedItemPosition();
        int numTicketsChildren = spNumTicketsChildren.getSelectedItemPosition();
        double ticketPriceAdult = sports.get(selectedSport).getPriceAdult();
        double ticketPriceChildren = sports.get(selectedSport).getPriceChild();

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
