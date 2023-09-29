import { Inject, Injectable } from '@nestjs/common';
import { PrismaService } from 'nestjs-prisma';

@Injectable()
export class PostQueryService {
  constructor(@Inject(PrismaService) private readonly prisma: PrismaService) {}

  async getAllPosts() {
    return await this.prisma.post.findMany();
  }
}
