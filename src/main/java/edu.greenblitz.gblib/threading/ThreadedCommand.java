package edu.greenblitz.gblib.threading;

import edu.greenblitz.gblib.command.base.GBCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ThreadedCommand extends GBCommand {

    private Thread myThread;
    private IThreadable threadable;
    private Runnable wrapper;
    private boolean shouldStop;

    public ThreadedCommand(IThreadable func, Subsystem... req){

        threadable = func;
        shouldStop = false;

        wrapper = () -> {
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
        shouldStop = true;
        threadable.atEnd();
    }

    @Override
    protected void atInit() {
        shouldStop = false;
        myThread = new Thread(wrapper);
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
