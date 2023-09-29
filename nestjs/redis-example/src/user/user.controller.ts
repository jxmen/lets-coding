import {
  Body,
  Controller,
  Inject,
  Param,
  Put,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { UserService } from './user.service';
import ChangeUserRequest from './dto/ChangeUserRequest';

@Controller('user')
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
}
