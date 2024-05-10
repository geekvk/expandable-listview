package com.example.expandablelist.adapter

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.expandablelist.MainActivity
import com.example.expandablelist.R

class ExpandableListAdapter(
    private val context : Context,
    private val expandableListTitle : List<String>,
    private val expandableListDetails : HashMap<String, List<String>>,
    private val selectedItems : MutableList<String>,
    private val listViewAdapter : ArrayAdapter<String>
) : BaseExpandableListAdapter(){
    override fun getGroupCount(): Int {
        return expandableListTitle.size
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return expandableListDetails[expandableListTitle[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return expandableListTitle[listPosition]
    }

    override fun getChild(listPosition: Int, expandableListPosition: Int): Any {
        return expandableListDetails[expandableListTitle[listPosition]]!![expandableListPosition]
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getChildId(listPosition: Int, expandableListPosition: Int): Long {
        return expandableListPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?): View {
        val convertViews = LayoutInflater.from(context).inflate(R.layout.list_group, null)
        val listTitle = getGroup(groupPosition) as String
        val listTitleText = convertViews.findViewById<View>(R.id.list_title) as TextView
        listTitleText.setTypeface(null,Typeface.BOLD)
        listTitleText.text = listTitle

        return convertViews
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        converterView: View?,
        parent: ViewGroup?): View {
        var conveterViews = LayoutInflater.from(context).inflate(R.layout.list_item, null)
        val expandableListName = getChild(groupPosition,childPosition) as String
        val expandedListTextView = conveterViews.findViewById<TextView>(R.id.list_item)
        val checkBox = conveterViews.findViewById<CheckBox>(R.id.cb_child)

        checkBox.isChecked = selectedItems.contains(expandableListName)
        expandedListTextView.text = expandableListName

        checkBox.setOnClickListener {
            val isChecked = checkBox.isChecked
            if (isChecked){
                selectedItems.add(expandableListName)
            }else{
                selectedItems.remove(expandableListName)
            }
            Log.d("selectedItems->", "${selectedItems.toString()}, $isChecked")
            listViewAdapter.notifyDataSetChanged()
            notifyDataSetChanged()
        }

        return conveterViews
    }

    override fun isChildSelectable(
        groupPosition: Int,
        childPosition: Int): Boolean {
        return true
    }
}