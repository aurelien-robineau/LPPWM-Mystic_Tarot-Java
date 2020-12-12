package com.lpweb.mystic_tarot.gui.components;

public interface Validable {
    /**
     * Changes the GUI component to a unvalid state.
     */
    public void setError();

    /**
     * Changes the GUI component to a valid state.
     */
    public void removeError();
}
