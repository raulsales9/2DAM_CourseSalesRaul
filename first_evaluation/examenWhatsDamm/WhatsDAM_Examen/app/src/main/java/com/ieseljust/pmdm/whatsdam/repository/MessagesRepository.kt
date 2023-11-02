package com.ieseljust.pmdm.whatsdam.repository

import com.ieseljust.pmdm.whatsdam.Message
import com.ieseljust.pmdm.whatsdam.Messages

class MessagesRepository private constructor() { // Constructor privat

    // Propietats del repositori

    val username="Casc Fosc"       // nom de l'usuari
    val server="192.168.1.1"    // adreça IP del servidor

    // Implementacio del Singleton
    companion object {
        // Referencia a la propia instancia de la classe
        private var INSTANCE: MessagesRepository? = null

        // Metode per obtenir la referencia a aquesta instancia
        fun getInstance(): MessagesRepository {
            if (INSTANCE == null) {
                INSTANCE = MessagesRepository()
            }
            return INSTANCE!!
        }
    }

    // Mètodes que ofereix aquest API del repositori:

    fun getMessages() = Messages.getMessages()
    fun getNumMessages() = Messages.getNumMessages()
    fun add(msg: Message) = Messages.add(msg)
    fun respondre(msg: Message){
        val resposta = Message(username = "Usuari", text = "Resposta" + msg.text)
        add(resposta)
    }
    fun supostBorrat(msg: Message) {
        msg.text = "El mensaje ha sido eliminado"
    }

}