package com.zeel.interviewmatrix

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainAdapter(val context: Activity, val rowCount: Int) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    val selectedPositions = ArrayList<Int>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainRecyclerCard: CardView = itemView.findViewById(R.id.mainRecyclerCard);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.layout_main_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return rowCount * rowCount
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mainRecyclerCard.setCardBackgroundColor(
            context.resources.getColor(if (!selectedPositions.contains(position)) R.color.white else R.color.red)
        )

        holder.mainRecyclerCard.setOnClickListener {

            selectConnectedBoxes(position);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun selectConnectedBoxes(position: Int) {

        selectHorizontalLine(position)

        selectVerticalLine(position)

        selectForwardCross(position)

        selectBackwardCross(position)
//        notifyDataSetChanged();

    }

    private fun selectBackwardCross(position: Int) {
        var tempPos = position
        var tempPos2 = position

        var cells1 = (position % rowCount)
        var end = (position - cells1 + 1) + (rowCount * cells1)

        while (tempPos < (end) && tempPos < (itemCount)) {
            selectedPositions.add(tempPos)
            notifyItemChanged(tempPos)
            tempPos += rowCount - 1
        }

        var line = -1
        while (tempPos2 > 0 && line != (tempPos2 / rowCount)) {
            selectedPositions.add(tempPos2)
            notifyItemChanged(tempPos2)
            line = tempPos2 / rowCount
            tempPos2 -= rowCount - 1
        }

    }

    private fun selectForwardCross(position: Int) {

        var tempPos = position
        var tempPos2 = position

        var firstPos =
            position - (((position % rowCount)) * rowCount) - ((position % rowCount))
        while (tempPos >= firstPos) {
            selectedPositions.add(tempPos)
            notifyItemChanged(tempPos)
            tempPos -= rowCount + 1
        }

        var lastPos =
            position + ((rowCount - (position % rowCount)) * rowCount) + (rowCount - (position % rowCount))
        while (tempPos2 < lastPos) {
            selectedPositions.add(tempPos2)
            notifyItemChanged(tempPos2)
            tempPos2 += rowCount + 1
        }

    }

    private fun selectVerticalLine(position: Int) {
        var firstPost = (position % rowCount);
        while (firstPost < (rowCount * rowCount)) {
            selectedPositions.add(firstPost)
            notifyItemChanged(firstPost)
            firstPost += rowCount
        }

    }

    private fun selectHorizontalLine(position: Int) {

        var firstPost = position - (position % rowCount);
        var lastPos = firstPost + rowCount - 1;

        for (i in firstPost..lastPos) {
            selectedPositions.add(i)
            notifyItemChanged(i)
        }

    }

}
