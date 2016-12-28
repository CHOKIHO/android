package com.ckh02.a.xpuzzle;

import android.view.View;

public interface DropTarget {

    public boolean allowDrop(DragSource source);
    public View dragDropView();
    public void onDrop(DragSource source);
    public void onDragEnter(DragSource source);
    public void onDragExit(DragSource source);

}
