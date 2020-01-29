package edu.greenblitz.gblib.threading;

import edu.greenblitz.gblib.command.GBCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;

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
    public void end(boolean interrupted) {
        shouldStop = true;
        if (interrupted)
            threadable.atInterrupt();
        else
            threadable.atEnd();
    }

    @Override
    public void initialize() {
        shouldStop = false;
        myThread = new Thread(wrapper);
        threadable.atInit();
        myThread.start();
    }

    @Override
    public boolean isFinished() {
        return threadable.isFinished();
    }
}
