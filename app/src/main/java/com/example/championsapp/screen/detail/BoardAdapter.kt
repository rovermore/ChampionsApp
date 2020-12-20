package com.example.championsapp.screen.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.R
import com.example.championsapp.databinding.BoardItemBinding
import com.example.championsapp.model.Champion
import com.squareup.picasso.Picasso

class BoardAdapter(var championList: MutableList<Champion>,
                   var itemClicked: OnItemClicked): RecyclerView.Adapter<BoardAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return MyViewHolder(view)
    }

    interface OnItemClicked {
        fun itemClicked(champion: Champion, position: Int)
    }

    override fun getItemCount(): Int {
        if(championList.isNullOrEmpty()){
            return 0
        }
        return championList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(!championList.isNullOrEmpty()) {
            holder.bindView(championList[position])
        }
    }

    fun updateChampionList(championTeamList: MutableList<Champion>) {
            this.championList = championTeamList
            notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private val binding = BoardItemBinding.bind(view)

        init{
            view.setOnClickListener(this)
        }

        fun bindView(champion: Champion) {
            binding.apply {
                champion.image?.run {
                    Picasso.with(binding.root.context)
                        .load(champion.image)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championIV)
                }
            }
        }

        override fun onClick(v: View?) {
            championList[adapterPosition].let { itemClicked.itemClicked(it, adapterPosition) }
        }
    }
}