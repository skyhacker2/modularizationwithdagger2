package io.github.skyhacker2.navigation.dagger.module;

import java.util.HashMap;
import java.util.Map;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import io.github.skyhacker2.core.dagger.scope.ComponentScope;
import io.github.skyhacker2.export_api.ComponentResolver;
import io.github.skyhacker2.export_api.component_account.ComponentAccount;
import io.github.skyhacker2.export_api.component_login.ComponentLogin;
import io.github.skyhacker2.export_api.component_home.ComponentHome;

@Module
public class ComponentModule {

    Map<String, Object> componentObjectMap = new HashMap<>();

    @Provides
    @ComponentScope
    @IntoMap
    @ClassKey(ComponentLogin.class)
    public ComponentResolver provideLoginComponentResolver() {
        return new ComponentResolver<ComponentLogin>() {
            @Override
            public ComponentLogin getComponent() {
                return ComponentModule.this.getComponent("io.github.skyhacker2.component_a.component.ComponentLoginImpl");
            }
        };
    }

    @Provides
    @ComponentScope
    @IntoMap
    @ClassKey(ComponentHome.class)
    public ComponentResolver provideHomeComponentResolver() {
        return new ComponentResolver<ComponentHome>() {
            @Override
            public ComponentHome getComponent() {
                return ComponentModule.this.getComponent("io.github.skyhacker2.home_component.component.ComponentHomeImpl");
            }
        };
    }

    @Provides
    @ComponentScope
    @IntoMap
    @ClassKey(ComponentAccount.class)
    public ComponentResolver provideAccountComponentResolver() {
        return new ComponentResolver<ComponentAccount>() {
            @Override
            public ComponentAccount getComponent() {
                return ComponentModule.this.getComponent("io.github.skyhacker2.component_account.ComponentAccountImpl");
            }
        };
    }


    private <T> T getComponent(String className) {
        try {
            if (componentObjectMap.get(className) != null) {
                return (T) componentObjectMap.get(className);
            }
            Class clasz = Class.forName(className);
            T o = (T) clasz.newInstance();
            componentObjectMap.put(className, o);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
