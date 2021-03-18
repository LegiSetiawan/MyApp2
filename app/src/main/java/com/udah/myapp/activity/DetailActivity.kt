package com.udah.myapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.udah.myapp.R
import com.udah.myapp.models.getdata.DataItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.card_app.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val getDataDetail = intent.getParcelableExtra<DataItem>("data")
        dNama.text = (getDataDetail?.nama)
        dHarga.text = (getDataDetail?.harga)
        dSatuan.text = (getDataDetail?.satuan)
        dTotal.text = (getDataDetail?.total)
    }
}