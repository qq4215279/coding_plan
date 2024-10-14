
package com.mumu.java_tools.dom4j.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
 *       &lt;sequence>
 *         &lt;element ref="{}uniqueId"/>
 *         &lt;element ref="{}itemId"/>
 *         &lt;element ref="{}itemType"/>
 *         &lt;element ref="{}subType"/>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}typeName"/>
 *         &lt;element ref="{}visual"/>
 *         &lt;element ref="{}unit"/>
 *         &lt;element ref="{}describe"/>
 *         &lt;element ref="{}effectDescribe"/>
 *         &lt;element ref="{}extraIconName"/>
 *         &lt;element ref="{}icon"/>
 *         &lt;element ref="{}tips"/>
 *         &lt;element ref="{}rewardShowTips"/>
 *         &lt;element ref="{}rewardDisplay"/>
 *         &lt;element ref="{}storageLimit"/>
 *         &lt;element ref="{}displayState"/>
 *         &lt;element ref="{}isRandom"/>
 *         &lt;element ref="{}isCanUse"/>
 *         &lt;element ref="{}isCanBatchUse"/>
 *         &lt;element ref="{}useEffect"/>
 *         &lt;element ref="{}isCanBuy"/>
 *         &lt;element ref="{}buyVipLimit"/>
 *         &lt;element ref="{}buyEffect"/>
 *         &lt;element ref="{}isCanSell"/>
 *         &lt;element ref="{}sellEffect"/>
 *         &lt;element ref="{}sellCondition"/>
 *         &lt;element ref="{}isCanGive"/>
 *         &lt;element ref="{}giveEffect"/>
 *         &lt;element ref="{}load"/>
 *         &lt;element ref="{}loadEffect"/>
 *         &lt;element ref="{}synthesis"/>
 *         &lt;element ref="{}isCanEquip"/>
 *         &lt;element ref="{}effectiveTime"/>
 *         &lt;element ref="{}effectiveTimeLimit"/>
 *         &lt;element ref="{}obversionValue"/>
 *         &lt;element ref="{}isCanJump"/>
 *         &lt;element ref="{}jumpEffect"/>
 *         &lt;element ref="{}recordDataDays"/>
 *         &lt;element ref="{}isCanMail"/>
 *         &lt;element ref="{}itemGoldValue"/>
 *         &lt;choice>
 *           &lt;element ref="{}clearInMonth"/>
 *           &lt;element ref="{}clearInWeek" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "uniqueId", "itemId", "itemType", "subType", "name", "typeName", "visual", "unit", "describe", "effectDescribe",
		"extraIconName", "icon", "tips", "rewardShowTips", "rewardDisplay", "storageLimit", "displayState", "isRandom", "isCanUse", "isCanBatchUse",
		"useEffect", "isCanBuy", "buyVipLimit", "buyEffect", "isCanSell", "sellEffect", "sellCondition", "isCanGive", "giveEffect", "load", "loadEffect",
		"synthesis", "isCanEquip", "effectiveTime", "effectiveTimeLimit", "obversionValue", "randomObversionValue", "isCanJump", "jumpEffect", "recordDataDays",
		"isCanMail", "itemGoldValue", "clearInMonth", "clearInWeek" })
@XmlRootElement(name = "config_itemInfo")
public class ConfigItemInfo {

