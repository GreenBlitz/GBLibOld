package edu.greenblitz.gblib.command.linked;

import edu.wpi.first.wpilibj.command.Command;

import java.util.LinkedList;
import java.util.List;

public class LinkedCommandListBuilder {

    public List<LinkedCommand> commands;

    public LinkedCommandListBuilder(){
        commands = new LinkedList<>();
    }

    public LinkedCommandListBuilder add(LinkedCommand command){
        commands.get(commands.size() - 1).setNext(command);
        commands.add(command);
        return this;
    }

    public LinkedCommand build(){
        return commands.get(0);
    }

    public static LinkedCommand generate(Command... commands){
        LinkedCommandListBuilder builder = new LinkedCommandListBuilder();
        for(Command value : commands){
            builder.add(new LinkedCommandWrapper(value));
        }
        return builder.build();
    }

}
