package com.shiftf6.workshop2.refactoring;

import java.util.ArrayList;
import java.util.List;

public class LoadingBayRepository {

    private final List<Integer> originalBays = new ArrayList<>();
    private ArrayList<Integer> temporaryBays;

    public void addLoadingBay(Integer size) {
        originalBays.add(size);
    }

    public void afterBaysSetInitialize() {
        this.temporaryBays = new ArrayList<>(this.originalBays);
    }

    public void loadBay(int index, int size) {
        this.temporaryBays.set(index, size);
    }

    public List<Integer> allBays() {
        return this.temporaryBays;
    }

    public void deleteAllTemporaryLoadingBays() {
        this.temporaryBays = new ArrayList<>();
    }

    public void resetTempLoadingBaysFromOriginals() {
        this.temporaryBays = new ArrayList<>(this.originalBays);
    }

    public boolean isEmpty(int index) {
        return originalBays.get(index) == temporaryBays.get(index);
    }
}
