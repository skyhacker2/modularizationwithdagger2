package io.github.skyhacker2.export_api;

public interface ComponentResolver<T extends IComponent> {
    T getComponent();
}
