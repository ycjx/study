package com.yxj.designPatterns;

/**
 * @author:ycjx
 * @descriptio  访问者模式
 * @create:2020-04-04 16:02
 */
public class VisitorPattern {

    public static void main(String[] args) {
        CarComponent car = new Car();
        Mechanic mechanic = new QualifiedMechanic();
        car.accept(mechanic);
        Mechanic nonqualifiedMechanic = new NonQualifiedMechanic();
        car.accept(nonqualifiedMechanic);
    }



}

// visitor
interface Mechanic {
    public void visit(CarComponent element);
    public String getName();
}

class QualifiedMechanic implements Mechanic {

    @Override
    public void visit(CarComponent element) {
        element.setBroken(true);
    }

    @Override
    public String getName() {
        return "qualified";
    }
}

class NonQualifiedMechanic implements Mechanic {

    @Override
    public void visit(CarComponent element) {
        element.setBroken(true);
    }

    @Override
    public String getName() {
        return "unqualified";
    }
}

// visitable
abstract class CarComponent {
    protected boolean broken;

    public abstract void accept(Mechanic mechanic);

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public boolean isBroken() {
        return this.broken;
    }
}

class Car extends CarComponent {

    private boolean broken = false;
    private CarComponent[] components;

    public Car() {
        components = new CarComponent[] {
                new Wheels(), new Engine(), new Brake()
        };
    }

    @Override
    public void accept(Mechanic mechanic) {
        this.broken = false;
        if (mechanic.getName().equals("qualified")) {
            int i = 0;
            while (i < components.length && this.broken == false) {
                CarComponent component = components[i];
                mechanic.visit(component);
                this.broken = component.isBroken();
                i++;
            }
        }
        // if mechanic isn't qualified, we suppose that
        // he isn't able to see breakdowns and so
        // he considers the car as no broken
        // (even if the car is broken)
    }

    @Override
    public boolean isBroken() {
        return this.broken;
    }
}

class Wheels extends CarComponent {

    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}

class Engine extends CarComponent {

    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}

class Brake extends CarComponent {

    @Override
    public void accept(Mechanic mechanic) {
        mechanic.visit(this);
    }
}