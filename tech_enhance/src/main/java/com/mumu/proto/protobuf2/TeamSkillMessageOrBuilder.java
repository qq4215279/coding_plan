// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

public interface TeamSkillMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TeamSkillMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 技能id
   * </pre>
   *
   * <code>int32 skillId = 1;</code>
   */
  int getSkillId();

  /**
   * <pre>
   * 技能阶段
   * </pre>
   *
   * <code>int32 step = 2;</code>
   */
  int getStep();

  /**
   * <pre>
   * 释放技能的编队id
   * </pre>
   *
   * <code>int32 teamId = 3;</code>
   */
  int getTeamId();

  /**
   * <pre>
   * 受技能影响的编队
   * </pre>
   *
   * <code>repeated .TeamSkillInfo teamList = 4;</code>
   */
  java.util.List<com.mumu.proto.protobuf2.TeamSkillInfo> 
      getTeamListList();
  /**
   * <pre>
   * 受技能影响的编队
   * </pre>
   *
   * <code>repeated .TeamSkillInfo teamList = 4;</code>
   */
  com.mumu.proto.protobuf2.TeamSkillInfo getTeamList(int index);
  /**
   * <pre>
   * 受技能影响的编队
   * </pre>
   *
   * <code>repeated .TeamSkillInfo teamList = 4;</code>
   */
  int getTeamListCount();
  /**
   * <pre>
   * 受技能影响的编队
   * </pre>
   *
   * <code>repeated .TeamSkillInfo teamList = 4;</code>
   */
  java.util.List<? extends com.mumu.proto.protobuf2.TeamSkillInfoOrBuilder> 
      getTeamListOrBuilderList();
  /**
   * <pre>
   * 受技能影响的编队
   * </pre>
   *
   * <code>repeated .TeamSkillInfo teamList = 4;</code>
   */
  com.mumu.proto.protobuf2.TeamSkillInfoOrBuilder getTeamListOrBuilder(
      int index);
}