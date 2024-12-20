// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

/**
 * <pre>
 * 部队冒字 ProtoId 10
 * </pre>
 *
 * Protobuf type {@code DisplayMessage}
 */
public  final class DisplayMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DisplayMessage)
    DisplayMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DisplayMessage.newBuilder() to construct.
  private DisplayMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DisplayMessage() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DisplayMessage(
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
          case 16: {

            num_ = input.readInt32();
            break;
          }
          case 24: {

            type_ = input.readInt32();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_DisplayMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_DisplayMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.mumu.proto.protobuf2.DisplayMessage.class, com.mumu.proto.protobuf2.DisplayMessage.Builder.class);
  }

  public static final int TEAMID_FIELD_NUMBER = 1;
  private int teamId_;
  /**
   * <pre>
   * 部队id
   * </pre>
   *
   * <code>int32 teamId = 1;</code>
   */
  public int getTeamId() {
    return teamId_;
  }

  public static final int NUM_FIELD_NUMBER = 2;
  private int num_;
  /**
   * <pre>
   * 数值
   * </pre>
   *
   * <code>int32 num = 2;</code>
   */
  public int getNum() {
    return num_;
  }

  public static final int TYPE_FIELD_NUMBER = 3;
  private int type_;
  /**
   * <pre>
   * 类型
   * </pre>
   *
   * <code>int32 type = 3;</code>
   */
  public int getType() {
    return type_;
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
    if (num_ != 0) {
      output.writeInt32(2, num_);
    }
    if (type_ != 0) {
      output.writeInt32(3, type_);
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
    if (num_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, num_);
    }
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, type_);
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
    if (!(obj instanceof com.mumu.proto.protobuf2.DisplayMessage)) {
      return super.equals(obj);
    }
    com.mumu.proto.protobuf2.DisplayMessage other = (com.mumu.proto.protobuf2.DisplayMessage) obj;

    if (getTeamId()
        != other.getTeamId()) return false;
    if (getNum()
        != other.getNum()) return false;
    if (getType()
        != other.getType()) return false;
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
    hash = (37 * hash) + NUM_FIELD_NUMBER;
    hash = (53 * hash) + getNum();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.DisplayMessage parseFrom(
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
  public static Builder newBuilder(com.mumu.proto.protobuf2.DisplayMessage prototype) {
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
   * 部队冒字 ProtoId 10
   * </pre>
   *
   * Protobuf type {@code DisplayMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DisplayMessage)
      com.mumu.proto.protobuf2.DisplayMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_DisplayMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_DisplayMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.mumu.proto.protobuf2.DisplayMessage.class, com.mumu.proto.protobuf2.DisplayMessage.Builder.class);
    }

    // Construct using com.mumu.proto.protobuf2.DisplayMessage.newBuilder()
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
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      teamId_ = 0;

      num_ = 0;

      type_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_DisplayMessage_descriptor;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.DisplayMessage getDefaultInstanceForType() {
      return com.mumu.proto.protobuf2.DisplayMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.DisplayMessage build() {
      com.mumu.proto.protobuf2.DisplayMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.DisplayMessage buildPartial() {
      com.mumu.proto.protobuf2.DisplayMessage result = new com.mumu.proto.protobuf2.DisplayMessage(this);
      result.teamId_ = teamId_;
      result.num_ = num_;
      result.type_ = type_;
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
      if (other instanceof com.mumu.proto.protobuf2.DisplayMessage) {
        return mergeFrom((com.mumu.proto.protobuf2.DisplayMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.mumu.proto.protobuf2.DisplayMessage other) {
      if (other == com.mumu.proto.protobuf2.DisplayMessage.getDefaultInstance()) return this;
      if (other.getTeamId() != 0) {
        setTeamId(other.getTeamId());
      }
      if (other.getNum() != 0) {
        setNum(other.getNum());
      }
      if (other.getType() != 0) {
        setType(other.getType());
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
      com.mumu.proto.protobuf2.DisplayMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.mumu.proto.protobuf2.DisplayMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int teamId_ ;
    /**
     * <pre>
     * 部队id
     * </pre>
     *
     * <code>int32 teamId = 1;</code>
     */
    public int getTeamId() {
      return teamId_;
    }
    /**
     * <pre>
     * 部队id
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
     * 部队id
     * </pre>
     *
     * <code>int32 teamId = 1;</code>
     */
    public Builder clearTeamId() {
      
      teamId_ = 0;
      onChanged();
      return this;
    }

    private int num_ ;
    /**
     * <pre>
     * 数值
     * </pre>
     *
     * <code>int32 num = 2;</code>
     */
    public int getNum() {
      return num_;
    }
    /**
     * <pre>
     * 数值
     * </pre>
     *
     * <code>int32 num = 2;</code>
     */
    public Builder setNum(int value) {
      
      num_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 数值
     * </pre>
     *
     * <code>int32 num = 2;</code>
     */
    public Builder clearNum() {
      
      num_ = 0;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <pre>
     * 类型
     * </pre>
     *
     * <code>int32 type = 3;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <pre>
     * 类型
     * </pre>
     *
     * <code>int32 type = 3;</code>
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 类型
     * </pre>
     *
     * <code>int32 type = 3;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
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


    // @@protoc_insertion_point(builder_scope:DisplayMessage)
  }

  // @@protoc_insertion_point(class_scope:DisplayMessage)
  private static final com.mumu.proto.protobuf2.DisplayMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.mumu.proto.protobuf2.DisplayMessage();
  }

  public static com.mumu.proto.protobuf2.DisplayMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DisplayMessage>
      PARSER = new com.google.protobuf.AbstractParser<DisplayMessage>() {
    @java.lang.Override
    public DisplayMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DisplayMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DisplayMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DisplayMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.mumu.proto.protobuf2.DisplayMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

