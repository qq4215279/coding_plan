// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

public interface ChangeAttackTeamMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ChangeAttackTeamMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 攻击编队id
   * </pre>
   *
   * <code>int32 attTeamId = 1;</code>
   */
  int getAttTeamId();

  /**
   * <pre>
   * 防守编队id
   * </pre>
   *
   * <code>int32 defTeamId = 2;</code>
   */
  int getDefTeamId();

  /**
   * <pre>
   * 攻击
   * </pre>
   *
   * <code>int32 reloadCd = 3;</code>
   */
  int getReloadCd();
}