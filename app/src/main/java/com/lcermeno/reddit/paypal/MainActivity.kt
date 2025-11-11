package com.lcermeno.reddit.paypal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.lcermeno.reddit.paypal.presentation.DashboardKey
import com.lcermeno.reddit.paypal.presentation.LoginKey
import com.lcermeno.reddit.paypal.presentation.dashboard.DashboardScreen
import com.lcermeno.reddit.paypal.presentation.login.LoginScreen
import com.lcermeno.reddit.paypal.ui.theme.PaypalTheme


/**
 * Description:
 * Create a simple Android application with a login screen. The login screen should accept a username and password. Upon entering valid credentials, the user should be redirected to a welcome screen. Ensure that the login process is secure and follows best practices.
 * Requirements:
 * Create a layout for the login screen with EditText fields for username and password, and a login button.
 * Implement validation to ensure that both username and password fields are not empty.
 *
 * Implement a secure authentication mechanism to verify the username and password. You can use hardcoded credentials for simplicity.
 * After successful authentication, navigate the user to a welcome screen displaying a welcome message.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaypalTheme {
                MainNavigation()
            }
        }
    }
}



@Composable
fun MainNavigation () {

    val backStack = rememberNavBackStack(LoginKey)

    val entryProvider = entryProvider {
        entry<LoginKey> {
            LoginScreen {
                backStack.removeLastOrNull()
                backStack.add(DashboardKey)
            }
        }

        entry<DashboardKey> {
            DashboardScreen()
        }
    }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider,
        onBack = {
            backStack.removeLastOrNull()
        }
    )
}