package edu.greenblitz.gblib.gears;

public class GearDependentValue<T> {

    protected T whenPower;
    protected T whenSpeed;

    public GearDependentValue(T whenPower, T whenSpeed){
        this.whenPower = whenPower;
        this.whenSpeed = whenSpeed;
    }

    public T getValue(Gear gear){
        return gear.isSpeed() ? whenSpeed : whenPower;
    }

    public T getValue(){
        return getValue(GlobalGearContainer.getInstance().getGear());
    }

    public void setValue(Gear gear, T val){
        if (gear.isSpeed())
            this.whenSpeed = val;
        else
            this.whenPower = val;
    }

}
