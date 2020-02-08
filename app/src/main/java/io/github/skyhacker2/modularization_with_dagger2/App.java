package io.github.skyhacker2.modularization_with_dagger2;

import android.app.Application;

import io.github.skyhacker2.export_api.component_account.ComponentAccount;
import io.github.skyhacker2.modularization_with_dagger2.dagger.component.DaggerComponentsComponent;
import io.github.skyhacker2.navigation.dagger.module.ComponentModule;
import io.github.skyhacker2.navigation.navigator.ComponentManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initComponentDependencies();

        initComponents();
    }

    private void initComponentDependencies() {
        ComponentManager.init(DaggerComponentsComponent.builder()
                .componentModule(new ComponentModule()).build()
                .componentMap());
    }

    private void initComponents() {

        ComponentAccount componentAccount = ComponentManager.component(ComponentAccount.class);
        if (componentAccount != null) {
            componentAccount.init(this);
        }

    }
}
