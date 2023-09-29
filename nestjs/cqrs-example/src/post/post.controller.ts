import { Controller, Get, Inject } from '@nestjs/common';
import { PostQueryService } from './post-query.service';

@Controller('posts')
export class PostController {
  constructor(
    @Inject(PostQueryService)
    private readonly postQueryService: PostQueryService,
  ) {}

  @Get()
  public async getPosts() {
    const posts = await this.postQueryService.getAllPosts();

    return {
      data: posts,
    };
  }
}
