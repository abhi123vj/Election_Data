package com.abhi.electiondata.adater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhi.electiondata.R
import com.abhi.electiondata.app.OnClickListener
import com.abhi.electiondata.model.VoterId

class MyAdapter(private var userList: ArrayList<VoterId>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var listener: OnClickListener? = null

    fun setListener(onClickListener: OnClickListener) {
        this.listener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
        //Custom OnClickListener Event
        holder.itemView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onClickEvent(userList[position])
            }
        })
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: VoterId) {
            val tvName = itemView.findViewById<TextView>(R.id.Name)
            val tvSerialNo = itemView.findViewById<TextView>(R.id.SerialNo)
            val tvGuardian = itemView.findViewById<TextView>(R.id.Guardian)
            val tvHouseNo = itemView.findViewById<TextView>(R.id.HouseNo)
            val tvHouseName = itemView.findViewById<TextView>(R.id.HouseName)
            val tvGenderAge = itemView.findViewById<TextView>(R.id.GenderAge)
            val tvIDCardNo = itemView.findViewById<TextView>(R.id.IDCardNo)
            val tvBOOTH = itemView.findViewById<TextView>(R.id.BOOTH)
            tvName.text = user.Name
            tvSerialNo.text=user.SerialNo
            tvGuardian.text = user.Guardian
            tvHouseNo.text=user.HouseNo
            tvHouseName.text = user.HouseName
            tvGenderAge.text=user.GenderAge
            tvIDCardNo.text = user.IDCardNo
            when(user.BOOTH){
                "1"-> tvBOOTH.text="St.Antony's U.P.S. Main Building West Portion,Vadi"
                "2"-> tvBOOTH.text="St.Joseph LPS main building south portion, Valiyakada"
                "3"-> tvBOOTH.text="MuhammadianLPS main building north portion, Valiyakada"
                "4"-> tvBOOTH.text="Muhammadian LPS Main building South portion, Valiyakada"
                "5"-> tvBOOTH.text="St.Joseph L.P.S Main Building, Middle Portion, Valiyakada"
                "6"-> tvBOOTH.text="St.JosephL.P.S Main Building, North Side, Valiyakada"
            }

        }
    }

    /*This method will filter the list and here we are passing the filtered data
        and assigning it to the list with notifyDataSetChanged method
    */
    fun filterList(filteredNames: ArrayList<VoterId>) {
        this.userList = filteredNames
        notifyDataSetChanged()
    }
}