package com.ckh02.a.xpuzzle;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageCell extends ImageView implements DragSource, DropTarget
{
    public boolean mEmpty = true;
    public int mCellNumber = -1;
    public GridView mGrid;


public ImageCell(Context context) {
	super (context);
}
public ImageCell(Context context, AttributeSet attrs) {
	super (context, attrs);
}
public ImageCell(Context context, AttributeSet attrs, int style) {
	super (context, attrs, style);
}

public boolean allowDrag () {
    // There is something to drag if the cell is not empty.
    return !mEmpty;
}

public ClipData clipDataForDragDrop () {
    return null;
    // eventually, create something with the id of the image and the cell number
}

public View dragDropView () {
    return this;
}

public void onDragStarted () {
    // Change the cell a bit so the user can tell which cell they started with.
    if (mCellNumber >= 0) {
       setColorFilter (R.color.cell_nearly_empty);
       invalidate ();
       //setBackgroundResource (R.color.cell_empty);
    }
}


public void onDropCompleted (DropTarget target, boolean success) {
    // Undo what we did in onDragStarted
    if (mCellNumber >= 0) {
       clearColorFilter ();
       invalidate ();
    }

    // If the drop succeeds, the image has moved elsewhere. 
    // So clear the image cell.

    if (success) {
       Log.d (ImageActivity.LOG_NAME, "ImageCell.onDropCompleted - clearing source: " + mCellNumber);
       mEmpty = true;
       if (mCellNumber >= 0) {
          int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
          setBackgroundResource (bg);
          setImageDrawable (null);
       } else {
         // If the cell number is negative, it means we are interacting with a free-standing
         // image cell. There is one of those. It is the place where an image is added when
         // the user clicks "add image".
         // At the conclusion of a drop, clear it.
         setImageResource (0);
       }
    } else {
      // On the failure case, reset the background color in case it is still set to the hover color.
      if (mCellNumber >=0) {
         int bg2 = mEmpty ? R.color.cell_empty : R.color.cell_filled;
         setBackgroundResource (bg2);
      }
    }

}

/**
 */
// DropTarget interface methods

/**
 * Return true if the DropTarget allows objects to be dropped on it.
 * Disallow drop if the source object is the same ImageCell.
 * Allow a drop of the ImageCell is empty
 * 
 * @param source DragSource where the drag started
 * @return boolean True if the drop will be accepted, false otherwise.
 */

public boolean allowDrop (DragSource source) {
    // Do not allow a drop if the DragSource is the same cell.
    if (source == this) return false;

    // An ImageCell accepts a drop if it is empty and if it is part of a grid.
    // A free-standing ImageCell does not accept drops.
    return mEmpty  && (mCellNumber >= 0);
}

/**
 * Handle an object being dropped on the DropTarget
 * 
 * @param source DragSource where the drag started
 */

public void onDrop (DragSource source) {
    Log.d (ImageActivity.LOG_NAME, "ImageCell.onDrop: " + mCellNumber + " source: " + source);
        
    // Mark the cell so it is no longer empty.
    mEmpty = false;
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundResource (bg);

    // The view being dragged does not actually change its parent and switch over to the ImageCell.
    // What we do is copy the drawable from the source view.
    ImageView sourceView = (ImageView) source.dragDropView ();
    Drawable d = sourceView.getDrawable ();
    if (d != null) {
       this.setImageDrawable (d);
       this.invalidate ();
    } else {
      Log.e (ImageActivity.LOG_NAME, "ImageCell.onDrop. Null Drawable");
    }

    // toast ("onDrop cell " + mCellNumber);
}

/**
 * React to a dragged object entering into the view of the DropTarget.
 */    

public void onDragEnter (DragSource source) {
    //Log.d (ImageActivity.LOG_NAME, "ImageCell.onDragEnter: " + mCellNumber);
    if (mCellNumber < 0) return;
    int bg = mEmpty ? R.color.cell_empty_hover : R.color.cell_filled_hover;
    setBackgroundResource (bg);
}

/**
 * React to a dragged object leaving the view of the DropTarget.
 */    

public void onDragExit (DragSource source) {
    if (mCellNumber < 0) return;
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundResource (bg);
}

/**
 */
// Other Methods

/**
 * Return true if this cell is empty.
 * If it is, it means that it will accept dropped views.
 * It also means that there is nothing to drag.
 * 
 * @return boolean
 */

public boolean isEmpty ()
{
    return mEmpty;
}

/**
 * Call this view's onClick listener. Return true if it was called.
 * Clicks are ignored if the cell is empty.
 * 
 * @return boolean
 */

public boolean performClick ()
{
    if (!mEmpty) return super.performClick ();
    return false;
}

/**
 * Call this view's onLongClick listener. Return true if it was called.
 * Clicks are ignored if the cell is empty.
 * 
 * @return boolean
 */

public boolean performLongClick ()
{
    if (!mEmpty) return super.performLongClick ();
    return false;
}

} // end ImageCell
