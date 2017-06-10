package xyz.marshallaf.rxandroidtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

public class MainActivity extends Activity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);

        RxView.clicks(button)
                .subscribe(aVoid -> Toast.makeText(MainActivity.this, "RxView.clicks", Toast.LENGTH_SHORT).show());

        RxTextView.textChanges(editText)
                .subscribe(charSequence -> textView.setText(charSequence));
    }
}
