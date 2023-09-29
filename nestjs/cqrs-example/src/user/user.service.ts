import { Injectable } from '@nestjs/common';
import CreateUserRequest from './dto/CreateUserRequest';
import { CommandBus } from '@nestjs/cqrs';
import CreateUserCommand from './command/CreateUserCommand';

@Injectable()
export class UserService {
  constructor(private commandBus: CommandBus) {}

  async createUser(createUserRequest: CreateUserRequest) {
    await this.commandBus.execute(new CreateUserCommand(createUserRequest));
  }
}
