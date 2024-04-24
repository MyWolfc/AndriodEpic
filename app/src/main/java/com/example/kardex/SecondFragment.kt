package com.example.kardex
import android.app.AlertDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.kardex.databinding.FragmentSecondBinding
import androidx.fragment.app.viewModels
/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    val args : SecondFragmentArgs by navArgs()

    val secondFragmentViewModel : SecondFragmentViewModel by viewModels{
        SecondFragmentViewModelFactory((requireActivity().application as MaterialKardexApplication).repository)
    }
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("Si entro aqui 2",":D")
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityContext = requireActivity()
//        secondFragmentViewModel.registroGuardado.observe(viewLifecycleOwner,true)
        if (args.periodo.isNotEmpty())
        {
            //este nos ayuda a filtrar con un fitro que itera hasta encontrarlo
            val material_kardex = Singleton.kardex.filter{ x -> x.periodo == args.periodo }.firstOrNull()
            if(material_kardex != null)
            {
                binding.txtCalificacion.setText(material_kardex.calificacion)
                binding.txtMateria.setText(material_kardex.materia)
                binding.txtPeriodo.setText(material_kardex.periodo)
                binding.txtClaveMateria.setText(material_kardex.claveMateria.toString())

                binding.txtClaveMateria.isEnabled = false

            }
        }
        binding.btnEliminar.setOnClickListener{
            //Log.d("TAG", "message")

            val periodoExiste = binding.txtPeriodo.text.toString().isEmpty()
            val clave_materiaExiste = binding.txtClaveMateria.text.toString().isEmpty()
            val materiaExiste = binding.txtCalificacion.text.toString().isEmpty()
            val calificacionExiste = binding.txtCalificacion.text.toString().isEmpty()
            if (periodoExiste || clave_materiaExiste || materiaExiste || calificacionExiste)
            {
                Toast.makeText(activityContext, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
            }
            else
            {
                    val periodo = binding.txtPeriodo.text.toString()
                val clave_materia = binding.txtClaveMateria.text.toString()
                val materia = binding.txtMateria.text.toString()
                val calificacionText = binding.txtCalificacion.text.toString()
                val calificacion = if (calificacionText.isNotBlank()) calificacionText.toInt() else 0

                val materia_kardex = MateriaKardex(periodo, clave_materia, materia, calificacion)
                val posicionEliminar = Singleton.getItem(materia_kardex)

                val builder = AlertDialog.Builder(activityContext)

                builder.setTitle("Se eliminara el siguiente registro")
                builder.setMessage("Se eliminara este registro. ¿Estás seguro de continuar?")

                builder.setPositiveButton("Aceptar") { dialog, which ->
                    Singleton.removeItem(posicionEliminar)
                    Toast.makeText(activityContext, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }

                builder.setNegativeButton("Cancelar") { dialog, which ->
                    //val posicion = Singleton.kardex.
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }

        binding.btnGuardarMateriaKardex.setOnClickListener {
            val periodo = binding.txtPeriodo.text.toString()
            val clave_materia = binding.txtClaveMateria.text.toString()
            val materia = binding.txtMateria.text.toString()
            val calificacionText = binding.txtCalificacion.text.toString()
            val calificacion = if (calificacionText.isNotBlank()) calificacionText.toInt() else 0
            val materia_kardexadd = MateriaKardex(periodo, clave_materia, materia, calificacion)

            secondFragmentViewModel.insertarMaterialKardex(materia_kardexadd)
//            val dbHelper = KardexSqlLiteOpenHelper(requireContext())
//            val db = Room.databaseBuilder(
//                    requireContext(),
//                    AppDatabase::class.java, "MaterialKardex"
//                    ).allowMainThreadQueries().build()
//            val db = dbHelper.writableDatabase
//            val values = ContentValues().apply {
//                put("clave_materia", materia_kardexadd.claveMateria)
//                put("materia", materia_kardexadd.materia)
//                put("periodo", materia_kardexadd.periodo)
//                put("calificacion",materia_kardexadd.calificacion)
//            }
//            val materiaKardexDao = db.MateriaKardexDAO()

            if(args.periodo != " ")
            {
//                materiaKardexDao.insertAll(materia_kardexadd)
                //Singleton.kardex.add(materia_kardexadd)
//                db.insert("MateriaKardex", null, values)

                Toast.makeText(activityContext, "Por favor, llene todos los campos", Toast.LENGTH_SHORT).show()

                Log.d("Insercion","Exitosa")

            }
            else
            {
//                  materiaKardexDao.update(materia_kardexadd)
//                var material_kardexmodi = Singleton.kardex.filter{ x -> x.periodo == args.periodo }.first()
//                material_kardexmodi.periodo = periodo
//                material_kardexmodi.calificacion = calificacion.toInt()
//                material_kardexmodi.materia = materia
//                val selectionargs = arrayOf(materia_kardexadd.claveMateria)
//
//                db.update("MateriaKardex",values,"clave_materia = ?",selectionargs)
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

//        arguments?.let { bundle ->
//            val periodo = bundle.getString("periodo")
//            val clave_materia = bundle.getString("clave_materia")
//            val materia = bundle.getString("materia")
//            val calificacion = bundle.getInt("calificacion")
//            var buscarMaterialKardex = MateriaKardex(periodo.toString(),clave_materia.toString(),materia.toString(),calificacion.toInt())
//            val posicion = Singleton.getItem(buscarMaterialKardex)
//            Log.d("Posicion por busquedad",posicion.toString())
//            binding.txtPeriodo.setText(periodo)
//            binding.txtClaveMateria.setText(clave_materia)
//            binding.txtMateria.setText(materia)
//            binding.txtCalificacion.setText(calificacion.toString())
//            }
       // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}