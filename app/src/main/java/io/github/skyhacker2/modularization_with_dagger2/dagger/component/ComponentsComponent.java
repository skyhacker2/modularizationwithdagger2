package io.github.skyhacker2.modularization_with_dagger2.dagger.component;

import java.util.Map;

import dagger.Component;
import io.github.skyhacker2.core.dagger.scope.ComponentScope;
import io.github.skyhacker2.export_api.ComponentResolver;
import io.github.skyhacker2.navigation.dagger.module.ComponentModule;

@Component(modules = {ComponentModule.class})
@ComponentScope
public interface ComponentsComponent {
    Map<Class<?>, ComponentResolver> componentMap();
}