	@XmlElement(required = true)
	protected BigInteger uniqueId;
	@XmlElement(required = true)
	protected BigInteger itemId;
	@XmlElement(required = true)
	protected BigInteger itemType;
	@XmlElement(required = true)
	protected BigInteger subType;
	@XmlElement(required = true)
	protected String name;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String typeName;
	@XmlElement(required = true)
	protected BigInteger visual;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String unit;
	@XmlElement(required = true)
	protected String describe;
	@XmlElement(required = true)
	protected String effectDescribe;
	@XmlElement(required = true)
	protected String extraIconName;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String icon;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String tips;
	@XmlElement(required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NCName")
	protected String rewardShowTips;
	@XmlElement(required = true)
	protected RewardDisplay rewardDisplay;
	@XmlElement(required = true)
	protected BigInteger storageLimit;
	@XmlElement(required = true)
	protected BigInteger displayState;
	@XmlElement(required = true)
	protected BigInteger isRandom;
	@XmlElement(required = true)
	protected BigInteger isCanUse;
	@XmlElement(required = true)
	protected BigInteger isCanBatchUse;
	@XmlElement(required = true)
	protected BigInteger useEffect;
	@XmlElement(required = true)
	protected BigInteger isCanBuy;
	@XmlElement(required = true)
	protected BigInteger buyVipLimit;
	@XmlElement(required = true)
	protected BuyEffect buyEffect;
	@XmlElement(required = true)
	protected BigInteger isCanSell;
	@XmlElement(required = true)
	protected SellEffect sellEffect;
	@XmlElement(required = true)
	protected SellCondition sellCondition;
	@XmlElement(required = true)
	protected BigInteger isCanGive;
	@XmlElement(required = true)
	protected GiveEffect giveEffect;
	@XmlElement(required = true)
	protected BigInteger load;
	@XmlElement(required = true)
	protected LoadEffect loadEffect;
	@XmlElement(required = true)
	protected BigInteger synthesis;
	@XmlElement(required = true)
	protected BigInteger isCanEquip;
	@XmlElement(required = true)
	protected BigInteger effectiveTime;
	@XmlElement(required = true)
	protected BigInteger effectiveTimeLimit;
	@XmlElement(required = true)
	protected ObversionValue obversionValue;
	@XmlElement(required = true)
	protected List<RandomObversionValue> randomObversionValue;
	@XmlElement(required = true)
	protected BigInteger isCanJump;
	protected double jumpEffect;
	@XmlElement(required = true)
	protected BigInteger recordDataDays;
	@XmlElement(required = true)
	protected BigInteger isCanMail;
	@XmlElement(required = true)
	protected BigInteger itemGoldValue;
	protected List<BigInteger> clearInMonth;
	protected List<BigInteger> clearInWeek;

	/**
	 * 获取uniqueId属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getUniqueId() {
		return uniqueId;
	}

	/**
	 * 设置uniqueId属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setUniqueId(BigInteger value) {
		this.uniqueId = value;
	}

	/**
	 * 获取itemId属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getItemId() {
		return itemId;
	}

	/**
	 * 设置itemId属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setItemId(BigInteger value) {
		this.itemId = value;
	}

	/**
	 * 获取itemType属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getItemType() {
		return itemType;
	}

	/**
	 * 设置itemType属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setItemType(BigInteger value) {
		this.itemType = value;
	}

	/**
	 * 获取subType属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getSubType() {
		return subType;
	}

	/**
	 * 设置subType属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setSubType(BigInteger value) {
		this.subType = value;
	}

	/**
	 * 获取name属性的值。
	 * @return possible object is {@link String }
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * 获取typeName属性的值。
	 * @return possible object is {@link String }
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 设置typeName属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setTypeName(String value) {
		this.typeName = value;
	}

	/**
	 * 获取visual属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getVisual() {
		return visual;
	}

	/**
	 * 设置visual属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setVisual(BigInteger value) {
		this.visual = value;
	}

	/**
	 * 获取unit属性的值。
	 * @return possible object is {@link String }
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置unit属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setUnit(String value) {
		this.unit = value;
	}

	/**
	 * 获取describe属性的值。
	 * @return possible object is {@link String }
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * 设置describe属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setDescribe(String value) {
		this.describe = value;
	}

	/**
	 * 获取effectDescribe属性的值。
	 * @return possible object is {@link String }
	 */
	public String getEffectDescribe() {
		return effectDescribe;
	}

	/**
	 * 设置effectDescribe属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setEffectDescribe(String value) {
		this.effectDescribe = value;
	}

	/**
	 * 获取extraIconName属性的值。
	 * @return possible object is {@link String }
	 */
	public String getExtraIconName() {
		return extraIconName;
	}

	/**
	 * 设置extraIconName属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setExtraIconName(String value) {
		this.extraIconName = value;
	}

	/**
	 * 获取icon属性的值。
	 * @return possible object is {@link String }
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置icon属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setIcon(String value) {
		this.icon = value;
	}

	/**
	 * 获取tips属性的值。
	 * @return possible object is {@link String }
	 */
	public String getTips() {
		return tips;
	}

	/**
	 * 设置tips属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setTips(String value) {
		this.tips = value;
	}

	/**
	 * 获取rewardShowTips属性的值。
	 * @return possible object is {@link String }
	 */
	public String getRewardShowTips() {
		return rewardShowTips;
	}

	/**
	 * 设置rewardShowTips属性的值。
	 * @param value allowed object is {@link String }
	 */
	public void setRewardShowTips(String value) {
		this.rewardShowTips = value;
	}

	/**
	 * 获取rewardDisplay属性的值。
	 * @return possible object is {@link RewardDisplay }
	 */
	public RewardDisplay getRewardDisplay() {
		return rewardDisplay;
	}

	/**
	 * 设置rewardDisplay属性的值。
	 * @param value allowed object is {@link RewardDisplay }
	 */
	public void setRewardDisplay(RewardDisplay value) {
		this.rewardDisplay = value;
	}

	/**
	 * 获取storageLimit属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getStorageLimit() {
		return storageLimit;
	}

	/**
	 * 设置storageLimit属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setStorageLimit(BigInteger value) {
		this.storageLimit = value;
	}

	/**
	 * 获取displayState属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getDisplayState() {
		return displayState;
	}

	/**
	 * 设置displayState属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setDisplayState(BigInteger value) {
		this.displayState = value;
	}

	/**
	 * 获取isRandom属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsRandom() {
		return isRandom;
	}

	/**
	 * 设置isRandom属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsRandom(BigInteger value) {
		this.isRandom = value;
	}

	/**
	 * 获取isCanUse属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanUse() {
		return isCanUse;
	}

	/**
	 * 设置isCanUse属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanUse(BigInteger value) {
		this.isCanUse = value;
	}

	/**
	 * 获取isCanBatchUse属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanBatchUse() {
		return isCanBatchUse;
	}

	/**
	 * 设置isCanBatchUse属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanBatchUse(BigInteger value) {
		this.isCanBatchUse = value;
	}

	/**
	 * 获取useEffect属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getUseEffect() {
		return useEffect;
	}

	/**
	 * 设置useEffect属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setUseEffect(BigInteger value) {
		this.useEffect = value;
	}

	/**
	 * 获取isCanBuy属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanBuy() {
		return isCanBuy;
	}

	/**
	 * 设置isCanBuy属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanBuy(BigInteger value) {
		this.isCanBuy = value;
	}

	/**
	 * 获取buyVipLimit属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getBuyVipLimit() {
		return buyVipLimit;
	}

	/**
	 * 设置buyVipLimit属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setBuyVipLimit(BigInteger value) {
		this.buyVipLimit = value;
	}

	/**
	 * 获取buyEffect属性的值。
	 * @return possible object is {@link BuyEffect }
	 */
	public BuyEffect getBuyEffect() {
		return buyEffect;
	}

	/**
	 * 设置buyEffect属性的值。
	 * @param value allowed object is {@link BuyEffect }
	 */
	public void setBuyEffect(BuyEffect value) {
		this.buyEffect = value;
	}

	/**
	 * 获取isCanSell属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanSell() {
		return isCanSell;
	}

	/**
	 * 设置isCanSell属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanSell(BigInteger value) {
		this.isCanSell = value;
	}

	/**
	 * 获取sellEffect属性的值。
	 * @return possible object is {@link SellEffect }
	 */
	public SellEffect getSellEffect() {
		return sellEffect;
	}

	/**
	 * 设置sellEffect属性的值。
	 * @param value allowed object is {@link SellEffect }
	 */
	public void setSellEffect(SellEffect value) {
		this.sellEffect = value;
	}

	/**
	 * 获取sellCondition属性的值。
	 * @return possible object is {@link SellCondition }
	 */
	public SellCondition getSellCondition() {
		return sellCondition;
	}

	/**
	 * 设置sellCondition属性的值。
	 * @param value allowed object is {@link SellCondition }
	 */
	public void setSellCondition(SellCondition value) {
		this.sellCondition = value;
	}

	/**
	 * 获取isCanGive属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanGive() {
		return isCanGive;
	}

	/**
	 * 设置isCanGive属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanGive(BigInteger value) {
		this.isCanGive = value;
	}

	/**
	 * 获取giveEffect属性的值。
	 * @return possible object is {@link GiveEffect }
	 */
	public GiveEffect getGiveEffect() {
		return giveEffect;
	}

	/**
	 * 设置giveEffect属性的值。
	 * @param value allowed object is {@link GiveEffect }
	 */
	public void setGiveEffect(GiveEffect value) {
		this.giveEffect = value;
	}

	/**
	 * 获取load属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getLoad() {
		return load;
	}

	/**
	 * 设置load属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setLoad(BigInteger value) {
		this.load = value;
	}

	/**
	 * 获取loadEffect属性的值。
	 * @return possible object is {@link LoadEffect }
	 */
	public LoadEffect getLoadEffect() {
		return loadEffect;
	}

	/**
	 * 设置loadEffect属性的值。
	 * @param value allowed object is {@link LoadEffect }
	 */
	public void setLoadEffect(LoadEffect value) {
		this.loadEffect = value;
	}

	/**
	 * 获取synthesis属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getSynthesis() {
		return synthesis;
	}

	/**
	 * 设置synthesis属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setSynthesis(BigInteger value) {
		this.synthesis = value;
	}

	/**
	 * 获取isCanEquip属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanEquip() {
		return isCanEquip;
	}

	/**
	 * 设置isCanEquip属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanEquip(BigInteger value) {
		this.isCanEquip = value;
	}

	/**
	 * 获取effectiveTime属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getEffectiveTime() {
		return effectiveTime;
	}

	/**
	 * 设置effectiveTime属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setEffectiveTime(BigInteger value) {
		this.effectiveTime = value;
	}

	/**
	 * 获取effectiveTimeLimit属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getEffectiveTimeLimit() {
		return effectiveTimeLimit;
	}

	/**
	 * 设置effectiveTimeLimit属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setEffectiveTimeLimit(BigInteger value) {
		this.effectiveTimeLimit = value;
	}

	/**
	 * 获取obversionValue属性的值。
	 * @return possible object is {@link ObversionValue }
	 */
	public ObversionValue getObversionValue() {
		return obversionValue;
	}

	/**
	 * 设置obversionValue属性的值。
	 * @param value allowed object is {@link ObversionValue }
	 */
	public void setObversionValue(ObversionValue value) {
		this.obversionValue = value;
	}

	public List<RandomObversionValue> getRandomObversionValue() {
		if (randomObversionValue == null) {
			randomObversionValue = new ArrayList<RandomObversionValue>();
		}
		return randomObversionValue;
	}

	/**
	 * 获取isCanJump属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanJump() {
		return isCanJump;
	}

	/**
	 * 设置isCanJump属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanJump(BigInteger value) {
		this.isCanJump = value;
	}

	/**
	 * 获取jumpEffect属性的值。
	 */
	public double getJumpEffect() {
		return jumpEffect;
	}

	/**
	 * 设置jumpEffect属性的值。
	 */
	public void setJumpEffect(double value) {
		this.jumpEffect = value;
	}

	/**
	 * 获取recordDataDays属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getRecordDataDays() {
		return recordDataDays;
	}

	/**
	 * 设置recordDataDays属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setRecordDataDays(BigInteger value) {
		this.recordDataDays = value;
	}

	/**
	 * 获取isCanMail属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getIsCanMail() {
		return isCanMail;
	}

	/**
	 * 设置isCanMail属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setIsCanMail(BigInteger value) {
		this.isCanMail = value;
	}

	/**
	 * 获取itemGoldValue属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public BigInteger getItemGoldValue() {
		return itemGoldValue;
	}

	/**
	 * 设置itemGoldValue属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setItemGoldValue(BigInteger value) {
		this.itemGoldValue = value;
	}

	/**
	 * 获取clearInMonth属性的值。
	 * @return possible object is {@link BigInteger }
	 */
	public List<BigInteger> getClearInMonth() {
		return clearInMonth;
	}

	/**
	 * 设置clearInMonth属性的值。
	 * @param value allowed object is {@link BigInteger }
	 */
	public void setClearInMonth(List<BigInteger> value) {
		this.clearInMonth = value;
	}

	/**
	 * Gets the value of the clearInWeek property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the clearInWeek property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getClearInWeek().add(newItem);
	 * </pre>
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link BigInteger }
	 */
	public List<BigInteger> getClearInWeek() {
		if (clearInWeek == null) {
			clearInWeek = new ArrayList<BigInteger>();
		}
		return this.clearInWeek;
	}

}
