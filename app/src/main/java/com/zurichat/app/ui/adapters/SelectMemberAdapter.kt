package com.zurichat.app.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.zurichat.app.databinding.ListItemSelectMemberBinding
import com.zurichat.app.models.MembersData
import com.zurichat.app.models.OrganizationMember

class SelectMemberAdapter(private val user: (OrganizationMember) -> Unit):

    RecyclerView.Adapter<SelectMemberAdapter.SelectMemberViewModel>(), Filterable {
    private var members = listOf<MembersData>()

    @SuppressLint("NotifyDataSetChanged")
    fun loadMembers(contacts: List<MembersData>) {
        this.members = contacts
        notifyDataSetChanged()
    }

    lateinit var list: List<OrganizationMember>
    private val _list: List<OrganizationMember> by lazy { list }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectMemberViewModel {
        val binding = ListItemSelectMemberBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SelectMemberViewModel(binding)
    }

    override fun onBindViewHolder(holder: SelectMemberViewModel, position: Int) {
        holder.apply{
            bind(list[position])
            itemView.setOnClickListener {
                user(list[position])

            }
        }
    }


    override fun getItemCount(): Int = list.size


    inner class SelectMemberViewModel(val binding: ListItemSelectMemberBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(user: OrganizationMember) {
//            binding.channelItemPersonNameTxt.text = if(user.firstName.isEmpty() && user.lastName.isEmpty())
//                "No name"
//            else "${user.firstName} ${user.lastName}"
            binding.channelItemPersonNameTxt.text = user.name()
            binding.channelItemMessageTxt.text = user.email
        }
    }

    override fun getFilter(): Filter {
        return _filter
    }

    val _filter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterList = mutableListOf<OrganizationMember>()

            for(i in _list) {
                if("${i.firstName}${i.lastName}".contains(constraint?:"",true)) {
                    filterList.add(i)
                }
            }

            if(filterList.isEmpty()) {
                for(i in _list) {
                    if(i.email.contains(constraint?:"",true)) {
                        filterList.add(i)
                    }
                }
            }
            return FilterResults().apply {
                values = filterList
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val resultList = results?.values as MutableList<OrganizationMember>

            if (resultList.isEmpty()) {
                list = _list
                notifyDataSetChanged()
            } else {
                list = results.values as MutableList<OrganizationMember>
                notifyDataSetChanged()
            }

        }

    }
}