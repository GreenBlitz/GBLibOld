package edu.greenblitz.gblib.command;

import edu.greenblitz.gblib.command.base.GBCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ResetCommands extends GBCommand {

    @Override
    protected void atInit() {
        Scheduler.getInstance().removeAll();
    }

    @Override
    protected void atEnd() {

    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
