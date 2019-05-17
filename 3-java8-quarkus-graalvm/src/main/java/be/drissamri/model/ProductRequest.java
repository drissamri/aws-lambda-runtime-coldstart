package be.drissamri.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ProductRequest {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
