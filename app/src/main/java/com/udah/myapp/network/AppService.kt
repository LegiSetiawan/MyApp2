package com.udah.myapp.network

import com.udah.myapp.models.action.ResponseAction
import com.udah.myapp.models.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface AppService {
    //Fungsi Get Data
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>
    //Fungsi Get Data By Id
    @GET("getData.php")
    fun getDataById(@Query("id")id:String) : Call<ResponseGetData>
    //insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("nama")nama:String,
                    @Field("harga")harga:String,
                    @Field("satuan")satuan:String,
                    @Field("total")total:String) :Call<ResponseAction>
    //update data
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id")id:String,
                   @Field("nama")nama:String,
                   @Field("harga")harga:String,
                   @Field("satuan")satuan:String,
                   @Field("total")total:String) :Call<ResponseAction>
    //delete data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id")id:String) :Call<ResponseAction>
}