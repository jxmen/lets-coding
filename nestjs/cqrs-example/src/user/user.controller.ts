import { Body, Controller, Inject, Post } from '@nestjs/common';
import CreateUserRequest from './dto/CreateUserRequest';
import { UserService } from './user.service';

@Controller('users')
export class UserController {
  private readonly userService: UserService;

  constructor(@Inject(UserService) userService: UserService) {
    this.userService = userService;
  }

  @Post()
  async createUser(@Body() createUserRequest: CreateUserRequest) {
    await this.userService.createUser(createUserRequest);
  }
}
