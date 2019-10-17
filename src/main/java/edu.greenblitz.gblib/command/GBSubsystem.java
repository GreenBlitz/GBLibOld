package edu.greenblitz.gblib.command;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class GBSubsystem extends Subsystem {
    public GBSubsystem(String name) {
        super(name);
    }

    public GBSubsystem() {
    }

    public boolean isAtDefaultState() {
        return getDefaultCommand() == getCurrentCommand();
    }
}
