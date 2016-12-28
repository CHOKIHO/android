package com.ckh02.a.xpuzzle;

public interface DragDropPresenter {

    public boolean isDragDropEnabled();
    public void onDragStarted(DragSource source);
    public void onDropCompleted(DropTarget target, boolean success);

}
