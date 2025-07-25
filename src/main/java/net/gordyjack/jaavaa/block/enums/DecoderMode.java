package net.gordyjack.jaavaa.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum DecoderMode implements StringIdentifiable {
    DEMUX("demux"),
    DECODE("decode");

    private final String name;

    DecoderMode(final String name) {
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
