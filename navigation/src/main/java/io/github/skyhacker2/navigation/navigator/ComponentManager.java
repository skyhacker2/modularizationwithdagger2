package io.github.skyhacker2.navigation.navigator;

import java.util.Map;

import io.github.skyhacker2.export_api.ComponentResolver;
import io.github.skyhacker2.export_api.IComponent;

public class ComponentManager {
    private static Map<Class<?>, ComponentResolver> sServicesMap;

    public static void init(Map<Class<?>, ComponentResolver> servicesMap) {
        sServicesMap = servicesMap;
    }

    public static <T> T component(Class<? extends IComponent> clasz) {
        try {
            return (T)sServicesMap.get(clasz).getComponent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
