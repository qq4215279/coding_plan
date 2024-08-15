// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

public interface MonomerDamageMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MonomerDamageMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 攻击方Id
   * </pre>
   *
   * <code>int32 attId = 1;</code>
   */
  int getAttId();

  /**
   * <pre>
   * 受击方Id
   * </pre>
   *
   * <code>int32 defId = 2;</code>
   */
  int getDefId();

  /**
   * <pre>
   * 伤害值
   * </pre>
   *
   * <code>int32 dam = 3;</code>
   */
  int getDam();

  /**
   * <pre>
   * 受击方血量
   * </pre>
   *
   * <code>int32 defHp = 4;</code>
   */
  int getDefHp();

  /**
   * <pre>
   * 是否暴击
   * </pre>
   *
   * <code>bool crip = 5;</code>
   */
  boolean getCrip();

  /**
   * <pre>
   * 是否命中目标
   * </pre>
   *
   * <code>bool hit = 7;</code>
   */
  boolean getHit();
}