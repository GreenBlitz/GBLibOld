package edu.greenblitz.gblib.command.base;

import edu.greenblitz.gblib.command.GBSubsystem;
import edu.greenblitz.gblib.hid.SmartJoystick;

public abstract class JoystickCommand<S extends GBSubsystem> extends SubsystemCommand<S> {
    protected SmartJoystick joystick;

    public JoystickCommand(S system, SmartJoystick joystick) {
        super(system);
        this.joystick = joystick;
    }
}
