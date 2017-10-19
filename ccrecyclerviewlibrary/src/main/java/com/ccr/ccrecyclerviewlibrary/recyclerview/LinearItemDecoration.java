package com.ccr.ccrecyclerviewlibrary.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * recyclerView线性布局的分割线
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = 0x11;
    public static final int VERTICAL = 0x12;
    public static final int TYPE_COLOR = 0x21;
    public static final int TYPE_DRAWABLE = 0x22;

    private int mOrientation = VERTICAL;
    private int mDrawColor = -1;
    private int mLineHeight = -1;
    private int mHeadMargin = 0;
    private int mBottomMargin = 0;

    private Drawable mDrawable = null;

    private int mType = -1;

    public LinearItemDecoration(@ColorInt int color, int lineHeight) {
        mDrawColor = color;
        mLineHeight = lineHeight;
        mType = TYPE_COLOR;
    }

    public LinearItemDecoration(Context context, @DrawableRes int drawable) {
        mDrawable = context.getResources().getDrawable(drawable);
        mType = TYPE_DRAWABLE;
    }

    /**
     * 设置排列方向
     *
     * @param orientation
     */
    public LinearItemDecoration setOrientation(int orientation) {
        mOrientation = orientation;
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL) {
            if (mType == TYPE_COLOR) {
                drawVerticalByColor(c, parent);
            } else if (mType == TYPE_DRAWABLE) {
                drawVerticalByDrawable(c, parent);
            }
        } else {
            if (mType == TYPE_COLOR) {
                drawHorizontalByColor(c, parent);
            } else if (mType == TYPE_DRAWABLE) {
                drawHorizontalByDrawable(c, parent);
            }
        }
    }

    public LinearItemDecoration setHeadMargin(int margin) {
        mHeadMargin = margin;
        return this;
    }

    public LinearItemDecoration setBottomMargin(int margin) {
        mBottomMargin = margin;
        return this;
    }

    private void drawVerticalByDrawable(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (i == 0) {
                final int firstBottom = child.getTop() + params.topMargin;
                final int firstTop = firstBottom + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, firstTop, right, firstBottom);
                mDrawable.draw(c);
            }
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mLineHeight;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawVerticalByColor(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        Paint paint = new Paint();
        paint.setColor(mDrawColor);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (i == 0) {
                final int firstBottom = child.getTop() + params.topMargin;
                final int firstTop = firstBottom + mLineHeight;
                c.drawRect(left, firstTop, right, firstBottom, paint);
            }
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mLineHeight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawHorizontalByColor(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        Paint paint = new Paint();
        paint.setColor(mDrawColor);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (i == 0) {
                final int firstRight = child.getLeft() + params.leftMargin;
                final int firstLeft = firstRight + mLineHeight;
                c.drawRect(firstLeft, top, firstRight, bottom, paint);
            }
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mLineHeight;
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawHorizontalByDrawable(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            if (i == 0) {
                final int firstRight = child.getLeft() + params.leftMargin;
                final int firstLeft = firstRight + mLineHeight;
                mDrawable.setBounds(firstLeft, top, firstRight, bottom);
                mDrawable.draw(c);
            }
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mLineHeight;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        boolean isLast = parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1;
        boolean isFirst = parent.getChildAdapterPosition(view) == 0;
        int offests = 0;
        if (mType == TYPE_COLOR) {
            offests = mLineHeight;
        }
        if (mOrientation == VERTICAL) {
            if (isFirst) {
                outRect.set(0, offests + mHeadMargin, 0, offests);
            } else if (isLast) {
                outRect.set(0, 0, 0, offests + mBottomMargin);
            } else {
                outRect.set(0, 0, 0, offests);
            }
        } else {
            if (isFirst) {
                outRect.set(offests + mHeadMargin, 0, offests, 0);
            } else if (isLast) {
                outRect.set(0, 0, offests + mBottomMargin, 0);
            } else {
                outRect.set(0, 0, offests, 0);
            }
        }
    }

}
