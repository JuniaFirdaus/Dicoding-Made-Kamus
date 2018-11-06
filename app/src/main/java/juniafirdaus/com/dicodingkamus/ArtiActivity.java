package juniafirdaus.com.dicodingkamus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

public class ArtiActivity extends AppCompatActivity {

    public static final String KEYPREFF="key";
    public static final String KEYNAMA ="nama";
    public static final String KEYARTI ="arti";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arti);
        TextView txtArti = findViewById(R.id.arti);
        TextView txtIstilah = findViewById(R.id.istilah);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            txtIstilah.setText(b.getString("nama"));
            txtArti.setText(b.getString("arti"));
        }

        sharedPreferences = getSharedPreferences(KEYPREFF, Context.MODE_PRIVATE);
        String nama = txtIstilah.getText().toString();
        String arti = txtArti.getText().toString();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEYNAMA, nama);
        editor.putString(KEYARTI, arti);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.arti, menu);
        return true;
    }

}
