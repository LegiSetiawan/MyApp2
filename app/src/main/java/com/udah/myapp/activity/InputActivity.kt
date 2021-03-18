package com.udah.myapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.udah.myapp.R
import com.udah.myapp.models.action.ResponseAction
import com.udah.myapp.models.getdata.DataItem
import com.udah.myapp.models.getdata.ResponseGetData
import com.udah.myapp.network.NetworkModule
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getDataInput = intent.getParcelableExtra<DataItem>("data")
        if (getDataInput!=null) {
            inNama.setText(getDataInput.nama)
            inHarga.setText(getDataInput.harga)
            inSatuan.setText(getDataInput.satuan)
            inTotal.setText(getDataInput.total)

            save.text = "Update"
        }

        when(save.text) {
            "Update"->{
            save.setOnClickListener {
                updateData(getDataInput?.id,inNama.text.toString(),inHarga.text.toString(),inSatuan.text.toString(),inTotal.text.toString())
            }
            }else->{
            save.setOnClickListener {
                inputData(inNama.text.toString(),inHarga.text.toString(),inSatuan.text.toString(),inTotal.text.toString())
            }
        }
        }

        cancel.setOnClickListener {
            finish()
        }
    }

    private fun inputData(nama:String?,harga:String?,satuan:String?,total:String?) {
        val input = NetworkModule.service().insertData(nama?:"",harga?:"",satuan?:"",total?:"")
        input.enqueue(object :Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext,"Data berhasil di simpan",Toast.LENGTH_SHORT).show()
                finish()
            }

        })
    }

    private fun updateData(id:String?,nama:String?,harga:String?,satuan:String?,total:String?) {
        val update = NetworkModule.service().updateData(id?:"",nama?:"",harga?:"",satuan?:"",total?:"")
        update.enqueue(object :Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext,"Data Berhasil Di Update",Toast.LENGTH_SHORT).show()
            }

        })
    }

}