// package com.study.game.mongodb;
//
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
//
// import org.bson.Document;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
//
// import com.mongodb.BasicDBObject;
// import com.mongodb.MongoClient;
// import com.mongodb.client.FindIterable;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.mongodb.client.model.Filters;
//
// /**
//  * MongoSourceTest
//  *
//  * @author liuzhen
//  * @version 1.0.0 2024/12/3 22:28
//  */
// public class MongoSourceTest {
//     private MongoClient mongoClient;
//
//     @Before
//     public void setUp() throws Exception {
//         // 建立连接
//         mongoClient = new MongoClient("127.0.0.1", 27017);
//     }
//
//     @Test
//     public void test01() {
//         MongoDatabase database = mongoClient.getDatabase("model");
//         MongoCollection<Document> collection = database.getCollection("user");
//         // 1. 文档添加
//         // Document document1 =
//         //     Document.parse("{name:'lihua', city:'shanghai', age:99, birth_day:new ISODate('2001-08- 01'), expectSalary:18000}");
//         // collection.insertOne(document1);
//         // Document document2 =
//         //     Document.parse("{name:'刘亦菲', city:'广东', age:18, birth_day:new ISODate('1987-08- 01'), expectSalary:30000}");
//         // collection.insertOne(document2);
//
//         // 2. 文档查询
//         // 2.1.  文档查询
//         Document doc = new Document();
//         // 按expectSalary倒序
//         doc.append("expectSalary", -1);
//         // 查找所有
//         FindIterable<Document> findIterable = collection.find().sort(doc);
//         for (Document subDoc : findIterable) {
//             // System.out.println(subDoc);
//             System.out.println("名称：" + subDoc.getString("name"));
//             System.out.println("城市:" + subDoc.getString("city"));
//             System.out.println("年龄:" + subDoc.getInteger("age"));
//         }
//
//         System.out.println("------------->");
//         // 2.2 条件查询
//         // 构建查询条件
//         BasicDBObject bson = new BasicDBObject("age", 99);
//         // 条件： 年龄大于10
//         BasicDBObject bson2 = new BasicDBObject("age", new BasicDBObject("$gt", 10));
//         // 查询记录获取结果集合
//         FindIterable<Document> filterIterable = collection.find(bson);
//         for (Document subDoc : filterIterable) {
//             System.out.println(subDoc);
//         }
//
//         System.out.println("------------------------------->");
//         // 3. 文档查询过滤
//         Document sdoc = new Document();
//         // 按expectSalary倒序
//         sdoc.append("expectSalary", -1);
//         FindIterable<Document> findIterable2 = collection.find(Filters.gt("expectSalary", 21000)).sort(sdoc);
//         for (Document subDoc : findIterable) {
//             System.out.println(subDoc);
//         }
//
//         System.out.println("------------------------------->");
//         // 4. 插入数据
//         Map<String, Object> map = new HashMap(4);
//         map.put("name", "lz");
//         map.put("age", 999);
//         map.put("birth_day", new Date());
//         map.put("expectSalary", 100000);
//         Document insertDoc = new Document(map);
//         // 插入数据
//         collection.insertOne(insertDoc);
//
//         System.out.println("------------------------------->");
//     }
//
//     @After
//     public void tearDown() throws Exception {
//         // 关闭连接
//         mongoClient.close();
//     }
// }
