package mcolorado.temperatureconverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView txtResult;
    FloatingActionButton fab;
    String resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtResult = findViewById(R.id.txtResult);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.putExtra(Intent.EXTRA_SUBJECT, "Your Temperature Conversion Result");
                i.putExtra(Intent.EXTRA_TEXT, resultText);

                if(i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
            }
        });
        fab.setEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultText = extras.getDouble("result") + " °" + extras.getString("unit");
            txtResult.setText(resultText);
            fab.setEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO
    }
}
