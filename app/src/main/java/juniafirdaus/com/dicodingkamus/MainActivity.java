package juniafirdaus.com.dicodingkamus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import juniafirdaus.com.dicodingkamus.English.EnglishFragment;
import juniafirdaus.com.dicodingkamus.Indonesia.IndonesiaFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CircleImageView profileCircleImageView;
    String profileImageUrl = "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAb8AAAAJGVlMmE5ZmNiLTZlMDQtNDcyMi04OWUzLTcwYWIxZTMzYjhmZA.jpg";


    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Kamus");


        drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        profileCircleImageView = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        Glide.with(MainActivity.this)
                .load(profileImageUrl)
                .into(profileCircleImageView);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            Fragment currentFragment = new IndonesiaFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, currentFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    protected void onPause() {
        super.onPause();
        drawer.removeDrawerListener(toggle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Bundle bundle = new Bundle();

        Fragment fragment = null;

        String title = "";
        if (id == R.id.nav_camera) {

            title = "Indonesia-English";
            fragment = new IndonesiaFragment();

        } else if (id == R.id.nav_gallery) {

            title = "English-Indonesia";
            fragment = new EnglishFragment();
            bundle.putString(EnglishFragment.EXTRAS, "English-Indonesia");
            fragment.setArguments(bundle);

        } else if (id == R.id.nav_send) {
            title = "History";
            fragment = new HistoryFragment();
            bundle.putString(HistoryFragment.EXTRAS, "HistoryFragment");
            fragment.setArguments(bundle);
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}