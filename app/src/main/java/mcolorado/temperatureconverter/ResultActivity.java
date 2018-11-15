package mcolorado.temperatureconverter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * Activity to display the results from the calculation
 */
public class ResultActivity extends AppCompatActivity {

    private TextView txtResult;
    private FloatingActionButton fab;
    private String resultText;

    /**
     * Activity lifecycle onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Load toolbar at the top
        Toolbar toolbar = findViewById(R.id.widgetToolbar);
        setSupportActionBar(toolbar);

        // Get TXT element
        txtResult = findViewById(R.id.txtResult);

        // Get Floating Action Button element
        fab = findViewById(R.id.floatingBtn);
        // Add OnClick listener
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls a selector to choose the messaging up for the user
             * @param view
             */
            @Override
            public void onClick(View view) {

                // Defines the SEND method for the intent
                Intent i = new Intent(Intent.ACTION_SEND);
                // Add configuration to the intent
                i.setData(Uri.parse("mailto:"));
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, "");
                i.putExtra(Intent.EXTRA_SUBJECT, "Your Temperature Conversion Result");
                i.putExtra(Intent.EXTRA_TEXT, resultText);

                try {
                    // Creates the choose menu to select the messaging app
                    startActivity(Intent.createChooser(i, "Send mail..."));
                    finish();
                    // Debug log
                    Log.i("Finished sending email.", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ResultActivity.this,
                            "Unable to find a suitable app for messaging.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        // Disable FAB button by default, so you cannot send an empty email
        fab.setEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Activity lifecycle onStart
     */
    @Override
    protected void onStart() {
        super.onStart();

        // Get information from previous activity (result and unit)
        Bundle extras = getIntent().getExtras();
        // If this information is present...
        if (extras != null) {
            // Populate app data
            Double result = extras.getDouble("result");
            DecimalFormat df = new DecimalFormat("#.##");
            resultText = df.format(result) + " °" + extras.getString("unit");
            txtResult.setText(resultText);
            // And enable FAB button
            fab.setEnabled(true);
        }
    }

    /**
     * Activity lifecycle method
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // TODO
    }
}
