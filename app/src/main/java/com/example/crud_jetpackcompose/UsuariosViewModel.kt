package com.example.crud_jetpackcompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsuariosViewModel : ViewModel() {
    var _listaUsuarios: ArrayList<Usuario> by mutableStateOf(arrayListOf())

    fun getUsuarios() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getUsuarios()
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    _listaUsuarios = response.body()!!.data
                }
            }
        }
    }

    fun addUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.addUsuarios(usuario)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }

    fun updateUsuario(idUsuario: String, usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.updateUsuario(idUsuario, usuario)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }

    fun deleteUsuario(idUsuario: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.deleteUsuario(idUsuario)
            withContext(Dispatchers.Main) {
                if (response.body()!!.codigo == "200") {
                    getUsuarios()
                }
            }
        }
    }
}