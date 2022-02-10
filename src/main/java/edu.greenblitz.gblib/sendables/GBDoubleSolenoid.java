package edu.greenblitz.gblib.sendables;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class GBDoubleSolenoid extends DoubleSolenoid {
    public GBDoubleSolenoid(PneumaticsModuleType controlModule, int forwardChannel, int reverseChannel) {
        super(controlModule, forwardChannel, reverseChannel);
    }


    private void setState(boolean newState) {
        set(newState ? Value.kForward : Value.kReverse);
    }

    private boolean getState() {
        return get() == Value.kForward;
    }
}
