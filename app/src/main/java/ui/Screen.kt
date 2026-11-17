package ui

sealed class Screen(val rout: String) {
    object Select: Screen("select")
    object Register: Screen("register")
    object Login: Screen("login")
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Shipping: Screen("shipping_screen")
    object Routes: Screen("routes_screen")
    object Alert: Screen("alert_screen")
}