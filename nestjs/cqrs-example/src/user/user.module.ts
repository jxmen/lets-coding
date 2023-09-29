import { Module } from '@nestjs/common';
import { UserController } from './user.controller';
import { UserService } from './user.service';
import CreateUserCommandHandler from './handler/CreateUserCommandHandler';
import { CqrsModule } from '@nestjs/cqrs';

@Module({
  imports: [CqrsModule],
  controllers: [UserController],
  providers: [UserService, CreateUserCommandHandler],
})
export class UserModule {}
