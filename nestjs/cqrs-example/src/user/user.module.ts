import { Module } from '@nestjs/common';
import { UserController } from './controller/user.controller';
import { UserCommandService } from './service/user-command.service';
import CreateUserCommandHandler from './handler/CreateUserCommandHandler';
import { CqrsModule } from '@nestjs/cqrs';
import {
  USER_REPOSITORY_TOKEN,
  UserQueryService,
} from './service/user-query.service';
import { PrismaUserRepository } from './repository/prisma-user.repository';
import WritePostCommandHandler from './handler/WritePostCommandHandler';
import { PrismaModule } from 'nestjs-prisma';

@Module({
  imports: [CqrsModule, PrismaModule.forRoot()],
  controllers: [UserController],
  providers: [
    UserCommandService,
    CreateUserCommandHandler,
    UserQueryService,
    {
      provide: USER_REPOSITORY_TOKEN,
      useClass: PrismaUserRepository,
    },
    WritePostCommandHandler,
  ],
  exports: [
    {
      provide: USER_REPOSITORY_TOKEN,
      useClass: PrismaUserRepository,
    },
  ],
})
export class UserModule {}
