package com.example.marmitasapp

import com.example.marmitasapp.ViewModel.PedidoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marmitasapp.Model.Data.AppDataBase
import com.example.marmitasapp.Model.Entity.Marmita
import com.example.marmitasapp.ViewModel.MarmitaViewModel
import com.example.marmitasapp.ViewModel.MarmitaViewModelFactory
import com.example.marmitasapp.ViewModel.PedidoViewModelFactory

class MarmitaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val marmitaDao = AppDataBase.getDatabase(applicationContext).marmitaDao()
        val pedidoDao = AppDataBase.getDatabase(applicationContext).pedidoDao()
        val viewModel: MarmitaViewModel by viewModels {
            MarmitaViewModelFactory(marmitaDao)
        }
        val pedidoViewModel: PedidoViewModel by viewModels {
            PedidoViewModelFactory(pedidoDao)
        }

        setContent {
            MarmitaNavHost(viewModel = viewModel, pedidoViewModel = pedidoViewModel)
        }
    }
}

@Composable
fun MarmitaNavHost(viewModel: MarmitaViewModel, pedidoViewModel: PedidoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MarmitaMenu(navController)
        }
        composable("cadastro") {
            CadastrarMarmitaScreen(viewModel = viewModel) {
                navController.navigate("menu") { popUpTo("menu") { inclusive = true } }
            }
        }
        composable("listagem") {
            VisualizarMarmitasScreen(viewModel = viewModel, navController = navController)
        }
        composable("fazerPedido/{marmitaId}") { backStackEntry ->
            val marmitaId = backStackEntry.arguments?.getString("marmitaId")?.toInt() ?: 0
            val marmita = viewModel.listaMarmitas.value.find { it.id == marmitaId }
            if (marmita != null) {
                FazerPedidoScreen(marmita = marmita, viewModel = pedidoViewModel) {
                    navController.navigate("listagem") { popUpTo("listagem") { inclusive = true } }
                }
            }
        }
    }
}

@Composable
fun MarmitaMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Gerenciar Marmitas", modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = { navController.navigate("cadastro") }, modifier = Modifier.fillMaxWidth()) {
            Text("Cadastrar Marmita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("listagem") }, modifier = Modifier.fillMaxWidth()) {
            Text("Visualizar Marmitas")
        }
    }
}

@Composable
fun CadastrarMarmitaScreen(viewModel: MarmitaViewModel, onFinish: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }

    val resultadoMensagem = viewModel.resultadoMensagem.value

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = descricao, onValueChange = { descricao = it }, label = { Text("Descrição") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = preco, onValueChange = { preco = it }, label = { Text("Preço") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val precoDouble = preco.toDoubleOrNull()
            if (precoDouble == null || nome.isBlank() || descricao.isBlank()) {
                viewModel.resultadoMensagem.value = "Por favor, preencha todos os campos corretamente!"
            } else {
                viewModel.inserirMarmita(nome, descricao, precoDouble)
                onFinish()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Salvar Marmita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultadoMensagem.isNotBlank()) {
            Text(text = resultadoMensagem, color = Color.Red)
        }
    }
}

@Composable
fun VisualizarMarmitasScreen(viewModel: MarmitaViewModel, navController: NavController) {
    val marmitas = viewModel.listaMarmitas.value

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        Text("Marmitas Cadastradas", style = TextStyle(fontSize = 24.sp, color = Color.Black))

        marmitas.forEach { marmita ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp).border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(marmita.nome, style = TextStyle(fontSize = 18.sp, color = Color.Black))

                Button(onClick = {
                    navController.navigate("fazerPedido/${marmita.id}")
                }) {
                    Text("Fazer Pedido")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun FazerPedidoScreen(marmita: Marmita, viewModel: PedidoViewModel, onFinish: () -> Unit) {
    var clienteId by remember { mutableStateOf(1) }
    var status by remember { mutableStateOf("Pendente") }
    val valorTotal = remember { marmita.preco }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pedido de Marmita: ${marmita.nome}", style = MaterialTheme.typography.headlineMedium)

        Text("Preço: R$ %.2f".format(valorTotal))

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.realizarPedido(clienteId, marmita.id, valorTotal, status)
            if (viewModel.resultadoMensagem.value == "Pedido realizado com sucesso!") {
                onFinish()
            }
        }) {
            Text("Confirmar Pedido")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = viewModel.resultadoMensagem.value)
    }
}
