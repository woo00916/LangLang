package com.handmadecode.langlang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.handmadecode.langlang.R
import com.handmadecode.langlang.data.History

class ListItemAdapter(context: Context, list: ArrayList<History>) : BaseAdapter() {
    private val context = context
    private val list = list

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
//        var lang:History= list.get(position)
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val context: Context = parent!!.context
        var view = convertView
        if (view == null) {
            var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item, parent, false)
        }
        val listViewItem = list[position]

        for ((index: Int, lang: String) in listViewItem.langs_json.withIndex()) {
            val id: Int = context.resources.getIdentifier("lang${index + 1}", "id", context.packageName)
            val tv = view!!.findViewById<TextView>(id)
            tv.visibility = View.VISIBLE
            tv.setText(lang)
        }
        val textView = view!!.findViewById<TextView>(R.id.origin_text)
        textView.setText(listViewItem.ori)
        return view
    }
}