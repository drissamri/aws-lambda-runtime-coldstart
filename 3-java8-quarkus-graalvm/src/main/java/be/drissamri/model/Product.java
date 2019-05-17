package be.drissamri.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Product {
    private String id;
    private String name;

    public Product(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
