package edu.greenblitz.gblib.gears;

public class GlobalGearContainer {

    private Gear gear;

    private static GlobalGearContainer instance;

    public static GlobalGearContainer getInstance(){
        if (instance == null){
            instance = new GlobalGearContainer();
        }
        return instance;
    }

    private GlobalGearContainer(){
        gear = Gear.SPEED;
    }

    public void setGear(Gear value){
        gear = value;
    }

    public Gear getGear(){
        return gear.isSpeed() ? Gear.SPEED : Gear.POWER;
    }

}
