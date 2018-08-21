package com.android.jsonregistercheck.list_expandable_height;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by user on 8/8/2018.
 */

public class ExpandableListViewHeight extends ListView {
    boolean expanded = false;

    public ExpandableListViewHeight(Context context) {
        super(context);
    }

    public ExpandableListViewHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableListViewHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExpanded(){
        return expanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (isExpanded()){

            int expandSpec = View.MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST
            );

            super.onMeasure(widthMeasureSpec,expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();

        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }


}
