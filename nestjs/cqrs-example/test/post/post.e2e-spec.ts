import { Test, TestingModule } from '@nestjs/testing';
import { PostModule } from '../../src/post/post.module';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { PrismaModule, PrismaService } from 'nestjs-prisma';
import { testPrismaModule } from '../../test-utils/test-prisma.module';

describe('Post e2e', () => {
  let app: INestApplication;
  let prismaService: PrismaService;

  beforeEach(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [PostModule],
    })
      .overrideModule(PrismaModule)
      .useModule(testPrismaModule)
      // .overrideProvider(PostQueryService)
      // .useValue(fakePostQueryService)
      .compile();

    app = moduleFixture.createNestApplication();
    await app.init();

    // fakePostCommandService.deleteAll();

    prismaService = app.get(PrismaService);
    await prismaService.post.deleteMany();
  });

  it('/posts (GET)', async () => {
    await prismaService.post.create({
      data: {
        userId: 1,
        title: 'test title',
        description: 'test description',
      },
    });

    const res = await request(app.getHttpServer()).get('/posts').expect(200);

    expect(res.body.data).toHaveLength(1);
    expect(res.body.data[0].userId).toBe(1);
    expect(res.body.data[0].title).toBe('test title');
    expect(res.body.data[0].description).toBe('test description');
  });
});
