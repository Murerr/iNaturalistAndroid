package org.inaturalist.android;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {
    private static final String TAG = "Credits";

    private TextView mAboutText;
    private INaturalistApp mApp;

	@Override
	protected void onStop()
	{
		super.onStop();
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApp = (INaturalistApp) getApplicationContext();
        mApp.applyLocaleSettings(getBaseContext());
        setContentView(R.layout.credits);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.credits_title);

        mAboutText = findViewById(R.id.inat_credits);

        StringBuilder credits = new StringBuilder();

        credits.append(getString(R.string.inat_credits));

        // Add per-network credit
        final String[] inatNetworks = mApp.getINatNetworks();

        credits.append("<br/><br/>");
        credits.append(getString(R.string.inat_credits_networks_pre));
        credits.append("<br/><br/>");

        for (String network : inatNetworks) {
            String networkCredit = mApp.getStringResourceByName("network_credit_" + network, "n/a");
            if (networkCredit.equals("n/a")) continue;

            credits.append(networkCredit);
            credits.append("<br/><br/>");
        }

        credits.append(getString(R.string.inat_credits_post));

        HtmlUtils.fromHtml(mAboutText, credits.toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
