import Nots.NotsModel.NotModel
import Nots.view.NotListeFragmentDirections
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notss.databinding.RecyclerRowBinding
import androidx.navigation.findNavController


class NotAdapter(val notListesi:List<NotModel>) :RecyclerView.Adapter<NotAdapter.NotHolder>(){
    class NotHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotHolder {
       val recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotHolder(recyclerRowBinding )
    }

    override fun getItemCount(): Int {
        return notListesi.size
    }

    override fun onBindViewHolder(holder: NotHolder, position: Int) {
        holder.binding.baslik.text=notListesi[position].notBaslik
        holder.itemView.setOnClickListener{
            val action = NotListeFragmentDirections.actionNotListeFragmentToNotFragment(bilgi = "eski", notId = notListesi[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}



