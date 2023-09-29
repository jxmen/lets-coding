import { Body, Controller, Get, Inject, Post } from '@nestjs/common';
import CreateUserRequest from '../dto/CreateUserRequest';
import { UserCommandService } from '../service/user-command.service';
import { UserQueryService } from '../service/user-query.service';

@Controller('users')
export class UserController {
  constructor(
    @Inject(UserCommandService)
    private readonly userCommandService: UserCommandService,

    @Inject(UserQueryService)
    private readonly userQueryService: UserQueryService,
  ) {}

  @Post()
  async createUser(@Body() createUserRequest: CreateUserRequest) {
    await this.userCommandService.createUser(createUserRequest);
  }

  @Get()
  async getUsers() {
    const users = await this.userQueryService.getUsers();

    return {
      data: users,
    };
  }
}
