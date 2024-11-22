package com.example.marmitasapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marmitasapp.Model.Data.AppDataBase
import com.example.marmitasapp.Model.Entity.Cliente
import com.example.marmitasapp.ViewModel.ClienteViewModel
import com.example.marmitasapp.ViewModel.ClienteViewModelFactory
import com.example.marmitasapp.ui.theme.MarmitasappTheme

class CadastroClienteActivity : ComponentActivity() {

    private val viewModel: ClienteViewModel by viewModels {
        ClienteViewModelFactory(AppDataBase.getDatabase(applicationContext).clienteDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CadastroClienteScreen(viewModel = viewModel()) {
                finish()
            }
        }
    }
}

@Composable
fun CadastroClienteScreen(viewModel: ClienteViewModel, onFinish: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }
    var preferencias by remember { mutableStateOf("") }

    val resultadoMensagem = viewModel.resultadoMensagem.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = preferencias,
            onValueChange = { preferencias = it },
            label = { Text("Preferências Alimentares") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.inserirCliente(nome, email, endereco, preferencias) // Chama o método para salvar o cliente
                if (viewModel.resultadoMensagem.value == "Cliente salvo com sucesso!") {
                    onFinish() // Fecha a tela após o sucesso
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe a mensagem de resultado (sucesso ou erro)
        if (resultadoMensagem.isNotBlank()) {
            Text(text = resultadoMensagem, color = Color.Green)
        }
    }
}

