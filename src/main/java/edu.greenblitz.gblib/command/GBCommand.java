package edu.greenblitz.gblib.command;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class GBCommand implements Command {

    protected Set<Subsystem> systems;

    public GBCommand(){
        this.systems = new HashSet<>();
    }

    public GBCommand(Subsystem... systems){
        this.systems = new HashSet<>();
        this.systems.addAll(Arrays.asList(systems));
    }

    public void require(Subsystem sys){
        this.systems.add(sys);
    }

    public boolean doesRequire(Subsystem sys){
        return systems.contains(sys);
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return systems;
    }
}
