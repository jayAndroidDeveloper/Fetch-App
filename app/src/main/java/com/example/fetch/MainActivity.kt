package com.example.fetch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.Adapter.DataAdapter
import com.example.fetch.Model.DataResponse
import com.example.fetch.data.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    var dataList = ArrayList<DataResponse>()

    lateinit
    var recyclerView: RecyclerView

    lateinit
    var searchbrandedit: EditText

    private
    var myAdapter: DataAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.projectRec)
        searchbrandedit=findViewById(R.id.search)
        myAdapter = DataAdapter(dataList, this)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getPorjectData()
        searchbrandedit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
    }

    private fun getPorjectData() {
        val call: Call<List<DataResponse>> = ApiClient.getClient.getProjects()
        call.enqueue(object : Callback<List<DataResponse>> {
            override fun onResponse(
                call: Call<List<DataResponse>>?,
                response: Response<List<DataResponse>>?
            ) {
                dataList.addAll(response!!.body()!!)
                recyclerView.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DataResponse>>?, t: Throwable?) {
                Log.e("MainActivity", "Failer")
            }
        })
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        val filteredNames = ArrayList<DataResponse>()
        //looping through existing elements and adding the element to filtered list
        dataList!!.filterTo(filteredNames) {
            //if the existing elements contains the search input
            it.title.contains(text)
        }
        //calling a method of the adapter class and passing the filtered list
        if (filteredNames != null) {
            myAdapter!!.filterList(filteredNames)
        }
    }
}