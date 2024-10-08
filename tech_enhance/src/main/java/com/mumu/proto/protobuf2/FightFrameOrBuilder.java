// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

public interface FightFrameOrBuilder extends
    // @@protoc_insertion_point(interface_extends:FightFrame)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 战场Id
   * </pre>
   *
   * <code>int32 id = 1;</code>
   */
  int getId();

  /**
   * <pre>
   * 帧号
   * </pre>
   *
   * <code>int32 frameNum = 2;</code>
   */
  int getFrameNum();

  /**
   * <pre>
   * 当前时间
   * </pre>
   *
   * <code>int32 time = 3;</code>
   */
  int getTime();

  /**
   * <pre>
   * 这帧中包含的协议
   * </pre>
   *
   * <code>repeated .FightMessage messages = 10;</code>
   */
  java.util.List<com.mumu.proto.protobuf2.FightMessage> 
      getMessagesList();
  /**
   * <pre>
   * 这帧中包含的协议
   * </pre>
   *
   * <code>repeated .FightMessage messages = 10;</code>
   */
  com.mumu.proto.protobuf2.FightMessage getMessages(int index);
  /**
   * <pre>
   * 这帧中包含的协议
   * </pre>
   *
   * <code>repeated .FightMessage messages = 10;</code>
   */
  int getMessagesCount();
  /**
   * <pre>
   * 这帧中包含的协议
   * </pre>
   *
   * <code>repeated .FightMessage messages = 10;</code>
   */
  java.util.List<? extends com.mumu.proto.protobuf2.FightMessageOrBuilder> 
      getMessagesOrBuilderList();
  /**
   * <pre>
   * 这帧中包含的协议
   * </pre>
   *
   * <code>repeated .FightMessage messages = 10;</code>
   */
  com.mumu.proto.protobuf2.FightMessageOrBuilder getMessagesOrBuilder(
      int index);
}
