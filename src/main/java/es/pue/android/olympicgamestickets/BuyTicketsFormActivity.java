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
import es.pue.android.olympicgamestickets.service.TicketsCalculator;

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

        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        spNumTicketsAdult = findViewById(R.id.spNumTicketsAdult);
        spNumTicketsAdult.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spNumTicketsChildren = findViewById(R.id.spNumTicketsChildren);
        spNumTicketsChildren.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        spSport = findViewById(R.id.spSport);
        spSport.setOnItemSelectedListener(this.getOnSpSportItemSelectedListener());

        this.initSports();
    }

    private void initSports() {
        sports = new ArrayList<>();
        List<String> sportNames = new ArrayList<>();

        sports.add(new Sport(1, getString(R.string.litsArchery), 10.00d, 5.00d));
        sports.add(new Sport(2, getString(R.string.litsJabalin), 20.00d, 10.00d));
        sports.add(new Sport(3, getString(R.string.litsThrowHammer), 30.00d, 15.00d));

        for (Sport sport: sports) {
            sportNames.add(sport.getName());
        }

        spSport.setAdapter(
                new ArrayAdapter<>(
                        this, R.layout.support_simple_spinner_dropdown_item,
                        sportNames
                )
        );
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

        return new TicketsCalculator().calculte(
                spNumTicketsAdult.getSelectedItemPosition(),
                spNumTicketsChildren.getSelectedItemPosition(),
                sports.get(selectedSport));
    }

    public void showInfo(View view) {
        int selectedSport = spSport.getSelectedItemPosition();
        Sport sport = sports.get(selectedSport);

        Intent i = new Intent(BuyTicketsFormActivity.this, ShowTicketsSummaryActivity.class);
        i.putExtra(Constants.NUM_TICKETS_ADULT, spNumTicketsAdult.getSelectedItemPosition());
        i.putExtra(Constants.NUM_TICKETS_CHILD, spNumTicketsChildren.getSelectedItemPosition());
        i.putExtra(Constants.SPORT, sport);
        startActivity(i);
    }
}
