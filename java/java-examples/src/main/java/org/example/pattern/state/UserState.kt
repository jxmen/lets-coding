package org.example.pattern.state

interface UserState {
    fun signUp()
    fun activate()
    fun delete()
}

class RegisterUserState(
    private val user: User
) : UserState {

    override fun signUp() {
        println("이미 가입된 유저입니다.")
    }

    override fun activate() {
        println("유저가 활성화 되었습니다.")
        user.state = user.activateUserState
    }

    override fun delete() {
        println("활성화 상태에서만 유저를 삭제할 수 있습니다.")
    }
}

class ActivateUserState(
    private val user: User
) : UserState{
    override fun signUp() {
        println("이미 가입한 유저입니다.")
    }

    override fun activate() {
        println("이미 활성화된 유저입니다.")
    }

    override fun delete() {
        println("유저 삭제 처리를 진행합니다.")
        user.state = user.deleteUserState
    }
}

class DeleteUserState(
    private val user: User
): UserState {
    override fun signUp() {
        println("삭제된 유저입니다.")
    }

    override fun activate() {
        println("삭제된 유저입니다.")
    }

    override fun delete() {
        println("이미 삭제된 유저입니다.")
    }
}


class User() {
    var registerUserState: RegisterUserState
    var activateUserState: ActivateUserState
    var deleteUserState: DeleteUserState

    var state: UserState;

    init {
        this.registerUserState = RegisterUserState(this)
        this.activateUserState = ActivateUserState(this)
        this.deleteUserState = DeleteUserState(this)

        this.state = this.registerUserState
    }

    fun activate() {
        this.state.activate()
    }

    fun delete() {
        this.state.delete()
    }
}

fun main() {
    val user = User()
    user.activate()
    user.activate()

    user.delete()
    user.delete()
}
