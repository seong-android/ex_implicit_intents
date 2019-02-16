package com.seongandroid.implicit_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.editText_website);
        mLocationEditText = (EditText) findViewById(R.id.editText_location);
        mShareTextEditText = (EditText) findViewById(R.id.editText_share_text);
    }

    public void openWebsite(View view) {
        // get the uri and parse it
        String uri = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(uri);

        // create the intent
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Can't handle this implicit intent");
            Toast toast = Toast.makeText(this, "Please enter valid URI", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void openLocation(View view) {
        // get the location and parse
        String location = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Can't handle this implicit intent");
            Toast toast = Toast.makeText(this, "Please enter a valid location", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void shareText(View view) {
        String text = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(text)
                .startChooser();
    }
}
