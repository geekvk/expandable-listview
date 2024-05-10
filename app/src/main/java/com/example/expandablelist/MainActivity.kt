package com.example.expandablelist

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.expandablelist.adapter.ExpandableListAdapter
import com.example.expandablelist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var expandableListTitle : List<String>
    private var expandableListDetails : HashMap<String, List<String>> = HashMap()

    private val selectedItems = ArrayList<String>()
    private val selectedGroups = ArrayList<Int>()

    val cricket : MutableList<String> = ArrayList()
    val footBall : MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cricket.add("In")
        cricket.add("SL")
        cricket.add("NZ")
        cricket.add("AUS")
        cricket.add("SA")

        footBall.add("PRT")
        footBall.add("ARG")
        footBall.add("BZ")
        footBall.add("GRM")
        expandableListDetails["cricket"] = cricket
        expandableListDetails["football"] = footBall
        expandableListTitle = ArrayList(expandableListDetails.keys)

        val listViewAdapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, selectedItems)
        binding.listView.adapter = listViewAdapter
        val adapter = ExpandableListAdapter(
            this,
            expandableListTitle,
            expandableListDetails,
            selectedItems,
            selectedGroups,
            listViewAdapter
        )
        binding.expView.setAdapter(adapter)


//        binding.expView.setOnGroupExpandListener {groupPosition ->
//            val selectedGroup = expandableListTitle[groupPosition]
//            selectedGroups.add(groupPosition)
//            adapter.notifyDataSetChanged()
//            false
//        }
//        binding.expView.setOnChildClickListener{
//                parent, v, groupPosition, childPosition, id ->
//            val selectedItem = expandableListDetails[expandableListTitle[groupPosition]]?.get(childPosition) as String
//            selectedGroups.remove(groupPosition)
//            adapter.notifyDataSetChanged()
//            true
//        }
    }
}