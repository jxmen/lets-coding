import CreateUserRequest from '../dto/CreateUserRequest';

export default class CreateUserCommand {
  public readonly name: string;

  constructor(createUserRequest: CreateUserRequest) {
    this.name = createUserRequest.name;
  }
}
