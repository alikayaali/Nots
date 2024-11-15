package Nots.view

import NotAdapter
import Nots.NotsModel.NotModel
import Nots.db.NotDatabase
import Nots.db.NotsDao
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.notss.databinding.FragmentNotBinding
import com.example.notss.databinding.FragmentNotListeBinding
import kotlinx.coroutines.launch

class NotFragment : Fragment() {

    private var _binding: FragmentNotBinding? = null
    private val binding get() = _binding!!

    private lateinit var db:NotDatabase
    private lateinit var notsDao: NotsDao
    private var secilenNot:NotModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db=Room.databaseBuilder(requireContext(),NotDatabase::class.java,"Notlar").build()
        notsDao=db.notsDao()
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val bilgi=NotFragmentArgs.fromBundle(it).bilgi
            if (bilgi=="yeni"){
                secilenNot=null
                //Yeni TariF Eklenecek
                binding.btnSil.isEnabled=false
                binding.btnKaydet.isEnabled=true
            }
            else {
                //Eski Tarif
                binding.btnSil.isEnabled = true
                binding.btnKaydet.isEnabled = false
                val id=NotFragmentArgs.fromBundle(it).notId
                lifecycleScope.launch {
                    val not=notsDao.findById(id)

                    not?.let {
                        veriGoster(it)  // EditText'lere veriyi yükle
                    }



                }
            }
        }

        binding.btnKaydet.setOnClickListener{
            kaydet(it)
        }
        binding.btnSil.setOnClickListener{
            sil(it)
        }

    }

    private fun veriGoster(not:NotModel){
        binding.editTextBaslik.setText(not.notBaslik)
        binding.editTextIcerik.setText(not.notIcerik)
        secilenNot=not
    }
    fun kaydet(view: View) {
        val baslik = binding.editTextBaslik.text.toString()
        val icerik = binding.editTextIcerik.text.toString()

        if (baslik.isNotEmpty() && icerik.isNotEmpty()) {
            val not = NotModel(baslik,icerik)  // NotModel oluşturuluyor
            lifecycleScope.launch {
                notsDao.insert(not)  // Veritabanına ekleniyor

            }
            findNavController().popBackStack()  // Önceki ekrana dönülüyor
        } else {
            Toast.makeText(requireContext(), "Başlık ve içerik boş olamaz!", Toast.LENGTH_SHORT).show()
        }
    }

    fun sil(view:View){
        if (secilenNot!=null){
            lifecycleScope.launch {
                notsDao.delete(not = secilenNot!!)

            }

        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
