package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Inicjalizacja
        var items = arrayListOf<String>()
        var adapter =ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_multiple_choice
            , items)
        // Dodanie do listy zaadań w przypadku wcisnięcia przycisku dodaj.
        add.setOnClickListener {
            // Zapobieganie dodawaniu pustych zadań
            if(editText.length() > 0) {
                items.add(editText.text.toString())
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                // Czyszczenie miejsca w któym wpisujemy tekst po dodaniu lub edycji
                editText.text.clear()
            }
        }
        // Usuwanie wszystkich elementów z listy w przypadku kliknięcia przycisku wyczyść
        clear.setOnClickListener {

            items.clear()
            adapter.notifyDataSetChanged()
        }
        // Wyświetlenie popupu o wybranym przez nas tasku
        listView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(this, "Wybrałeś task --> "+items.get(i), android.widget.Toast.LENGTH_SHORT).show()
        }
        // Usunięcie wybranych przez nas tasków w momencie kliknięcia przycisku usuń
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(items.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}