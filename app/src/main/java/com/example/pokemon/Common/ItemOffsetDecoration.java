package com.example.pokemon.Common;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//Decorative items in Recycler View
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int itemoffeset;

    public ItemOffsetDecoration(int itemoffeset) {
        this.itemoffeset = itemoffeset;
    }
    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int dimens){
        this(context.getResources().getDimensionPixelSize(dimens));
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
        super.getItemOffsets(outRect,view,parent,state);
        outRect.set(itemoffeset,itemoffeset,itemoffeset,itemoffeset);
    }

}
