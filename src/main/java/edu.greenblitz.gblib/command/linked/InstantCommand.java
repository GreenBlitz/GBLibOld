package edu.greenblitz.gblib.command.linked;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class InstantCommand extends LinkedCommand {

    public InstantCommand(LinkedCommand next) {
        super(next);
    }

    public InstantCommand() {
        super();
    }

    public InstantCommand(String name) {
        super(name);
    }

    public InstantCommand(double timeout) {
        super(timeout);
    }

    public InstantCommand(Subsystem subsystem) {
        super(subsystem);
    }

    public InstantCommand(String name, Subsystem subsystem) {
        super(name, subsystem);
    }

    public InstantCommand(double timeout, Subsystem subsystem) {
        super(timeout, subsystem);
    }

    public InstantCommand(String name, double timeout) {
        super(name, timeout);
    }

    public InstantCommand(String name, double timeout, Subsystem subsystem) {
        super(name, timeout, subsystem);
    }

    public InstantCommand(String name, LinkedCommand next) {
        super(name, next);
    }

    public InstantCommand(double timeout, LinkedCommand next) {
        super(timeout, next);
    }

    public InstantCommand(Subsystem subsystem, LinkedCommand next) {
        super(subsystem, next);
    }

    public InstantCommand(String name, Subsystem subsystem, LinkedCommand next) {
        super(name, subsystem, next);
    }

    public InstantCommand(double timeout, Subsystem subsystem, LinkedCommand next) {
        super(timeout, subsystem, next);
    }

    public InstantCommand(String name, double timeout, LinkedCommand next) {
        super(name, timeout, next);
    }

    public InstantCommand(String name, double timeout, Subsystem subsystem, LinkedCommand next) {
        super(name, timeout, subsystem, next);
    }

    @Override
    protected final void execute(){}

    @Override
    protected final void beforeNextLink() {}

    @Override
    protected final void atInterrupt() { }

    @Override
    protected final void atInit() {
        performInstantAction();
    }

    public abstract void performInstantAction();

    @Override
    protected final boolean isFinished() {
        return true;
    }
}
