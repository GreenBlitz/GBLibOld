package edu.greenblitz.gblib.command.linked;

import edu.greenblitz.gblib.command.base.GBCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.Vector;

public class LinkedCommandWrapper extends LinkedCommand {

    private Command command;

    public LinkedCommandWrapper(Command command){
        this.command = command;

    }

    @Override
    public Vector<Subsystem> getRequirements(){
        try {
            return (Vector<Subsystem>) GBCommand.requirements.get(GBCommand.requirementsSet.get(command));
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    public LinkedCommandWrapper(Command command, LinkedCommand next){
        super(next);
        this.command = command;
    }

    @Override
    protected void atInit(){
        this.setInterruptible(command.isInterruptible());
        command.start();
    }

    @Override
    protected boolean isFinished() {
        return command.isCompleted();
    }

    @Override
    public synchronized boolean doesRequire(Subsystem subsystem) {
        return command.doesRequire(subsystem);
    }

}
