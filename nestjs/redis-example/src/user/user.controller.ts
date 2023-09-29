import {
  Body,
  Controller,
  Get,
  Inject,
  Param,
  Put,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { UserService } from './user.service';
import ChangeUserRequest from './dto/ChangeUserRequest';

@Controller('users')
export class UserController {
  constructor(@Inject(UserService) private readonly userService: UserService) {}

  @UsePipes(new ValidationPipe())
  @Put(':id')
  public async setKey(
    @Param('id') id: number,
    @Body() changeUserRequest: ChangeUserRequest,
  ): Promise<void> {
    await this.userService.changeUser(id, changeUserRequest);
  }

  @Get(':id')
  public async getUser(@Param('id') userId: number) {
    return await this.userService.getUser(userId);
  }

  @Get()
  public async getUsers() {
    const users = await this.userService.getUsers();

    return {
      data: users,
    };
  }
}
