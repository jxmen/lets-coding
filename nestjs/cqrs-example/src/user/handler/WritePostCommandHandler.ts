import { CommandHandler, ICommandHandler } from '@nestjs/cqrs';
import { Inject } from '@nestjs/common';
import { PrismaService } from 'nestjs-prisma';
import WritePostCommand from '../command/WritePostCommand';

@CommandHandler(WritePostCommand)
export default class WritePostCommandHandler
  implements ICommandHandler<WritePostCommand>
{
  constructor(@Inject(PrismaService) private readonly prisma: PrismaService) {}

  async execute(command: WritePostCommand): Promise<void> {
    await this.prisma.post.create({
      data: {
        userId: command.userId,
        title: command.title,
        description: command.description,
      },
    });
  }
}
