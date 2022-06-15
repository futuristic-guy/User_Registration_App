package com.example.userregistrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecycleShowData : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_show_data)

        actionBar?.title = "User Info"
        supportActionBar?.title = "User Info"
        var bundle = intent.getBundleExtra("Bundle")
        var arrayList= bundle?.getParcelableArrayList<Model>("uList") as ArrayList<Model>
        adapter = CustomAdapter(this, arrayList)
        initView()

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: OnItemClickListener{
            override fun onClick(position: Int) {

                var intent = Intent(applicationContext, UserProfileActivity::class.java)
                intent.putExtra("uName",arrayList[position].userName)
                intent.putExtra("uPhone",arrayList[position].phone)
                intent.putExtra("uEmail", arrayList[position].email)
                intent.putExtra("uGender", arrayList[position].gender)
                startActivity(intent)


            }

        })



    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycle_view)
    }


}