// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

/**
 * <pre>
 * 技能信息
 * </pre>
 *
 * Protobuf type {@code TeamSkillInfo}
 */
public  final class TeamSkillInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TeamSkillInfo)
    TeamSkillInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TeamSkillInfo.newBuilder() to construct.
  private TeamSkillInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TeamSkillInfo() {
    infoList_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TeamSkillInfo(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            teamId_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              infoList_ = new java.util.ArrayList<com.mumu.proto.protobuf2.PlayerSkillInfo>();
              mutable_bitField0_ |= 0x00000002;
            }
            infoList_.add(
                input.readMessage(com.mumu.proto.protobuf2.PlayerSkillInfo.parser(), extensionRegistry));
            break;
          }
          case 24: {

            shieldOffset_ = input.readInt32();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        infoList_ = java.util.Collections.unmodifiableList(infoList_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_TeamSkillInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_TeamSkillInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.mumu.proto.protobuf2.TeamSkillInfo.class, com.mumu.proto.protobuf2.TeamSkillInfo.Builder.class);
  }

  private int bitField0_;
  public static final int TEAMID_FIELD_NUMBER = 1;
  private int teamId_;
  /**
   * <pre>
   * 编队id
   * </pre>
   *
   * <code>int32 teamId = 1;</code>
   */
  public int getTeamId() {
    return teamId_;
  }

  public static final int INFOLIST_FIELD_NUMBER = 2;
  private java.util.List<com.mumu.proto.protobuf2.PlayerSkillInfo> infoList_;
  /**
   * <pre>
   * 编队中受到技能影响
   * </pre>
   *
   * <code>repeated .PlayerSkillInfo infoList = 2;</code>
   */
  public java.util.List<com.mumu.proto.protobuf2.PlayerSkillInfo> getInfoListList() {
    return infoList_;
  }
  /**
   * <pre>
   * 编队中受到技能影响
   * </pre>
   *
   * <code>repeated .PlayerSkillInfo infoList = 2;</code>
   */
  public java.util.List<? extends com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder> 
      getInfoListOrBuilderList() {
    return infoList_;
  }
  /**
   * <pre>
   * 编队中受到技能影响
   * </pre>
   *
   * <code>repeated .PlayerSkillInfo infoList = 2;</code>
   */
  public int getInfoListCount() {
    return infoList_.size();
  }
  /**
   * <pre>
   * 编队中受到技能影响
   * </pre>
   *
   * <code>repeated .PlayerSkillInfo infoList = 2;</code>
   */
  public com.mumu.proto.protobuf2.PlayerSkillInfo getInfoList(int index) {
    return infoList_.get(index);
  }
  /**
   * <pre>
   * 编队中受到技能影响
   * </pre>
   *
   * <code>repeated .PlayerSkillInfo infoList = 2;</code>
   */
  public com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder getInfoListOrBuilder(
      int index) {
    return infoList_.get(index);
  }

  public static final int SHIELDOFFSET_FIELD_NUMBER = 3;
  private int shieldOffset_;
  /**
   * <pre>
   * 护盾吸收伤害
   * </pre>
   *
   * <code>int32 shieldOffset = 3;</code>
   */
  public int getShieldOffset() {
    return shieldOffset_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (teamId_ != 0) {
      output.writeInt32(1, teamId_);
    }
    for (int i = 0; i < infoList_.size(); i++) {
      output.writeMessage(2, infoList_.get(i));
    }
    if (shieldOffset_ != 0) {
      output.writeInt32(3, shieldOffset_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (teamId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, teamId_);
    }
    for (int i = 0; i < infoList_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, infoList_.get(i));
    }
    if (shieldOffset_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, shieldOffset_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.mumu.proto.protobuf2.TeamSkillInfo)) {
      return super.equals(obj);
    }
    com.mumu.proto.protobuf2.TeamSkillInfo other = (com.mumu.proto.protobuf2.TeamSkillInfo) obj;

    if (getTeamId()
        != other.getTeamId()) return false;
    if (!getInfoListList()
        .equals(other.getInfoListList())) return false;
    if (getShieldOffset()
        != other.getShieldOffset()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TEAMID_FIELD_NUMBER;
    hash = (53 * hash) + getTeamId();
    if (getInfoListCount() > 0) {
      hash = (37 * hash) + INFOLIST_FIELD_NUMBER;
      hash = (53 * hash) + getInfoListList().hashCode();
    }
    hash = (37 * hash) + SHIELDOFFSET_FIELD_NUMBER;
    hash = (53 * hash) + getShieldOffset();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.TeamSkillInfo parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.mumu.proto.protobuf2.TeamSkillInfo prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * 技能信息
   * </pre>
   *
   * Protobuf type {@code TeamSkillInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TeamSkillInfo)
      com.mumu.proto.protobuf2.TeamSkillInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_TeamSkillInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_TeamSkillInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.mumu.proto.protobuf2.TeamSkillInfo.class, com.mumu.proto.protobuf2.TeamSkillInfo.Builder.class);
    }

    // Construct using com.mumu.proto.protobuf2.TeamSkillInfo.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getInfoListFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      teamId_ = 0;

      if (infoListBuilder_ == null) {
        infoList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        infoListBuilder_.clear();
      }
      shieldOffset_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_TeamSkillInfo_descriptor;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.TeamSkillInfo getDefaultInstanceForType() {
      return com.mumu.proto.protobuf2.TeamSkillInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.TeamSkillInfo build() {
      com.mumu.proto.protobuf2.TeamSkillInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.TeamSkillInfo buildPartial() {
      com.mumu.proto.protobuf2.TeamSkillInfo result = new com.mumu.proto.protobuf2.TeamSkillInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.teamId_ = teamId_;
      if (infoListBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          infoList_ = java.util.Collections.unmodifiableList(infoList_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.infoList_ = infoList_;
      } else {
        result.infoList_ = infoListBuilder_.build();
      }
      result.shieldOffset_ = shieldOffset_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.mumu.proto.protobuf2.TeamSkillInfo) {
        return mergeFrom((com.mumu.proto.protobuf2.TeamSkillInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.mumu.proto.protobuf2.TeamSkillInfo other) {
      if (other == com.mumu.proto.protobuf2.TeamSkillInfo.getDefaultInstance()) return this;
      if (other.getTeamId() != 0) {
        setTeamId(other.getTeamId());
      }
      if (infoListBuilder_ == null) {
        if (!other.infoList_.isEmpty()) {
          if (infoList_.isEmpty()) {
            infoList_ = other.infoList_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureInfoListIsMutable();
            infoList_.addAll(other.infoList_);
          }
          onChanged();
        }
      } else {
        if (!other.infoList_.isEmpty()) {
          if (infoListBuilder_.isEmpty()) {
            infoListBuilder_.dispose();
            infoListBuilder_ = null;
            infoList_ = other.infoList_;
            bitField0_ = (bitField0_ & ~0x00000002);
            infoListBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getInfoListFieldBuilder() : null;
          } else {
            infoListBuilder_.addAllMessages(other.infoList_);
          }
        }
      }
      if (other.getShieldOffset() != 0) {
        setShieldOffset(other.getShieldOffset());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.mumu.proto.protobuf2.TeamSkillInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.mumu.proto.protobuf2.TeamSkillInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int teamId_ ;
    /**
     * <pre>
     * 编队id
     * </pre>
     *
     * <code>int32 teamId = 1;</code>
     */
    public int getTeamId() {
      return teamId_;
    }
    /**
     * <pre>
     * 编队id
     * </pre>
     *
     * <code>int32 teamId = 1;</code>
     */
    public Builder setTeamId(int value) {
      
      teamId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 编队id
     * </pre>
     *
     * <code>int32 teamId = 1;</code>
     */
    public Builder clearTeamId() {
      
      teamId_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.mumu.proto.protobuf2.PlayerSkillInfo> infoList_ =
      java.util.Collections.emptyList();
    private void ensureInfoListIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        infoList_ = new java.util.ArrayList<com.mumu.proto.protobuf2.PlayerSkillInfo>(infoList_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.mumu.proto.protobuf2.PlayerSkillInfo, com.mumu.proto.protobuf2.PlayerSkillInfo.Builder, com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder> infoListBuilder_;

    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public java.util.List<com.mumu.proto.protobuf2.PlayerSkillInfo> getInfoListList() {
      if (infoListBuilder_ == null) {
        return java.util.Collections.unmodifiableList(infoList_);
      } else {
        return infoListBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public int getInfoListCount() {
      if (infoListBuilder_ == null) {
        return infoList_.size();
      } else {
        return infoListBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public com.mumu.proto.protobuf2.PlayerSkillInfo getInfoList(int index) {
      if (infoListBuilder_ == null) {
        return infoList_.get(index);
      } else {
        return infoListBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder setInfoList(
        int index, com.mumu.proto.protobuf2.PlayerSkillInfo value) {
      if (infoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInfoListIsMutable();
        infoList_.set(index, value);
        onChanged();
      } else {
        infoListBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder setInfoList(
        int index, com.mumu.proto.protobuf2.PlayerSkillInfo.Builder builderForValue) {
      if (infoListBuilder_ == null) {
        ensureInfoListIsMutable();
        infoList_.set(index, builderForValue.build());
        onChanged();
      } else {
        infoListBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder addInfoList(com.mumu.proto.protobuf2.PlayerSkillInfo value) {
      if (infoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInfoListIsMutable();
        infoList_.add(value);
        onChanged();
      } else {
        infoListBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder addInfoList(
        int index, com.mumu.proto.protobuf2.PlayerSkillInfo value) {
      if (infoListBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureInfoListIsMutable();
        infoList_.add(index, value);
        onChanged();
      } else {
        infoListBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder addInfoList(
        com.mumu.proto.protobuf2.PlayerSkillInfo.Builder builderForValue) {
      if (infoListBuilder_ == null) {
        ensureInfoListIsMutable();
        infoList_.add(builderForValue.build());
        onChanged();
      } else {
        infoListBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder addInfoList(
        int index, com.mumu.proto.protobuf2.PlayerSkillInfo.Builder builderForValue) {
      if (infoListBuilder_ == null) {
        ensureInfoListIsMutable();
        infoList_.add(index, builderForValue.build());
        onChanged();
      } else {
        infoListBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder addAllInfoList(
        java.lang.Iterable<? extends com.mumu.proto.protobuf2.PlayerSkillInfo> values) {
      if (infoListBuilder_ == null) {
        ensureInfoListIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, infoList_);
        onChanged();
      } else {
        infoListBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder clearInfoList() {
      if (infoListBuilder_ == null) {
        infoList_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        infoListBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public Builder removeInfoList(int index) {
      if (infoListBuilder_ == null) {
        ensureInfoListIsMutable();
        infoList_.remove(index);
        onChanged();
      } else {
        infoListBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public com.mumu.proto.protobuf2.PlayerSkillInfo.Builder getInfoListBuilder(
        int index) {
      return getInfoListFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder getInfoListOrBuilder(
        int index) {
      if (infoListBuilder_ == null) {
        return infoList_.get(index);  } else {
        return infoListBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public java.util.List<? extends com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder> 
         getInfoListOrBuilderList() {
      if (infoListBuilder_ != null) {
        return infoListBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(infoList_);
      }
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public com.mumu.proto.protobuf2.PlayerSkillInfo.Builder addInfoListBuilder() {
      return getInfoListFieldBuilder().addBuilder(
          com.mumu.proto.protobuf2.PlayerSkillInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public com.mumu.proto.protobuf2.PlayerSkillInfo.Builder addInfoListBuilder(
        int index) {
      return getInfoListFieldBuilder().addBuilder(
          index, com.mumu.proto.protobuf2.PlayerSkillInfo.getDefaultInstance());
    }
    /**
     * <pre>
     * 编队中受到技能影响
     * </pre>
     *
     * <code>repeated .PlayerSkillInfo infoList = 2;</code>
     */
    public java.util.List<com.mumu.proto.protobuf2.PlayerSkillInfo.Builder> 
         getInfoListBuilderList() {
      return getInfoListFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.mumu.proto.protobuf2.PlayerSkillInfo, com.mumu.proto.protobuf2.PlayerSkillInfo.Builder, com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder> 
        getInfoListFieldBuilder() {
      if (infoListBuilder_ == null) {
        infoListBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.mumu.proto.protobuf2.PlayerSkillInfo, com.mumu.proto.protobuf2.PlayerSkillInfo.Builder, com.mumu.proto.protobuf2.PlayerSkillInfoOrBuilder>(
                infoList_,
                ((bitField0_ & 0x00000002) != 0),
                getParentForChildren(),
                isClean());
        infoList_ = null;
      }
      return infoListBuilder_;
    }

    private int shieldOffset_ ;
    /**
     * <pre>
     * 护盾吸收伤害
     * </pre>
     *
     * <code>int32 shieldOffset = 3;</code>
     */
    public int getShieldOffset() {
      return shieldOffset_;
    }
    /**
     * <pre>
     * 护盾吸收伤害
     * </pre>
     *
     * <code>int32 shieldOffset = 3;</code>
     */
    public Builder setShieldOffset(int value) {
      
      shieldOffset_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 护盾吸收伤害
     * </pre>
     *
     * <code>int32 shieldOffset = 3;</code>
     */
    public Builder clearShieldOffset() {
      
      shieldOffset_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:TeamSkillInfo)
  }

  // @@protoc_insertion_point(class_scope:TeamSkillInfo)
  private static final com.mumu.proto.protobuf2.TeamSkillInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.mumu.proto.protobuf2.TeamSkillInfo();
  }

  public static com.mumu.proto.protobuf2.TeamSkillInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TeamSkillInfo>
      PARSER = new com.google.protobuf.AbstractParser<TeamSkillInfo>() {
    @java.lang.Override
    public TeamSkillInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TeamSkillInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TeamSkillInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TeamSkillInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.mumu.proto.protobuf2.TeamSkillInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

