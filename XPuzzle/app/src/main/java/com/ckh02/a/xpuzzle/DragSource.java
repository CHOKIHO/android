package com.ckh02.a.xpuzzle;

import android.content.ClipData;
import android.view.View;

public interface DragSource {
    public boolean allowDrag();
    public ClipData clipDataForDragDrop();
    public View dragDropView();
    public void onDragStarted();
    public void onDropCompleted(DropTarget target, boolean success);

}
