import { Module } from '@nestjs/common';
import { UserController } from './controller/user.controller';
import { UserCommandService } from './service/user-command.service';
import CreateUserCommandHandler from './handler/CreateUserCommandHandler';
import { CqrsModule } from '@nestjs/cqrs';
import { UserQueryService } from './service/user-query.service';

@Module({
  imports: [CqrsModule],
  controllers: [UserController],
  providers: [UserCommandService, CreateUserCommandHandler, UserQueryService],
})
export class UserModule {}
