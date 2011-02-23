package msu.cse.turtlegraphics;

import java.util.Observer;

public interface CommandListObserver
{
    void handleCommandListChanged(ObservableCommandList list);
}
