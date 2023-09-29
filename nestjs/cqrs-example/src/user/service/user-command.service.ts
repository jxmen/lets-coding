import { Injectable } from '@nestjs/common';
import CreateUserRequest from '../dto/CreateUserRequest';
import { CommandBus } from '@nestjs/cqrs';
import CreateUserCommand from '../command/CreateUserCommand';
import WritePostRequest from '../dto/WritePostRequest';
import WritePostCommand from '../command/WritePostCommand';

@Injectable()
export class UserCommandService {
  constructor(private commandBus: CommandBus) {}

  async createUser(createUserRequest: CreateUserRequest) {
    await this.commandBus.execute(new CreateUserCommand(createUserRequest));
  }

  async writePost(userId: number, writePostRequest: WritePostRequest) {
    await this.commandBus.execute(
      new WritePostCommand(userId, writePostRequest),
    );
  }
}
