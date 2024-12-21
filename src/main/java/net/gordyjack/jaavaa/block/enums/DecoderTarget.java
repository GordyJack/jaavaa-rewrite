package net.gordyjack.jaavaa.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum DecoderTarget implements StringIdentifiable {
    LEFT("left"),
    FRONT("front"),
    RIGHT("right"),
    NONE("none");

    private final String name;

    DecoderTarget(final String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
