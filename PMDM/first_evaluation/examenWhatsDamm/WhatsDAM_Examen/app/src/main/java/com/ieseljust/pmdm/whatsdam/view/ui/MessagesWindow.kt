package com.ieseljust.pmdm.whatsdam.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ieseljust.pmdm.whatsdam.Message
// importem la classe de vinculacio
import com.ieseljust.pmdm.whatsdam.databinding.ActivityMessagesWindowBinding
import com.ieseljust.pmdm.whatsdam.repository.MessagesRepository
import com.ieseljust.pmdm.whatsdam.viewmodel.MessagesViewModel
import com.ieseljust.pmdm.whatsdam.viewmodel.MissatgesAdapter


class MessagesWindow : AppCompatActivity() {

    // Instanciem la classe de vinculacio
    private lateinit var binding: ActivityMessagesWindowBinding

    // Informacio del nom i el server
    private lateinit var nickname:String
    private lateinit var serveraddress:String

    // Referencia al repositori
    val repository=MessagesRepository.getInstance()

    // Adaptació a MVVM: Definim una instància del ViewModel com a lateinit
    private lateinit var viewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Innstanciem la classe de vinculacio
        binding= ActivityMessagesWindowBinding.inflate(layoutInflater)

        // Establim la vista
        setContentView(binding.root)

        // Instanciem el ViewModel mitjançant ViewModelProvider
        viewModel =
            ViewModelProvider(this)[MessagesViewModel::class.java]

        // Agefaem el nickname del propi repositori
        nickname = repository.username
        serveraddress = repository.server
        binding.connectionInfoTextView.text="Connectat a ${serveraddress} com a ${nickname}"

        // Escoltem l'ultim element inserit per fer el scroll
        // quan aquest canvie
        viewModel.latestInserted.observe(this) {
            binding.MessagesRecyclerView.smoothScrollToPosition(it)
        }

        // Escoltem l'adaptador per al RecyclerView
        viewModel.adaptador.observe(this) {
            binding.MessagesRecyclerView.adapter = it

            // Fem scroll a l'ultim element, per carregar el final
            // de la conversa en la carrega de missatges inicial
            var latest=(binding.MessagesRecyclerView.adapter?.itemCount?:0)-1
            if (latest==-1) latest=0
            binding.MessagesRecyclerView.smoothScrollToPosition(latest)
        }

        // Comportament del boto d'enviar
        binding.sendMessage.setOnClickListener{

            if (binding.MessageText.text.toString()!="") {

                // Afegim el missatge a la llista

                viewModel.addMessage(Message(
                    username = nickname,
                    text = binding.MessageText.text.toString()
                ))

                // Netegem el text
                binding.MessageText.setText("")
            }
        }

        binding.MessagesRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.MessagesRecyclerView.adapter = viewModel.adaptador.value

        viewModel.resposta.observe(this){
            binding.MessageText.setText(it)
        }




        // Configuracio del RecyclerView
        // Associem el LayoutManager, com a linial


        // No cal associar aci l'adaptador al recyclerView, ja
        // que ho fem en l'observador.
        // binding.MessagesRecyclerView.adapter = MissatgesAdapter()

    }
}