package com.abhi.electiondata

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.abhi.electiondata.adater.MyAdapter
import com.abhi.electiondata.app.OnClickListener
import com.abhi.electiondata.model.VoterId
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var myAdapter: MyAdapter? = null
    private var users = ArrayList<VoterId>()


    private val listener = object : OnClickListener {
        override fun onClickEvent(user: VoterId) {
            Toast.makeText(applicationContext, user.Name, Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var etSearch: TextInputEditText
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etField)
        recyclerView = findViewById(R.id.recyclerView)

        getUserList()


        etSearch.addTextChangedListener {
            println(etSearch.text.toString())
            filter(etSearch.text.toString())
        }
    }

    private fun getUserList() {
        Log.d("myTag", "This is my message");
        var jason: String? = null
        try {
            val inputStream: InputStream = assets.open("csvjson.json")
            Log.d("myTag", "try" + inputStream);
            jason = inputStream.bufferedReader().use { it.readText() }
            Log.d("myTag", "try" + jason);
            var jsonarray = JSONArray(jason)

            for (i in 0 until jsonarray.length()){
                var jsonobj = jsonarray.getJSONObject(i)
                val bookObject = VoterId(
                    jsonobj.getString("Serial No"),
                    jsonobj.getString("Name"),
                    jsonobj.getString("Guardian's Name"),
                    jsonobj.getString("New House No."),
                    jsonobj.getString("House Name"),
                    jsonobj.getString("Gender / Age"),
                    jsonobj.getString("ID Card No."),
                    jsonobj.getString("BOOTH"),
                )
                users.add(bookObject)
            }
        } catch (e: IOException) {
            print("thejson")
        }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        myAdapter = MyAdapter(users)
        myAdapter!!.setListener(listener)
        recyclerView.adapter = myAdapter
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<VoterId>()
        //looping through existing elements and adding the element to filtered list
        users.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.Name.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
            it.SerialNo.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
            it.IDCardNo.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))




        }
        //calling a method of the adapter class and passing the filtered list
        myAdapter!!.filterList(filteredNames)
    }
}