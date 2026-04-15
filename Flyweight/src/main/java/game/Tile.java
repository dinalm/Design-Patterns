package main.java.game;

public abstract class Tile {

    /** Intrinsic: one-character symbol used for console display. */
    public abstract char getCharacter();

    /** Intrinsic: string key used to locate the shared main.java.game.TileGraphic. */
    public abstract String getType();

    /** Intrinsic: behaviour description (console only). */
    public abstract void action();
}