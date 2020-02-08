package io.github.skyhacker2.modularization_with_dagger2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.github.skyhacker2.export_api.component_home.ComponentHome;
import io.github.skyhacker2.navigation.navigator.ComponentManager;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidSchedulers.mainThread().scheduleDirect(()->{
            ComponentHome homeComponent = ComponentManager.component(ComponentHome.class);
            if (homeComponent != null) {
                homeComponent.goToHome(this);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "No Home Component", Toast.LENGTH_SHORT).show();
            }
        }, 2000, TimeUnit.MILLISECONDS);


    }
}
