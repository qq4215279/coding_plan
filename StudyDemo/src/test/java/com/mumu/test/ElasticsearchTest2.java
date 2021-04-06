//package com.mumu.test;
//
//import com.mumu.StudyDemoApplication;
//import com.mumu.pojo.Item;
//import com.mumu.repository.ItemRepository;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
//import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
//import org.elasticsearch.search.sort.SortBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
//import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = StudyDemoApplication.class)
//public class ElasticsearchTest2 {
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Test
//    public void testQuery() {
//
//        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery( "title","小米" );
//        Iterable<Item> items = this.itemRepository.search( queryBuilder );
//        items.forEach( item -> System.out.println(item) );
////        items.forEach(System.out::println);
//    }
//
//    @Test
//    public void testNativeQuery1(){
//
//        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 添加基本的分词查询
//        queryBuilder.withQuery( QueryBuilders.matchQuery( "title","小米" ) );
//        // 执行搜索，获取结果
//        Page<Item> items = this.itemRepository.search( queryBuilder.build() );
//        // 打印总条数
//        System.out.println(items.getTotalElements());
//        // 打印总页数
//        System.out.println(items.getTotalPages());
//        items.forEach(System.out::println);
//
//    }
//
//    @Test
//    public void testNativeQuery(){
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        queryBuilder.withQuery( QueryBuilders.termQuery( "category","手机" ) );
//        // 初始化分页参数
//        int page = 0;
//        int size = 3;
//        // 设置分页参数
//        queryBuilder.withPageable( PageRequest.of( page,size ) );
//        Page<Item> items = this.itemRepository.search( queryBuilder.build() );
//
//        // 打印总条数
//        System.out.println(items.getTotalElements());
//        // 打印总页数
//        System.out.println(items.getTotalPages());
//        // 每页大小
//        System.out.println(items.getSize());
//        // 当前页
//        System.out.println(items.getNumber());
//        items.forEach(System.out::println);
//
//    }
//
//    @Test
//    public void testNativeQuery2(){
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        queryBuilder.withQuery( QueryBuilders.termQuery( "category","手机" ) );
//        // 排序
//        queryBuilder.withSort( SortBuilders.fieldSort( "price" ).order( SortOrder.DESC ) );
//        // 执行搜索，获取结果
//        Page<Item> items = this.itemRepository.search(queryBuilder.build());
//        // 打印总条数
//        System.out.println(items.getTotalElements());
//        items.forEach(System.out::println);
//
//    }
//
//    @Test
//    public void testAgg(){
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 不查询任何结果
//        queryBuilder.withSourceFilter( new FetchSourceFilter( new String[]{""},null ) );
//        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
//        queryBuilder.addAggregation( AggregationBuilders.terms( "brands" ).field( "brand" ) );
//        // 2、查询,需要把结果强转为AggregatedPage类型
//        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search( queryBuilder.build() );
//        // 3、解析
//        // 3.1、从结果中取出名为brands的那个聚合，
//        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
//        StringTerms agg = (StringTerms) aggPage.getAggregation( "brands" );
//        // 3.2、获取桶
//        List<StringTerms.Bucket> buckets = agg.getBuckets();
//        // 3.3、遍历
//        buckets.forEach( bucket -> {
//            // 3.4、获取桶中的key，即品牌名称
//            System.out.println(bucket.getKeyAsString());
//            // 3.5、获取桶中的文档数量
//            System.out.println(bucket.getDocCount());
//        } );
//
//    }
//
//    @Test
//    public void testSubAgg(){
//
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//        // 不查询任何结果
//        queryBuilder.withSourceFilter( new FetchSourceFilter( new String[]{""},null ) );
//        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
//        queryBuilder.addAggregation( AggregationBuilders.terms( "brands" ).field( "brand" )
//                // 在品牌聚合桶内进行嵌套聚合，求平均值
//                .subAggregation( AggregationBuilders.avg( "priceAvg" ).field( "price" ) ) );
//        // 2、查询,需要把结果强转为AggregatedPage类型
//        AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository.search( queryBuilder.build() );
//        // 3、解析
//        // 3.1、从结果中取出名为brands的那个聚合，
//        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
//        StringTerms agg = (StringTerms) aggPage.getAggregation( "brands" );
//        // 3.2、获取桶
//        List<StringTerms.Bucket> buckets = agg.getBuckets();
//        // 3.3、遍历
//        for (StringTerms.Bucket bucket : buckets){
//            // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
//            System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");
//
//            // 3.6.获取子聚合结果：
//            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
//            System.out.println("平均售价：" + avg.getValue());
//        }
//
//    }
//
//
//}
