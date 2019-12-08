package edu.greenblitz.gblib.command.base;

import com.revrobotics.CANSparkMaxFrames;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.time.Clock;
import java.util.Vector;

public abstract class GBCommand extends Command {
    protected static final Logger logger = LogManager.getLogger(GBCommand.class);

    protected static final Field requirements;
    protected static final Field requirementsSet;

    protected boolean interrupted = false;

    static {
        try {
            Class WPISet = Class.forName("edu.wpi.first.wpilibj.command.Set");
            requirements = WPISet.getDeclaredField("m_set");
            requirements.setAccessible(true);
            requirementsSet = Command.class.getDeclaredField("m_requirements");
            requirementsSet.setAccessible(true);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<Subsystem> getRequirements(){
        try {
            return (Vector<Subsystem>) GBCommand.requirements.get(GBCommand.requirementsSet.get(this));
        } catch (IllegalAccessException e) {
            return null;
        }
    }


    public void addRequirements(Iterable<? extends Subsystem> systems) {
        for (Subsystem s : systems)
            requires(s);
    }

    public void requires(Subsystem... requirements) {
        for (Subsystem requirement : requirements) {
            requires(requirement);
        }
    }

    public void requires(Iterable<Subsystem> requirements) {
        System.out.println("requires test print");
        for (Subsystem requirement : requirements) {
            requires(requirement);
        }
    }

    public GBCommand() {
    }

    public GBCommand(String name) {
        super(name);
    }

    public GBCommand(double timeout) {
        super(timeout);
    }

    public GBCommand(Subsystem subsystem) {
        super(subsystem);
    }

    public GBCommand(String name, Subsystem subsystem) {
        super(name, subsystem);
    }

    public GBCommand(double timeout, Subsystem subsystem) {
        super(timeout, subsystem);
    }

    public GBCommand(String name, double timeout) {
        super(name, timeout);
    }

    public GBCommand(String name, double timeout, Subsystem subsystem) {
        super(name, timeout, subsystem);
    }

    @Override
    protected final void initialize() {
        logger.debug("initializing command {}...", getName());
        atInit();
        logger.debug("command {} has initialized!", getName());
    }

    @Override
    protected final void end() {
        logger.debug("ending command {}...", getName());
        atEnd();
        logger.debug("command {} has ended!", getName());
    }

    @Override
    protected final void interrupted() {
        logger.debug("interrupting command {}", getName());
        interrupted = true;
        atInterrupt();
        logger.debug("command {} was interrupted", getName());
    }

    protected void atInterrupt() {
        atEnd();
    }

    protected void atEnd() {
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    protected void atInit() {
    }
}
