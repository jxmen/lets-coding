import { Module } from '@nestjs/common';
import { PostController } from './post.controller';
import { PostQueryService } from './post-query.service';
import { PrismaModule } from 'nestjs-prisma';

@Module({
  imports: [PrismaModule],
  controllers: [PostController],
  providers: [PostQueryService],
})
export class PostModule {}
