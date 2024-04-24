package com.example.kardex
import android.util.Log

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
//se le va decalrar que necesita un parametro
class MateriaKardexAdapter(val xyz: (MateriaKardex) -> Unit) : ListAdapter< MateriaKardex, MateriaKardexAdapter.MateriaKardexViewHolder>(MKComparator()) {

    class MateriaKardexViewHolder(materia_kardex_item: View,val xyz: (MateriaKardex) -> Unit) : RecyclerView.ViewHolder(materia_kardex_item){
        val tvMateria = materia_kardex_item.findViewById<TextView>(R.id.tvMateria)
        val tvCalificacion = materia_kardex_item.findViewById<TextView>(R.id.tvCalificacion)
        val tvPeriodo = materia_kardex_item.findViewById<TextView>(R.id.tvPeriodo)

        fun bind(materiaKardex: MateriaKardex){
            tvMateria.text = materiaKardex.materia
            tvCalificacion.text = materiaKardex.calificacion.toString()
            tvPeriodo.text = materiaKardex.periodo

            itemView.setOnClickListener{
                xyz(materiaKardex)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaKardexViewHolder {
        val materia_kardex_item = LayoutInflater.from(parent.context).inflate(R.layout.materia_kardex_item, parent, false)
        return MateriaKardexViewHolder(materia_kardex_item,xyz)
    }

//    override fun getItemCount(): Int {
//        return Singleton.kardex.size
//    }




    override fun onBindViewHolder(holder: MateriaKardexViewHolder, position: Int) {
        //holder.bind(Singleton.kardex[position])
        val Materia_epica  = getItem(position)
        holder.bind(Materia_epica)
        //Log.d("posicion", position.toString())
//        holder.itemView.setOnClickListener{
//            Log.d("TAG", "message")
//            //Toast.makeText(holder.itemView.context,"CLICK EN: ${Singleton.kardex[position]}",Toast.LENGTH_SHORT).show()
//        }

//          FORMA ANTERIOR DE HACERSE, FORMA NUEVA CON FUNCION BIND LINEA 17-21
//        holder.tvMateria.text = Singleton.kardex(position).materia
//        holder.tvCalificacion.text = Singleton.kardex(position).calificacion.toString()
//        holder.tvPeriodo.text = Singleton.kardex(position).periodo
    }
}
class MKComparator : DiffUtil.ItemCallback<MateriaKardex>() {
    override fun areItemsTheSame(oldItem: MateriaKardex, newItem: MateriaKardex): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MateriaKardex, newItem: MateriaKardex): Boolean {
        return oldItem.claveMateria == newItem.claveMateria
    }
}