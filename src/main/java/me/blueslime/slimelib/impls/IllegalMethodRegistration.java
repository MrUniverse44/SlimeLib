// Copyright (C) 2025 BlueSlime Development
package me.blueslime.slimelib.impls;

public class IllegalMethodRegistration extends Exception {
    public IllegalMethodRegistration(Exception origin) {
        super("Can't register a method with arguments because can't be invoked with the @Register annotation.", origin);
    }

    public IllegalMethodRegistration(String message) {
        super(message);
    }
}
