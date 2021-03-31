package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var recyclerView: RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = ItemAdapter(this, getItemsList())
        //recyclerView.layoutManager = GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                return if (position < 6 ) { // totalRowCount : How many item you want to show
                    1 // the item in position now takes up 4 spans
                } else 2
            }
        })
        recyclerView.layoutManager = gridLayoutManager

    }

    fun ToastMe(item: MenuItem) {
        Toast.makeText(this, "Home!", Toast.LENGTH_SHORT).show()
    }

    fun onCreateDialog(item: MenuItem) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Важное сообщение!")
                .setMessage("Ты нажал на Info")
                .setPositiveButton("ОК") { dialog, id ->
                    dialog.cancel()
                }
        builder.show()
    }

    private fun getItemsList(): ArrayList<DataModel> {
        val list = ArrayList<DataModel>()

        list.add(DataModel("Квитанции", "- 40 080,55 ₽ ", R.drawable.ic_bill, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Счетчики", "Подайте показания", R.drawable.ic_counter, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Рассрочка", "Сл. платеж 25.02.2018", R.drawable.ic_installment, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Страхование", "Полис до 12.01.2019", R.drawable.ic_insurance, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Интернет и ТВ", "Баланс 350 ₽", R.drawable.ic_tv, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Домофон", "Подключен", R.drawable.ic_homephone, ItemAdapter.VIEW_TYPE_TWO))
        list.add(DataModel("Охрана", "Нет", R.drawable.ic_security, ItemAdapter.VIEW_TYPE_ONE))
        list.add(DataModel("Контакты УК и служб", "", R.drawable.ic_contacts, ItemAdapter.VIEW_TYPE_ONE))
        list.add(DataModel("Мои заявки", "", R.drawable.ic_request, ItemAdapter.VIEW_TYPE_ONE))
        list.add(DataModel("Памятка жителя А101", "", R.drawable.ic_about, ItemAdapter.VIEW_TYPE_ONE))

        return list
    }


}