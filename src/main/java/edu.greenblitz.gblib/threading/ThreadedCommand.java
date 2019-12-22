package edu.greenblitz.gblib.threading;

import edu.greenblitz.gblib.command.base.GBCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ThreadedCommand extends GBCommand {

    private Thread myThread;
    private IThreadable threadable;
    private boolean shouldStop;

    public ThreadedCommand(IThreadable func, Subsystem... req){

        threadable = func;
        shouldStop = false;

        Runnable wrapper = () -> {
            while (!shouldStop && !isFinished()){
                threadable.run();
            }
        };

        myThread = new Thread(wrapper);

        for (Subsystem sys : req){
            requires(sys);
        }
    }

    @Override
    protected void atEnd() {
        threadable.atEnd();
    }

    @Override
    protected void atInit() {
        threadable.atInit();
        myThread.start();
    }

    @Override
    protected void atInterrupt(){
        shouldStop = true;
        threadable.atInterrupt();
    }

    @Override
    protected boolean isFinished() {
        return threadable.isFinished();
    }
}
