package com.example.miprimeraapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

open class Entrada(
    val id: Int,
    val precio: Int,
    val ev: String
){
    open fun mostrarDetalle(): String{
        return "$id $precio $ev"
    }
}

class EntradaGeneral(
    id: Int,
    precio: Int,
    ev: String
): Entrada(id, precio, ev){
    override fun mostrarDetalle(): String{
        return println("ID: $id  $ev Tipo de entrada: General  Precio: $$precio").toString()
    }
}
class EntradaVip(
    id: Int,
    precio: Int,
    ev: String,
    val beneficiosExtra: String
): Entrada(id, precio, ev){
    override fun mostrarDetalle(): String {
        return println("ID: $id  $ev Tipo de entrada: VIP  Precio: $$precio  Beneficios: $beneficiosExtra").toString()
    }

}

fun main(){
    val entrada1 = EntradaGeneral(232, 56000, "evento1")
    val entrada2 = EntradaVip(254, 56000, "evento1", "beneficio1")

    val ventasGen = listOf<EntradaGeneral>()
    val venatsVip = listOf<EntradaVip>()

    entrada1.mostrarDetalle()
    entrada2.mostrarDetalle()
}