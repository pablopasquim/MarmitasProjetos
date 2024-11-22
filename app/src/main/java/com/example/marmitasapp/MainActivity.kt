package com.example.marmitasapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen { navigateToCadastroCliente() }
        }
    }

    private fun navigateToCadastroCliente() {
        // Cria o Intent para navegar para CadastroClienteActivity
        val intent = Intent(this, CadastroClienteActivity::class.java)
        startActivity(intent) // Inicia a nova Activity
    }
}

@Composable
fun MainScreen(onNavigateToCadastro: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao Sistema de Marmitas",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = onNavigateToCadastro,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar Cliente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Outras ações, como abrir lista de marmitas */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lista de Marmitas")
        }
    }
}