package Nots.view

import NotAdapter
import Nots.db.NotDatabase
import Nots.db.NotsDao
import android.graphics.Path.Direction
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.notss.databinding.FragmentNotListeBinding
import kotlinx.coroutines.launch


class NotListeFragment : Fragment() {

        private var _binding: FragmentNotListeBinding? = null
        private val binding get() = _binding!!
    private lateinit var db:NotDatabase
    private lateinit var notsDao: NotsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Room.databaseBuilder(requireContext(),NotDatabase::class.java,"Notlar").build()
        notsDao=db.notsDao()

    }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentNotListeBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.btnEkle.setOnClickListener{yeniEkle(it)}
            binding.notrecyclerview.layoutManager=LinearLayoutManager(requireContext())
            verileriAl()
        }
    fun verileriAl(){
        lifecycleScope.launch{
            val notListesi = notsDao.getAllNotes() // Not verilerini al
            val adapter = NotAdapter(notListesi)  // Adapter'a veriyi gönder
            binding.notrecyclerview.adapter = adapter
        }
    }

    fun yeniEkle(view: View){
        val action=NotListeFragmentDirections.actionNotListeFragmentToNotFragment(0, bilgi = "yeni")
        Navigation.findNavController(view).navigate(action)
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }


