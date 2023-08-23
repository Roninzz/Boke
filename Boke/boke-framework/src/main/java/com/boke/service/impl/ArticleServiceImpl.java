package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.constants.SystemConstants;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Article;
import com.boke.domain.entity.ArticleTag;
import com.boke.domain.entity.Category;
import com.boke.domain.entity.Tag;
import com.boke.domain.vo.*;
import com.boke.mapper.ArticleMapper;
import com.boke.service.ArticleService;
import com.boke.service.ArticleTagService;
import com.boke.service.CategoryService;
import com.boke.service.TagService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ArticleService articleService;
    @Override
    public ResponseResult getHotArticleList() {
        //热门文章查询  逢装成ResponseResult返回

        //mybatisplus 查询条件集合
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        //不能把草稿展示出来,必须是正式公开文章
        lqw.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLIC);
        //按照浏览量降序排序
        lqw.orderByDesc(Article::getViewCount);
        //按浏览量查询10篇
        Page<Article> page = new Page<>(1,3);

        page(page,lqw);

        List<Article> articles = page.getRecords();
        //最后返回的不能是article的对象，因为article对象里面是所有的字段，而我们给前端暴漏的字段是只需要一部分
        //所以要重新封装一个前端要的字段，一些隐私的字段要去掉，暴露无关紧要的
        //所以创建个articleVO对象,然后bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    //所有文章查询
    @Override
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, Long categoryId,Long tagId) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();

        //查询条件
        //如果有categoryId。则根据categoryId查询文章
        lqw.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //状态是公共的
        lqw.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_PUBLIC);
        //置顶的文章要显示在前面，对isTop进行降序
        lqw.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lqw);
        //查询分类名称 categoryName
        List<Article> records = page.getRecords();

        //根据标签id查询文章列表
        if (Objects.nonNull(tagId) && tagId > 0){
            LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ArticleTag::getTagId,tagId);
            List<ArticleTag> articleTags = articleTagService.list(lambdaQueryWrapper);
            records.clear();
            articleTags.stream()
                    .map(articleTag -> {
                        Article byId = articleService.getById(articleTag.getArticleId());
                        records.add(byId);
                        return byId;
                    })
                    .collect(Collectors.toList());
            long count = articleTags.stream().count();
            page.setTotal(count);
        }
        records.stream()
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {

                        if (categoryService.getById(article.getCategoryId()) == null){
                            return article.setCategoryName(null);
                        }
                        article.setCategoryName(categoryService.getById(article.getCategoryId()).getCategoryName());
                        return article;
                    }
                })
                .collect(Collectors.toList());
        //封装VO
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(records, ArticleListVo.class);
        articleListVos.stream()
                .map(articleListVo -> {
                    List<TagOptionVo> tagOptionVoList = new ArrayList<>();

                    LambdaQueryWrapper<ArticleTag> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                    lambdaQueryWrapper.eq(ArticleTag::getArticleId,articleListVo.getId());
                    List<ArticleTag> ArticleTags = articleTagService.list(lambdaQueryWrapper);
                    ArticleTags.stream()
                            .map(ArticleTag -> {
                                Tag tag = tagService.getById(ArticleTag.getTagId());
                                return tagOptionVoList.add(BeanCopyUtils.copyBean(tag, TagOptionVo.class));
                            })
                            .collect(Collectors.toList());


                    articleListVo.setTagVOList(tagOptionVoList);
                    return articleListVo;
                })
                .collect(Collectors.toList());

        return ResponseResult.okResult(new PageVo(articleListVos,page.getTotal()));
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //封装vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名称
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if (category != null){
            articleDetailVo.setCategoryName(category.getCategoryName());
        }
        article.setCategoryName(null);
        //查询每个文章的标签
        LambdaQueryWrapper<ArticleTag> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ArticleTag::getArticleId,id);
        List<ArticleTag> ArticleTags = articleTagService.list(lqw);
        List<TagOptionVo> TagOptionVos = new ArrayList<>();
        ArticleTags.stream()
                .map(ArticleTag -> {
                    Tag tag = tagService.getById(ArticleTag.getTagId());
                    return TagOptionVos.add(BeanCopyUtils.copyBean(tag, TagOptionVo.class));
                })
                .collect(Collectors.toList());
        articleDetailVo.setTagVOList(TagOptionVos);
        //查询上一篇文章
        LambdaQueryWrapper<Article> lastArticleLqw = new LambdaQueryWrapper<>();
        lastArticleLqw.lt(Article::getId,id).orderByDesc(Article::getId).last("limit 1");
        Article last = articleService.getOne(lastArticleLqw);
        if (last == null){
            articleDetailVo.setLastArticle(null);
        }else {
            ArticlePaginationVo articleLastVo = BeanCopyUtils.copyBean(last, ArticlePaginationVo.class);
            articleDetailVo.setLastArticle(articleLastVo);
        }

        //查询下一篇文章
        LambdaQueryWrapper<Article> nextArticleLqw = new LambdaQueryWrapper<>();
        nextArticleLqw.gt(Article::getId,id).orderByAsc(Article::getId).last("limit 1");
        Article next = articleService.getOne(nextArticleLqw);
        if (next == null){
            articleDetailVo.setNextArticle(null);
        }else {
            ArticlePaginationVo articleNextVo = BeanCopyUtils.copyBean(next, ArticlePaginationVo.class);
            articleDetailVo.setNextArticle(articleNextVo);
        }

        return ResponseResult.okResult(articleDetailVo);

        //浏览量+1

        //查询浏览量
        //查询点赞量
    }

    @Override
    public ResponseResult getArchivesList(Integer pageNum, Integer pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();

        //查询条件
        //如果有categoryId。则根据categoryId查询文章
        lqw.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //状态是公共的
        lqw.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_PUBLIC);
        //置顶的文章要显示在前面，对isTop进行降序
        lqw.orderByDesc(Article::getCreateTime);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lqw);
        List<Article> records = page.getRecords();
        //封装VO
        List<HotArticleVo> articleListVos = BeanCopyUtils.copyBeanList(records, HotArticleVo.class);

        return ResponseResult.okResult(new PageVo(articleListVos,page.getTotal()));
    }
}
