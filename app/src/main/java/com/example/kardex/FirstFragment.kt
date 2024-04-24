package com.example.kardex

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.kardex.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    val firstFragmentEpico : FisrtsFragmentViewModel by viewModels{
        FirstFragmentViewModelFactory((requireActivity().application as MaterialKardexApplication).repository)
    }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

//        val db = Room.databaseBuilder(
//            requireContext(),
//            AppDatabase::class.java, "MaterialKardex"
//        ).allowMainThreadQueries().build()
//
//        val MKDAO = db.MateriaKardexDAO()
//
//        Singleton.kardex.clear()
//        Singleton.kardex.addAll(MKDAO.getAll())

        val adapter = MateriaKardexAdapter{
            onItemClick(it)
        }
        binding.recyclerView.adapter = adapter
        //Log.d("Posisicio",adapter.itemCount.toString())

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        firstFragmentEpico.materiaKardexTodasMaterias.observe(viewLifecycleOwner, Observer { materias ->
            materias?.let {
                Log.e("Si entro al observer","Materias ${materias.size}")
                adapter.submitList(it)
            }
        })

    }

    private fun onItemClick(materiaKardex : MateriaKardex) {

//        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(0.toString())
//        findNavController().navigate(action)
        val bundle = Bundle().apply {

            putString("periodo", materiaKardex.periodo)
            putString("clave_materia", materiaKardex.claveMateria)
            putString("materia", materiaKardex.materia)
            putInt("calificacion", materiaKardex.calificacion)
        }

//        val action = FragmentFirstDirections
//        v.findNavController().navigate(action)
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(materiaKardex.periodo)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}