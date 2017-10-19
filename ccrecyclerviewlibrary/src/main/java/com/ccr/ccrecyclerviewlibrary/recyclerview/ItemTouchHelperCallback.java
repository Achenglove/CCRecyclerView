package com.ccr.ccrecyclerviewlibrary.recyclerview;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * ItemTouchHelper是一个强大的工具，它处理好了关于在RecyclerView上添加拖动排序与滑动删除的所有事情。
 * 它是RecyclerView.ItemDecoration的子类，也就是说它可以轻易的添加到几乎所有的LayoutManager和Adapter中。
 * 它还可以和现有的item动画一起工作，提供受类型限制的拖放动画等等，
 * 要使用ItemTouchHelper，你需要创建一个ItemTouchHelper.Callback。这个接口可以让你监听“move”与 “swipe”事件
 * <p>
 * Created by Sunflower on 2015/8/17.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public final float ALPHA_FULL = 1.0f;
    /**
     * 是否允许长按拖动
     */
    private boolean longPressDragEnabled;
    /**
     * 是否允许拖动操作
     */
    private boolean itemViewSwipeEnabled;
    /**
     * 上下为拖动（drag）----拖动方向
     */
    private int dragFlags;
    /**
     * 左右为滑动（swipe）--滑动方向
     */
    private int swipeFlags;
    private QuickAdapter mAdapter;


    public ItemTouchHelperCallback(Builder builder) {
        mAdapter = builder.adapter;
        longPressDragEnabled = builder.longPressDragEnabled;
        itemViewSwipeEnabled = builder.itemViewSwipeEnabled;
        dragFlags = builder.dragFlags;
        swipeFlags = builder.swipeFlags;
    }

    /**
     * 要支持长按RecyclerView item进入拖动操作，你必须在isLongPressDragEnabled()方法中返回true
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return longPressDragEnabled;
    }

    /**
     * 要在view任意位置触摸事件发生时启用滑动操作，则直接在isItemViewSwipeEnabled()中返回true就可以了
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return itemViewSwipeEnabled;
    }


    /**
     * 需要重写getMovementFlags()方法来指定可以支持的拖放和滑动的方向
     * 使用helperItemTouchHelper.makeMovementFlags(int, int)来构造返回的flag
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        // move item in `fromPos` to `toPos` in adapter.
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;// true if moved, false otherwise
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
// remove from adapter
        mAdapter.remove(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Fade out the view as it is swiped out of the parent's bounds
            final float alpha = ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }


    /**
     * 在每次View Holder的状态变成拖拽 (ACTION_STATE_DRAG) 或者 滑动 (ACTION_STATE_SWIPE)的时候被调用
     * 这是把你的item view变成激活状态的最佳地点。
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            // Let the view holder know that this item is being moved or dragged
//            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 在一个view被拖拽然后被放开的时候被调用，同时也会在滑动被取消或者完成ACTION_STATE_IDLE)的时候被调用。
     * 这里是恢复item view idle状态的典型地方。
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        // Tell the view holder it's time to restore the idle state
//        viewHolder.itemView.setBackgroundColor(0);
    }

    public static class Builder {
        /**
         * 是否允许长按拖动
         */
        private boolean longPressDragEnabled;
        /**
         * 是否允许拖动操作
         */
        private boolean itemViewSwipeEnabled;
        /**
         * 上下为拖动（drag）----拖动方向
         */
        private int dragFlags;
        /**
         * 左右为滑动（swipe）--滑动方向
         */
        private int swipeFlags;
        private QuickAdapter adapter;

        public Builder(QuickAdapter adapter) {
            this.adapter = adapter;
            longPressDragEnabled = false;
            itemViewSwipeEnabled = false;
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }

        public Builder setLongPressDragEnabled(boolean isLongPressDragEnabled) {
            this.longPressDragEnabled = isLongPressDragEnabled;
            return this;
        }

        public Builder setItemViewSwipeEnabled(boolean itemViewSwipeEnabled) {
            this.itemViewSwipeEnabled = itemViewSwipeEnabled;
            return this;
        }

        public Builder setDragFlags(int dragFlags) {
            this.dragFlags = dragFlags;
            return this;
        }

        public Builder setSwipeFlags(int swipeFlags) {
            this.swipeFlags = swipeFlags;
            return this;
        }

        public ItemTouchHelperCallback build() {
            return new ItemTouchHelperCallback(this);

        }
    }


}
