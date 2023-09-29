export default class UserResponse {
  private readonly id: number;
  private readonly name: string;

  constructor(user) {
    this.id = user.id;
    this.name = user.name;
  }
}
