//package com.mumu.test;
//
//import com.mumu.StudyDemoApplication;
//import com.mumu.pojo.Item;
//import com.mumu.repository.ItemRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = StudyDemoApplication.class)
//public class ElasticsearchTest1 {
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Test
//    public void test01(){
//
//        elasticsearchTemplate.createIndex(Item.class);
//        elasticsearchTemplate.putMapping( Item.class );
//    }
//
//    @Test
//    public void index(){
//
//        Item item = new Item(1L, "小米手机7", " 手机",
//                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
//        itemRepository.save(item);
//
//    }
//
//
//    @Test
//    public void indexList() {
//        List<Item> list = new ArrayList<>();
//        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
//        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
//        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
//        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
//        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
//        // 接收对象集合，实现批量新增
//        itemRepository.saveAll(list);
//    }
//
//
//
//}
