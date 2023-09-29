import {
  Body,
  Controller,
  Get,
  Inject,
  Param,
  ParseIntPipe,
  Post,
} from '@nestjs/common';
import CreateUserRequest from '../dto/CreateUserRequest';
import { UserCommandService } from '../service/user-command.service';
import { UserQueryService } from '../service/user-query.service';
import WritePostRequest from '../dto/WritePostRequest';

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

  @Post(':id/posts')
  async writePost(
    @Body() writePostRequest: WritePostRequest,
    @Param('id', ParseIntPipe) userId: number,
  ) {
    await this.userCommandService.writePost(userId, writePostRequest);
  }

  @Get(':id/posts')
  async getPosts(@Param('id', ParseIntPipe) userId: number) {
    const posts = await this.userQueryService.getPosts(userId);

    return {
      data: posts,
    };
  }
}
