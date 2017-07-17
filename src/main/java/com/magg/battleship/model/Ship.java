package com.magg.battleship.model;

import java.util.HashMap;
import java.util.Map;

import com.magg.battleship.model.weapon.Weapon;

public class Ship {

    private boolean destroyed;
    private final Map<Position, Integer> positions;

    public Ship(ShipType type, int width, int height, Position position) {
        int resiliency = 0;
        switch (type) {
            case Q:
                resiliency = 2;
                break;
            case P:
                resiliency = 1;
                break;
        }

        this.positions = new HashMap<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Position shipPosition = Position.create(position, i, j);
                this.positions.put(shipPosition, resiliency);
            }
        }
    }

    public boolean canBeTargetted(Weapon w) {
        return this.positions.containsKey(w.getTarget());
    }

    public boolean damage(Weapon w) {
        if (isDestroyed()) {
            return false;
        }

        if (!canBeTargetted(w)) {
            return false;
        }

        int positionResiliency = this.positions.get(w.getTarget());
        positionResiliency--;
        if (positionResiliency == 0) {
            this.positions.remove(w.getTarget());
        } else {
            this.positions.put(w.getTarget(), positionResiliency);
        }

        if (this.positions.isEmpty()) {
            destroyed = true;
        }

        return true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
