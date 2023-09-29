import { Inject, Injectable } from '@nestjs/common';
import { PrismaService } from 'nestjs-prisma';
import UserResponse from '../dto/UserResponse';

@Injectable()
export class UserQueryService {
  constructor(
    @Inject(PrismaService)
    private readonly prismaService: PrismaService,
  ) {}

  async getUsers(): Promise<UserResponse[]> {
    const users = await this.prismaService.user.findMany({
      select: {
        id: true,
        name: true,
      },
    });

    return users.map((user) => new UserResponse(user));
  }
}
