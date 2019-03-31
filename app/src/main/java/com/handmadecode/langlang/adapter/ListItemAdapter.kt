package com.handmadecode.langlang

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.handmadecode.langlang.data.Languages
import org.w3c.dom.Text

class ListItemAdapter(context: Context, list : ArrayList<Languages>) : BaseAdapter() {
    private val context=context
    private val list =list

    override fun getItem(position: Int): Any {
       return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        var lang:Languages= list.get(position)
       return  lang.reqId
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val context:Context=parent!!.context
        var view  = convertView
        if(view==null){
            var inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_item,parent,false)
        }
        val listViewItem=list[position]

        for(lang:String in listViewItem.langs){
            val tv =view!!. findViewById<TextView>(R.id.lang1)
            tv.visibility=View.VISIBLE
            tv.setText(lang)
        }
        val textView=view!!.findViewById<TextView>(R.id.origin_text)
        textView.setText(listViewItem.origin_text)
        return view
    }
}