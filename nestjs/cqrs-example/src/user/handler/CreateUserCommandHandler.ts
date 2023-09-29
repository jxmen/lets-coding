import CreateUserCommand from '../command/CreateUserCommand';
import { CommandHandler, ICommandHandler } from '@nestjs/cqrs';
import { PrismaService } from 'nestjs-prisma';
import { Inject } from '@nestjs/common';

@CommandHandler(CreateUserCommand)
export default class CreateUserCommandHandler
  implements ICommandHandler<CreateUserCommand>
{
  constructor(@Inject(PrismaService) private readonly prisma: PrismaService) {}

  async execute(command: CreateUserCommand): Promise<void> {
    await this.prisma.user.create({
      data: {
        name: command.name,
      },
    });
  }
}
