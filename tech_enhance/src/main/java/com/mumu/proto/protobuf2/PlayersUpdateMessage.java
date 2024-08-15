// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

/**
 * <pre>
 * 小兵更新协议 ProtoId 3
 * </pre>
 *
 * Protobuf type {@code PlayersUpdateMessage}
 */
public  final class PlayersUpdateMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:PlayersUpdateMessage)
    PlayersUpdateMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use PlayersUpdateMessage.newBuilder() to construct.
  private PlayersUpdateMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlayersUpdateMessage() {
    addPlayers_ = java.util.Collections.emptyList();
    removePlayers_ = emptyIntList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlayersUpdateMessage(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              addPlayers_ = new java.util.ArrayList<com.mumu.proto.protobuf2.PlayerMessage>();
              mutable_bitField0_ |= 0x00000001;
            }
            addPlayers_.add(
                input.readMessage(com.mumu.proto.protobuf2.PlayerMessage.parser(), extensionRegistry));
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
              removePlayers_ = newIntList();
              mutable_bitField0_ |= 0x00000002;
            }
            removePlayers_.addInt(input.readInt32());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) != 0) && input.getBytesUntilLimit() > 0) {
              removePlayers_ = newIntList();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              removePlayers_.addInt(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 24: {

            teamId_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        addPlayers_ = java.util.Collections.unmodifiableList(addPlayers_);
      }
      if (((mutable_bitField0_ & 0x00000002) != 0)) {
        removePlayers_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_PlayersUpdateMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_PlayersUpdateMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.mumu.proto.protobuf2.PlayersUpdateMessage.class, com.mumu.proto.protobuf2.PlayersUpdateMessage.Builder.class);
  }

  private int bitField0_;
  public static final int ADDPLAYERS_FIELD_NUMBER = 1;
  private java.util.List<com.mumu.proto.protobuf2.PlayerMessage> addPlayers_;
  /**
   * <pre>
   * 新增的小兵
   * </pre>
   *
   * <code>repeated .PlayerMessage addPlayers = 1;</code>
   */
  public java.util.List<com.mumu.proto.protobuf2.PlayerMessage> getAddPlayersList() {
    return addPlayers_;
  }
  /**
   * <pre>
   * 新增的小兵
   * </pre>
   *
   * <code>repeated .PlayerMessage addPlayers = 1;</code>
   */
  public java.util.List<? extends com.mumu.proto.protobuf2.PlayerMessageOrBuilder> 
      getAddPlayersOrBuilderList() {
    return addPlayers_;
  }
  /**
   * <pre>
   * 新增的小兵
   * </pre>
   *
   * <code>repeated .PlayerMessage addPlayers = 1;</code>
   */
  public int getAddPlayersCount() {
    return addPlayers_.size();
  }
  /**
   * <pre>
   * 新增的小兵
   * </pre>
   *
   * <code>repeated .PlayerMessage addPlayers = 1;</code>
   */
  public com.mumu.proto.protobuf2.PlayerMessage getAddPlayers(int index) {
    return addPlayers_.get(index);
  }
  /**
   * <pre>
   * 新增的小兵
   * </pre>
   *
   * <code>repeated .PlayerMessage addPlayers = 1;</code>
   */
  public com.mumu.proto.protobuf2.PlayerMessageOrBuilder getAddPlayersOrBuilder(
      int index) {
    return addPlayers_.get(index);
  }

  public static final int REMOVEPLAYERS_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.IntList removePlayers_;
  /**
   * <pre>
   * 被删除的小兵
   * </pre>
   *
   * <code>repeated int32 removePlayers = 2;</code>
   */
  public java.util.List<java.lang.Integer>
      getRemovePlayersList() {
    return removePlayers_;
  }
  /**
   * <pre>
   * 被删除的小兵
   * </pre>
   *
   * <code>repeated int32 removePlayers = 2;</code>
   */
  public int getRemovePlayersCount() {
    return removePlayers_.size();
  }
  /**
   * <pre>
   * 被删除的小兵
   * </pre>
   *
   * <code>repeated int32 removePlayers = 2;</code>
   */
  public int getRemovePlayers(int index) {
    return removePlayers_.getInt(index);
  }
  private int removePlayersMemoizedSerializedSize = -1;

  public static final int TEAMID_FIELD_NUMBER = 3;
  private int teamId_;
  /**
   * <pre>
   * 编队id
   * </pre>
   *
   * <code>int32 teamId = 3;</code>
   */
  public int getTeamId() {
    return teamId_;
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
    getSerializedSize();
    for (int i = 0; i < addPlayers_.size(); i++) {
      output.writeMessage(1, addPlayers_.get(i));
    }
    if (getRemovePlayersList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(removePlayersMemoizedSerializedSize);
    }
    for (int i = 0; i < removePlayers_.size(); i++) {
      output.writeInt32NoTag(removePlayers_.getInt(i));
    }
    if (teamId_ != 0) {
      output.writeInt32(3, teamId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < addPlayers_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, addPlayers_.get(i));
    }
    {
      int dataSize = 0;
      for (int i = 0; i < removePlayers_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(removePlayers_.getInt(i));
      }
      size += dataSize;
      if (!getRemovePlayersList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      removePlayersMemoizedSerializedSize = dataSize;
    }
    if (teamId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, teamId_);
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
    if (!(obj instanceof com.mumu.proto.protobuf2.PlayersUpdateMessage)) {
      return super.equals(obj);
    }
    com.mumu.proto.protobuf2.PlayersUpdateMessage other = (com.mumu.proto.protobuf2.PlayersUpdateMessage) obj;

    if (!getAddPlayersList()
        .equals(other.getAddPlayersList())) return false;
    if (!getRemovePlayersList()
        .equals(other.getRemovePlayersList())) return false;
    if (getTeamId()
        != other.getTeamId()) return false;
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
    if (getAddPlayersCount() > 0) {
      hash = (37 * hash) + ADDPLAYERS_FIELD_NUMBER;
      hash = (53 * hash) + getAddPlayersList().hashCode();
    }
    if (getRemovePlayersCount() > 0) {
      hash = (37 * hash) + REMOVEPLAYERS_FIELD_NUMBER;
      hash = (53 * hash) + getRemovePlayersList().hashCode();
    }
    hash = (37 * hash) + TEAMID_FIELD_NUMBER;
    hash = (53 * hash) + getTeamId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.PlayersUpdateMessage parseFrom(
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
  public static Builder newBuilder(com.mumu.proto.protobuf2.PlayersUpdateMessage prototype) {
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
   * 小兵更新协议 ProtoId 3
   * </pre>
   *
   * Protobuf type {@code PlayersUpdateMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:PlayersUpdateMessage)
      com.mumu.proto.protobuf2.PlayersUpdateMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_PlayersUpdateMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_PlayersUpdateMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.mumu.proto.protobuf2.PlayersUpdateMessage.class, com.mumu.proto.protobuf2.PlayersUpdateMessage.Builder.class);
    }

    // Construct using com.mumu.proto.protobuf2.PlayersUpdateMessage.newBuilder()
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
        getAddPlayersFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (addPlayersBuilder_ == null) {
        addPlayers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        addPlayersBuilder_.clear();
      }
      removePlayers_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000002);
      teamId_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_PlayersUpdateMessage_descriptor;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.PlayersUpdateMessage getDefaultInstanceForType() {
      return com.mumu.proto.protobuf2.PlayersUpdateMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.PlayersUpdateMessage build() {
      com.mumu.proto.protobuf2.PlayersUpdateMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.PlayersUpdateMessage buildPartial() {
      com.mumu.proto.protobuf2.PlayersUpdateMessage result = new com.mumu.proto.protobuf2.PlayersUpdateMessage(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (addPlayersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          addPlayers_ = java.util.Collections.unmodifiableList(addPlayers_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.addPlayers_ = addPlayers_;
      } else {
        result.addPlayers_ = addPlayersBuilder_.build();
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        removePlayers_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.removePlayers_ = removePlayers_;
      result.teamId_ = teamId_;
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
      if (other instanceof com.mumu.proto.protobuf2.PlayersUpdateMessage) {
        return mergeFrom((com.mumu.proto.protobuf2.PlayersUpdateMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.mumu.proto.protobuf2.PlayersUpdateMessage other) {
      if (other == com.mumu.proto.protobuf2.PlayersUpdateMessage.getDefaultInstance()) return this;
      if (addPlayersBuilder_ == null) {
        if (!other.addPlayers_.isEmpty()) {
          if (addPlayers_.isEmpty()) {
            addPlayers_ = other.addPlayers_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAddPlayersIsMutable();
            addPlayers_.addAll(other.addPlayers_);
          }
          onChanged();
        }
      } else {
        if (!other.addPlayers_.isEmpty()) {
          if (addPlayersBuilder_.isEmpty()) {
            addPlayersBuilder_.dispose();
            addPlayersBuilder_ = null;
            addPlayers_ = other.addPlayers_;
            bitField0_ = (bitField0_ & ~0x00000001);
            addPlayersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getAddPlayersFieldBuilder() : null;
          } else {
            addPlayersBuilder_.addAllMessages(other.addPlayers_);
          }
        }
      }
      if (!other.removePlayers_.isEmpty()) {
        if (removePlayers_.isEmpty()) {
          removePlayers_ = other.removePlayers_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureRemovePlayersIsMutable();
          removePlayers_.addAll(other.removePlayers_);
        }
        onChanged();
      }
      if (other.getTeamId() != 0) {
        setTeamId(other.getTeamId());
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
      com.mumu.proto.protobuf2.PlayersUpdateMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.mumu.proto.protobuf2.PlayersUpdateMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<com.mumu.proto.protobuf2.PlayerMessage> addPlayers_ =
      java.util.Collections.emptyList();
    private void ensureAddPlayersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        addPlayers_ = new java.util.ArrayList<com.mumu.proto.protobuf2.PlayerMessage>(addPlayers_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.mumu.proto.protobuf2.PlayerMessage, com.mumu.proto.protobuf2.PlayerMessage.Builder, com.mumu.proto.protobuf2.PlayerMessageOrBuilder> addPlayersBuilder_;

    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public java.util.List<com.mumu.proto.protobuf2.PlayerMessage> getAddPlayersList() {
      if (addPlayersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(addPlayers_);
      } else {
        return addPlayersBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public int getAddPlayersCount() {
      if (addPlayersBuilder_ == null) {
        return addPlayers_.size();
      } else {
        return addPlayersBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public com.mumu.proto.protobuf2.PlayerMessage getAddPlayers(int index) {
      if (addPlayersBuilder_ == null) {
        return addPlayers_.get(index);
      } else {
        return addPlayersBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder setAddPlayers(
        int index, com.mumu.proto.protobuf2.PlayerMessage value) {
      if (addPlayersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAddPlayersIsMutable();
        addPlayers_.set(index, value);
        onChanged();
      } else {
        addPlayersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder setAddPlayers(
        int index, com.mumu.proto.protobuf2.PlayerMessage.Builder builderForValue) {
      if (addPlayersBuilder_ == null) {
        ensureAddPlayersIsMutable();
        addPlayers_.set(index, builderForValue.build());
        onChanged();
      } else {
        addPlayersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder addAddPlayers(com.mumu.proto.protobuf2.PlayerMessage value) {
      if (addPlayersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAddPlayersIsMutable();
        addPlayers_.add(value);
        onChanged();
      } else {
        addPlayersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder addAddPlayers(
        int index, com.mumu.proto.protobuf2.PlayerMessage value) {
      if (addPlayersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAddPlayersIsMutable();
        addPlayers_.add(index, value);
        onChanged();
      } else {
        addPlayersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder addAddPlayers(
        com.mumu.proto.protobuf2.PlayerMessage.Builder builderForValue) {
      if (addPlayersBuilder_ == null) {
        ensureAddPlayersIsMutable();
        addPlayers_.add(builderForValue.build());
        onChanged();
      } else {
        addPlayersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder addAddPlayers(
        int index, com.mumu.proto.protobuf2.PlayerMessage.Builder builderForValue) {
      if (addPlayersBuilder_ == null) {
        ensureAddPlayersIsMutable();
        addPlayers_.add(index, builderForValue.build());
        onChanged();
      } else {
        addPlayersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder addAllAddPlayers(
        java.lang.Iterable<? extends com.mumu.proto.protobuf2.PlayerMessage> values) {
      if (addPlayersBuilder_ == null) {
        ensureAddPlayersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, addPlayers_);
        onChanged();
      } else {
        addPlayersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder clearAddPlayers() {
      if (addPlayersBuilder_ == null) {
        addPlayers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        addPlayersBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public Builder removeAddPlayers(int index) {
      if (addPlayersBuilder_ == null) {
        ensureAddPlayersIsMutable();
        addPlayers_.remove(index);
        onChanged();
      } else {
        addPlayersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public com.mumu.proto.protobuf2.PlayerMessage.Builder getAddPlayersBuilder(
        int index) {
      return getAddPlayersFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public com.mumu.proto.protobuf2.PlayerMessageOrBuilder getAddPlayersOrBuilder(
        int index) {
      if (addPlayersBuilder_ == null) {
        return addPlayers_.get(index);  } else {
        return addPlayersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public java.util.List<? extends com.mumu.proto.protobuf2.PlayerMessageOrBuilder> 
         getAddPlayersOrBuilderList() {
      if (addPlayersBuilder_ != null) {
        return addPlayersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(addPlayers_);
      }
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public com.mumu.proto.protobuf2.PlayerMessage.Builder addAddPlayersBuilder() {
      return getAddPlayersFieldBuilder().addBuilder(
          com.mumu.proto.protobuf2.PlayerMessage.getDefaultInstance());
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public com.mumu.proto.protobuf2.PlayerMessage.Builder addAddPlayersBuilder(
        int index) {
      return getAddPlayersFieldBuilder().addBuilder(
          index, com.mumu.proto.protobuf2.PlayerMessage.getDefaultInstance());
    }
    /**
     * <pre>
     * 新增的小兵
     * </pre>
     *
     * <code>repeated .PlayerMessage addPlayers = 1;</code>
     */
    public java.util.List<com.mumu.proto.protobuf2.PlayerMessage.Builder> 
         getAddPlayersBuilderList() {
      return getAddPlayersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.mumu.proto.protobuf2.PlayerMessage, com.mumu.proto.protobuf2.PlayerMessage.Builder, com.mumu.proto.protobuf2.PlayerMessageOrBuilder> 
        getAddPlayersFieldBuilder() {
      if (addPlayersBuilder_ == null) {
        addPlayersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.mumu.proto.protobuf2.PlayerMessage, com.mumu.proto.protobuf2.PlayerMessage.Builder, com.mumu.proto.protobuf2.PlayerMessageOrBuilder>(
                addPlayers_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        addPlayers_ = null;
      }
      return addPlayersBuilder_;
    }

    private com.google.protobuf.Internal.IntList removePlayers_ = emptyIntList();
    private void ensureRemovePlayersIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        removePlayers_ = mutableCopy(removePlayers_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public java.util.List<java.lang.Integer>
        getRemovePlayersList() {
      return ((bitField0_ & 0x00000002) != 0) ?
               java.util.Collections.unmodifiableList(removePlayers_) : removePlayers_;
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public int getRemovePlayersCount() {
      return removePlayers_.size();
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public int getRemovePlayers(int index) {
      return removePlayers_.getInt(index);
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public Builder setRemovePlayers(
        int index, int value) {
      ensureRemovePlayersIsMutable();
      removePlayers_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public Builder addRemovePlayers(int value) {
      ensureRemovePlayersIsMutable();
      removePlayers_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public Builder addAllRemovePlayers(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureRemovePlayersIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, removePlayers_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被删除的小兵
     * </pre>
     *
     * <code>repeated int32 removePlayers = 2;</code>
     */
    public Builder clearRemovePlayers() {
      removePlayers_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }

    private int teamId_ ;
    /**
     * <pre>
     * 编队id
     * </pre>
     *
     * <code>int32 teamId = 3;</code>
     */
    public int getTeamId() {
      return teamId_;
    }
    /**
     * <pre>
     * 编队id
     * </pre>
     *
     * <code>int32 teamId = 3;</code>
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
     * <code>int32 teamId = 3;</code>
     */
    public Builder clearTeamId() {
      
      teamId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:PlayersUpdateMessage)
  }

  // @@protoc_insertion_point(class_scope:PlayersUpdateMessage)
  private static final com.mumu.proto.protobuf2.PlayersUpdateMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.mumu.proto.protobuf2.PlayersUpdateMessage();
  }

  public static com.mumu.proto.protobuf2.PlayersUpdateMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PlayersUpdateMessage>
      PARSER = new com.google.protobuf.AbstractParser<PlayersUpdateMessage>() {
    @java.lang.Override
    public PlayersUpdateMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new PlayersUpdateMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlayersUpdateMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlayersUpdateMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.mumu.proto.protobuf2.PlayersUpdateMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
