package com.zeel.interviewmatrix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter
    private var rowCount: Int = 8
    val mainEdit: EditText by lazy { findViewById(R.id.mainEdit) }
    val mainButton: CardView by lazy { findViewById(R.id.mainButton) }
    val mainRecycler: RecyclerView by lazy { findViewById(R.id.mainRecycler) }

    val systemTime: Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainButton.setOnClickListener {

            if (System.currentTimeMillis() - systemTime < 3000) {
                return@setOnClickListener
            }

            if (mainEdit.text.toString().isEmpty()) {
                mainEdit.error = "Enter a value"
                return@setOnClickListener
            }
            rowCount = Integer.parseInt(mainEdit.text.toString());
            if (rowCount < 4) {
                mainEdit.error = "Must be bigger than 4"
                return@setOnClickListener
            }
            initRecyclerView()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mainAdapter = MainAdapter(this, rowCount)
        mainRecycler.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@MainActivity, rowCount)
        }
    }
}