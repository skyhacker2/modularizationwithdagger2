package io.github.skyhacker2.component_a.component;

import android.content.Context;

import io.github.skyhacker2.component_a.LoginActivity;
import io.github.skyhacker2.export_api.component_login.ComponentLogin;

public class ComponentLoginImpl implements ComponentLogin {

    @Override
    public void goToLogin(Context context) {
        LoginActivity.start(context);
    }

    @Override
    public String getName() {
        return "ComponentLoginImpl";
    }
}
