package com.example.championsapp.screen.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.R
import com.example.championsapp.databinding.TeamItemBinding
import com.example.championsapp.model.Champion
import com.example.championsapp.model.ChampionTeam
import com.example.championsapp.screen.detail.BoardAdapter

class MainAdapter (var championTeamList: MutableList<ChampionTeam>?,
                   val context: Context
) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(championTeamList.isNullOrEmpty()){
            return 0
        }
        return championTeamList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(!championTeamList.isNullOrEmpty()) {
            holder.bindView(championTeamList!![position])
        }
    }

    fun updateTransactionList(championTeamList: MutableList<ChampionTeam>?){
        this.championTeamList = championTeamList
        notifyDataSetChanged()
    }

    fun clearMainAdapter() {
        championTeamList?.run {
            championTeamList!!.clear()
            championTeamList = null
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), BoardAdapter.OnItemClicked  {

        private val binding  = TeamItemBinding.bind(view)

        fun bindView(championTeam: ChampionTeam) {
                val recyclerView = binding.championRowRecycler
                recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter = BoardAdapter(championTeam.championList as MutableList<Champion>, this)

                binding.teamNameTextView.text = championTeam.teamName

        }

        override fun itemClicked(champion: Champion, position: Int) {
            TODO("Not yet implemented")
        }
    }
}