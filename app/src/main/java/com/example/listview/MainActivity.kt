package com.example.listview

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var data = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var _lv1: ListView
    lateinit var _btnTambah: Button
    lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _lv1 = findViewById(R.id.lv1)
        _btnTambah = findViewById(R.id.btnTambah)

        data.addAll(listOf("1","2","3","4","5"))

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        _lv1.adapter = adapter


        _btnTambah.setOnClickListener {
            var dtAkhir = Integer.parseInt(data.get(data.size - 1)) + 1
            data.add("$dtAkhir")

            adapter.notifyDataSetChanged()
        }

        _lv1.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this,
                data[position],
                Toast.LENGTH_SHORT
            ).show()
        }


        gestureDetector = GestureDetector(
            this,
            object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                val position = _lv1.pointToPosition(e.x.toInt(), e.y.toInt())
                if (position != ListView.INVALID_POSITION) {
                    val selectedItem = data[position]
                    showActionDialog(position, selectedItem, data, adapter)
                }
                return true
            }
        })
    }


}