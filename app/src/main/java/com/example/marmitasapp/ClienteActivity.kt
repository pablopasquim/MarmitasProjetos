package com.example.marmitasapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marmitasapp.Model.Data.AppDataBase
import com.example.marmitasapp.Model.Entity.Cliente
import com.example.marmitasapp.ViewModel.ClienteViewModel
import com.example.marmitasapp.ViewModel.ClienteViewModelFactory


class ClienteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializando o ViewModel
        val clienteDao = AppDataBase.getDatabase(applicationContext).clienteDao()
        val viewModel: ClienteViewModel by viewModels {
            ClienteViewModelFactory(clienteDao)
        }

        setContent {
            // Usando o NavController para a navegação entre as telas dentro da ClienteActivity
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") {
                    ClientesMenu(navController)
                }
                composable("cadastro") {
                    CadastrarClienteScreen(viewModel = viewModel) {
                        navController.popBackStack() // Volta para o menu após o cadastro
                    }
                }
                composable("listagem") {
                    VisualizarClientesScreen(viewModel = viewModel, navController = navController)
                }
                composable("editarCliente/{clienteNome}") { backStackEntry ->
                    val clienteNome = backStackEntry.arguments?.getString("clienteNome") ?: ""
                    val cliente = viewModel.listaClientes.value.find { it.nome == clienteNome }
                    if (cliente != null) {
                        EditarClienteScreen(cliente = cliente, viewModel = viewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClientesMenu(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gerenciar Clientes", modifier = Modifier.padding(bottom = 16.dp))

        Button(
            onClick = { navController.navigate("cadastro") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("listagem") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Visualizar Clientes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao Menu Principal")
        }
    }
}

@Composable
fun CadastrarClienteScreen(viewModel: ClienteViewModel, onFinish: () -> Unit) {
    // Lógica de cadastro de cliente
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
                viewModel.inserirCliente(nome, email, endereco, preferencias)
                if (resultadoMensagem == "Cliente salvo com sucesso!") {
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultadoMensagem.isNotBlank()) {
            Text(text = resultadoMensagem)
        }

        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao Menu")
        }
    }
}

@Composable
fun VisualizarClientesScreen(viewModel: ClienteViewModel, navController: NavController) {
    val clientes = viewModel.listaClientes.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Clientes Cadastrados", modifier = Modifier.padding(bottom = 16.dp))

        clientes.forEach { cliente ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Nome: ${cliente.nome}", modifier = Modifier.padding(bottom = 4.dp))
                    Text("Email: ${cliente.email}", modifier = Modifier.padding(bottom = 4.dp))
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            // Passando o nome do cliente para a tela de edição
                            navController.navigate("editarCliente/${cliente.nome}")
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Editar")
                    }
                    Button(
                        onClick = {
                            viewModel.excluirCliente(cliente)
                        }
                    ) {
                        Text("Excluir")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar ao Menu")
        }
    }
}

@Composable
fun EditarClienteScreen(cliente: Cliente, viewModel: ClienteViewModel, onFinish: () -> Unit) {
    var nome by remember { mutableStateOf(cliente.nome) }
    var email by remember { mutableStateOf(cliente.email) }
    var endereco by remember { mutableStateOf(cliente.endereco) }
    var preferencias by remember { mutableStateOf(cliente.preferencias) }

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
                viewModel.atualizarCliente(cliente.id, nome, email, endereco, preferencias)
                if (resultadoMensagem == "Cliente atualizado com sucesso!") {
                    onFinish() // Chama o onFinish para navegar de volta
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Alterações")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultadoMensagem.isNotBlank()) {
            Text(text = resultadoMensagem)
        }
    }
}







