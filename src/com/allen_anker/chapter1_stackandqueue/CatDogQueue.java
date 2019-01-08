package com.allen_anker.chapter1_stackandqueue;

import java.util.LinkedList;
import java.util.Queue;

public class CatDogQueue {
    private Queue<PetHolder> catQueue;
    private Queue<PetHolder> dogQueue;
    private int count;

    public CatDogQueue() {
        count = 0;
        catQueue = new LinkedList<>();
        dogQueue = new LinkedList<>();
    }

    public void add(Pet pet) {
        if (pet.getPetType().equals("dog")) {
            dogQueue.offer(new PetHolder(pet, count++));
        } else if (pet.getPetType().equals("cat")) {
            catQueue.offer(new PetHolder(pet, count++));
        } else {
            throw new RuntimeException("pet type not found");
        }
    }

    public Pet pollAll() {
        if (this.isEmpty()) {
            return null;
        }
        if (dogQueue.isEmpty()) {
            return catQueue.poll().getPet();
        }
        if (catQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        }

        if (dogQueue.peek().getTimestamp() < catQueue.peek().getTimestamp()) {
            return dogQueue.poll().getPet();
        } else {
            return catQueue.poll().getPet();
        }
    }

    public Dog pollDog() {
        if (dogQueue.isEmpty()) {
            return null;
        }

        return (Dog) dogQueue.poll().getPet();
    }

    public Cat pollCat() {
        if (catQueue.isEmpty()) {
            return null;
        }

        return (Cat) catQueue.poll().getPet();
    }

    public boolean isEmpty() {
        return catQueue.isEmpty() && dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public static void main(String[] args) {
        CatDogQueue petQueue = new CatDogQueue();
        petQueue.add(new Dog());
        petQueue.add(new Dog());
        petQueue.add(new Cat());
        petQueue.add(new Dog());
        petQueue.add(new Cat());
        System.out.println(petQueue.pollAll());
        System.out.println(petQueue.pollCat());
        while (!petQueue.isEmpty()) {
            System.out.println(petQueue.pollAll());
        }
    }
}

class PetHolder {
    private Pet pet;
    private long timestamp;

    public PetHolder(Pet pet, long timestamp) {
        this.pet = pet;
        this.timestamp = timestamp;
    }

    public Pet getPet() {
        return pet;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}
