package System;

import java.io.File;

public interface FileState{
    public void start(File target);
    public void process(float percent) throws InterruptedException;
    public void end(File target, boolean result);
}
