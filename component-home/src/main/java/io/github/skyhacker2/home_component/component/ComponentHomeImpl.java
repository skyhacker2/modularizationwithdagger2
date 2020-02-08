package io.github.skyhacker2.home_component.component;

import android.content.Context;

import io.github.skyhacker2.export_api.component_home.ComponentHome;
import io.github.skyhacker2.home_component.HomeActivity;

public class ComponentHomeImpl implements ComponentHome {

    @Override
    public void goToHome(Context context) {
        HomeActivity.start(context);
    }

    @Override
    public String getName() {
        return "ComponentHomeImpl";
    }
}
