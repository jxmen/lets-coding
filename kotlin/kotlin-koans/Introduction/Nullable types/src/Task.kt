fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
) {
    val email: String = client?.personalInfo?.email ?: return

    // let은 null이 아닐때만 안의 구문이 실행된다
    message?.let { mailer.sendMessage(email, message)} // message를 it으로도 가능
}

class Client(val personalInfo: PersonalInfo?)
class PersonalInfo(val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}
