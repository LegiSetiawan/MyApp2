package com.udah.myapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.udah.myapp.R
import com.udah.myapp.adapter.DataAdapter
import com.udah.myapp.models.action.ResponseAction
import com.udah.myapp.models.getdata.DataItem
import com.udah.myapp.models.getdata.ResponseGetData
import com.udah.myapp.network.NetworkModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_app.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        showData()

    }
    private fun showData() {

        val listSayur = NetworkModule.service().getData()
        listSayur.enqueue(object : Callback<ResponseGetData> {
            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {
                if (response.isSuccessful) {
                    val item = response.body()?.data
                    val adapter = DataAdapter(item,object : DataAdapter.OnClickListener{
                        override fun detail(item: DataItem?) {
                            val intent = Intent(applicationContext,
                                DetailActivity::class.java)
                            intent.putExtra("data",item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItem?) {
                            AlertDialog.Builder(this@MainActivity).apply {
                                    setTitle("Hapus Data")
                                    setMessage("yakin data akan di delete .!?")
                                    setPositiveButton("Delete") {dialog,which->
                                        deleteData(item?.id)
                                        dialog.dismiss()
                                }
                                setNegativeButton("cancel") {dialog,which->
                                    dialog.dismiss()
                                }
                            }.show()
                        }
                        override fun update(item:DataItem?) {
                            val intent = Intent(applicationContext,
                                InputActivity::class.java)
                            intent.putExtra("data",item)
                            startActivity(intent)
                        }
                    })
                    recycler_view.adapter = adapter
                }

            }

        })

    }

    private fun deleteData(id: String?) {
                    val delete = NetworkModule.service().deleteData(id?:"")
        delete.enqueue(object :Callback<ResponseAction>{
            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext,"Data berhasi di hapus",Toast.LENGTH_SHORT).show()
                showData()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}