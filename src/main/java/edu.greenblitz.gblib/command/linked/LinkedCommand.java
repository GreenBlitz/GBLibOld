package edu.greenblitz.gblib.command.linked;

import edu.greenblitz.gblib.command.base.GBCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class LinkedCommand extends GBCommand {

    private LinkedCommand next;

    public LinkedCommand(LinkedCommand next) {
        this.next = next;
    }

    public LinkedCommand() {
    }

    public LinkedCommand(String name) {
        super(name);
    }

    public LinkedCommand(double timeout) {
        super(timeout);
    }

    public LinkedCommand(Subsystem subsystem) {
        super(subsystem);
    }

    public LinkedCommand(String name, Subsystem subsystem) {
        super(name, subsystem);
    }

    public LinkedCommand(double timeout, Subsystem subsystem) {
        super(timeout, subsystem);
    }

    public LinkedCommand(String name, double timeout) {
        super(name, timeout);
    }

    public LinkedCommand(String name, double timeout, Subsystem subsystem) {
        super(name, timeout, subsystem);
    }

    public LinkedCommand(String name, LinkedCommand next) {
        super(name);
        this.next = next;
    }

    public LinkedCommand(double timeout, LinkedCommand next) {
        super(timeout);
        this.next = next;
    }

    public LinkedCommand(Subsystem subsystem, LinkedCommand next) {
        super(subsystem);
        this.next = next;
    }

    public LinkedCommand(String name, Subsystem subsystem, LinkedCommand next) {
        super(name, subsystem);
        this.next = next;
    }

    public LinkedCommand(double timeout, Subsystem subsystem, LinkedCommand next) {
        super(timeout, subsystem);
        this.next = next;
    }

    public LinkedCommand(String name, double timeout, LinkedCommand next) {
        super(name, timeout);
        this.next = next;
    }

    public LinkedCommand(String name, double timeout, Subsystem subsystem, LinkedCommand next) {
        super(name, timeout, subsystem);
        this.next = next;
    }

    public void setNext(LinkedCommand link){
        this.next = link;
    }

    @Override
    protected final void atEnd() {
        beforeNextLink();
        if (!(interrupted || next == null)){
            System.out.println("requires");
            next.requires(getRequirements());
            next.start();
        }
    }

    @Override
    public void start(){
        System.out.println("Started");
        super.start();
    }

    /**
     * executed when <code>atEnd</code> is called
     */
    protected void beforeNextLink() {}
}
