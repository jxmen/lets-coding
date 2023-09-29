import { Injectable } from '@nestjs/common';
import PostRepository from './post.repository';

@Injectable()
export class PrismaPostRepository implements PostRepository {}
