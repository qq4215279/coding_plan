
package com.mumu.java_tools.dom4j.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;

/**
 * <p>
 * anonymous complex type的 Java 类。
 * <p>
 * 以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{}itemid"/>
 *         &lt;element ref="{}count"/>
 *         &lt;element ref="{}chance"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "itemid", "count", "chance" })
@XmlRootElement(name = "randomObversionValue")
public class RandomObversionValue {

	protected BigInteger itemid;
	protected BigInteger count;
	protected BigInteger chance;

	/**
	 * 获取itemid属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getItemid() {
		return itemid;
	}

	/**
	 * 设置itemid属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setItemid(BigInteger value) {
		this.itemid = value;
	}

	/**
	 * 获取count属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getCount() {
		return count;
	}

	/**
	 * 设置count属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setCount(BigInteger value) {
		this.count = value;
	}

	/**
	 * 获取chance属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getChance() {
		return chance;
	}

	/**
	 * 设置chance属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setChance(BigInteger value) {
		this.chance = value;
	}

}
