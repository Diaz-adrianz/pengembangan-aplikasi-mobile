package pam.pertemuan8.uts.data

object UserSession {

    private val users = mutableListOf(
        User(name = "Pengguna Demo", username = "demo", password = "demo123")
    )

    var currentUser: User? = null
        private set

    val displayName: String get() = currentUser?.name ?: "Pelanggan"
    val firstName: String get() = displayName.trim().substringBefore(" ")

    fun register(name: String, username: String, password: String): Boolean {
        if (users.any { it.username.equals(username, ignoreCase = true) }) return false
        val user = User(name, username, password)
        users.add(user)
        currentUser = user
        return true
    }

    fun login(username: String, password: String): Boolean {
        val user = users.firstOrNull {
            it.username.equals(username, ignoreCase = true) && it.password == password
        }
        currentUser = user
        return user != null
    }

    fun updateName(name: String) {
        currentUser?.name = name
    }

    fun logout() {
        currentUser = null
    }
}