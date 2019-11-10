package edu.greenblitz.gblib.command.linked;

import edu.wpi.first.wpilibj.command.Command;

public class MetaLinkedCommand extends LinkedCommand {
    public MetaLinkedCommand(Command... commands){
        super(LinkedCommandListBuilder.generate(commands));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
