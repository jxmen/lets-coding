import { Inject, Injectable } from '@nestjs/common';
import UserRepository from '../user.repository';
import { PrismaService } from 'nestjs-prisma';

@Injectable()
export class PrismaUserRepository implements UserRepository {
  constructor(
    @Inject(PrismaService)
    private readonly prismaService: PrismaService,
  ) {}

  async findAll() {
    const users = await this.prismaService.user.findMany({
      select: {
        id: true,
        name: true,
      },
    });

    return users;
  }

  async findAllPosts(userId: number) {
    const posts = await this.prismaService.post.findMany({
      where: { userId },
    });

    return posts;
  }
}
