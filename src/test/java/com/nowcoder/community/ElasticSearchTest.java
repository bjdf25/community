package com.nowcoder.community;

import com.nowcoder.community.dao.IDiscussPostDao;
import com.nowcoder.community.dao.elasticsearch.DiscussPostRepository;
import com.nowcoder.community.entity.DiscussPost;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ElasticSearchTest {

    @Autowired
    private IDiscussPostDao  discussPostDao;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testInsert(){
        discussPostRepository.save(discussPostDao.selectDiscussPostById(241));
        discussPostRepository.save(discussPostDao.selectDiscussPostById(242));
        discussPostRepository.save(discussPostDao.selectDiscussPostById(243));
    }

    @Test
    public void testInsetList(){
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(101,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(102,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(103,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(111,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(112,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(131,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(132,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(133,0,100));
        discussPostRepository.saveAll(discussPostDao.selectDiscussPosts(134,0,100));
    }

    @Test
    public void testUpdate(){
        DiscussPost discussPost = discussPostDao.selectDiscussPostById(231);
        discussPost.setContent("我是新人，求罩");
        discussPostRepository.save(discussPost);
    }

    @Test
    public void testDelete(){
        discussPostRepository.deleteById(231);
    }

    @Test
    public void testSelectByRepository(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0,10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        Page<DiscussPost> page = discussPostRepository.search(searchQuery);
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getSize());
        for (DiscussPost discussPost : page) {
            System.out.println(discussPost);
        }
    }
}
