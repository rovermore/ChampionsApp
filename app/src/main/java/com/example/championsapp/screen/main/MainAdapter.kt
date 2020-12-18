package com.example.championsapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.R
import com.example.championsapp.databinding.TeamItemBinding
import com.example.championsapp.model.ChampionTeam
import com.squareup.picasso.Picasso

class MainAdapter (var championTeamList: MutableList<ChampionTeam>?) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

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

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        private val binding  = TeamItemBinding.bind(view)

        fun bindView(championTeam: ChampionTeam) {
            binding.apply {
                championTeam.championList[0].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView1)

                }
                championTeam.championList[1].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView2)

                }
                championTeam.championList[2].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView3)

                }
                championTeam.championList[3].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView4)

                }
                championTeam.championList[4].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView5)

                }
                championTeam.championList[5].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView6)

                }
                championTeam.championList[6].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView7)

                }
                championTeam.championList[7].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView8)

                }
                championTeam.championList[8].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView9)

                }
                championTeam.championList[9].image?.apply {
                    Picasso.with(binding.root.context)
                        .load(this)
                        .error(R.drawable.ic_baseline_person_24)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(championImageView10)

                }
                teamNameTextView.text = championTeam.teamName
            }
        }
    }
}