package com.mumu.java_tools.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * TableDemo
 *
 * @author liuzhen
 *
 * @version 1.0.0 2024/5/21 9:44
 */
public class TableDemo {

    
    /**
     * Table table = HashBasedTable.create() 创建 Table 对象
     *
     * table api:
     *  boolean contains(Object rowKey, Object columnKey)  如果表包含具有指定行和列键的映射，则返回true。
     *  boolean containsRow(Object rowKey)  如果表包含具有指定行键的映射，则返回true。
     *  boolean containsColumn(Object columnKey)  如果表包含具有指定列的映射，则返回true。
     *  boolean containsValue(Object value)  如果表包含具有指定值的映射，则返回true。
     *  boolean equals(Object obj)  将指定对象与此表进行比较以获得相等性。
     *
     *  Set<Table.Cell <R, C, V>> cellSet()  返回所有行键/列键/值三元组的集合。
     *  V get(Object rowKey, Object columnKey)  返回与给定行和列键对应的值，如果不存在此类映射，则返回null。
     *  V put(R rowKey, C columnKey, V value)  将指定的值与指定的键关联。
     *  void putAll(Table<? extends R, ? extends C, ? extends V> table)  将指定表中的所有映射复制到此表。
     *
     *  Set<R> rowKeySet()  返回一组在表中具有一个或多个值的行键。
     *  Map<C, V> row(R rowKey)  返回具有给定行键的所有映射的视图。
     *  Map<R, Map<C, V>> rowMap()  返回一个视图，该视图将每个行键与从列键到值的相应映射相关联。
     *
     *  Set<C> columnKeySet()  返回一组在表中包含一个或多个值的列键。
     *  Map<R, V> column(C columnKey)  返回具有给定列键的所有映射的视图。
     *  Map<C, Map<R, V>> columnMap()  返回一个视图，该视图将每个列键与从行键到值的相应映射相关联。
     *
     *  Collection<V> values()  返回所有值的集合，可能包含重复项。
     *
     *  boolean isEmpty()  如果表不包含映射，则返回true。
     *  int size()  返回表中的行键/列键/值映射的数量。
     *  V remove(Object rowKey, Object columnKey)  删除与给定键关联的映射（如果有）。
     *  void clear()  从表中删除所有映射。
     *  int hashCode()  返回此表的哈希码。
     *
     * @param args args
     * @return void
     * @date 2024/5/21 9:49
     */
    public static void main(String[] args) {
        // create a table
        Table<String, Integer, String> employeeTable = HashBasedTable.create();
        employeeTable.put("IBM", 101, "Mahesh");
        employeeTable.put("IBM", 102, "Ramesh");
        employeeTable.put("IBM", 103, "Suresh");

        employeeTable.put("Microsoft", 111, "Sohan");
        employeeTable.put("Microsoft", 112, "Mohan");
        employeeTable.put("Microsoft", 113, "Rohan");

        employeeTable.put("TCS", 121, "Ram");
        employeeTable.put("TCS", 122, "Shyam");
        employeeTable.put("TCS", 123, "Sunil");

        Set<Table.Cell<String, Integer, String>> cells = employeeTable.cellSet();

        // get Map corresponding to IBM
        Map<Integer, String> ibmEmployees = employeeTable.row("IBM");
        System.out.println("List of IBM Employees");
        for (Map.Entry<Integer, String> entry : ibmEmployees.entrySet()) {
            System.out.println("Emp Id: " + entry.getKey() + ", Name: " + entry.getValue());
        }

        // get all the unique keys of the table
        Set<String> employers = employeeTable.rowKeySet();
        System.out.print("Employers: ");
        for (String employer : employers) {
            System.out.print(employer + " ");
        }
        System.out.println();

        // get a Map corresponding to 102
        Map<String, String> EmployerMap = employeeTable.column(102);
        for (Map.Entry<String, String> entry : EmployerMap.entrySet()) {
            System.out.println("Employer: " + entry.getKey() + ", Name: " + entry.getValue());
        }

    }

    @Test
    public void test() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("row1", "col1", 1);
        table.put("row1", "col2", 2);
        table.put("row2", "col1", 3);

        // 方法1：遍历所有单元格（Table.Cell）  最常用！！！
        for (Table.Cell<String, String, Integer> cell : table.cellSet()) {
            System.out.println("Row: " + cell.getRowKey()
                + ", Column: " + cell.getColumnKey()
                + ", Value: " + cell.getValue());
        }

        // 方法2：遍历行视图（Map 结构）
        // table.rowMap() 返回 Map<R, Map<C, V>>
        for (Map.Entry<String, Map<String, Integer>> rowEntry : table.rowMap().entrySet()) {
            String rowKey = rowEntry.getKey();
            Map<String, Integer> columns = rowEntry.getValue();
            for (Map.Entry<String, Integer> colEntry : columns.entrySet()) {
                String colKey = colEntry.getKey();
                Integer value = colEntry.getValue();
                System.out.println("Row: " + rowKey + ", Col: " + colKey + ", Value: " + value);
            }
        }

        // 方法3：遍历列视图
        // 同理，table.columnMap() 返回 Map<C, Map<R, V>>，你可以按列来遍历：
        for (Map.Entry<String, Map<String, Integer>> colEntry : table.columnMap().entrySet()) {
            String colKey = colEntry.getKey();
            Map<String, Integer> rows = colEntry.getValue();
            for (Map.Entry<String, Integer> rowEntry : rows.entrySet()) {
                String rowKey = rowEntry.getKey();
                Integer value = rowEntry.getValue();
                System.out.println("Col: " + colKey + ", Row: " + rowKey + ", Value: " + value);
            }
        }
    }

}
