// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Fight.proto

package com.mumu.proto.protobuf2;

/**
 * <pre>
 * 重置cd ProtoId 4
 * </pre>
 *
 * Protobuf type {@code ResetCdMessage}
 */
public  final class ResetCdMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ResetCdMessage)
    ResetCdMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ResetCdMessage.newBuilder() to construct.
  private ResetCdMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResetCdMessage() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResetCdMessage(
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

            id_ = input.readInt32();
            break;
          }
          case 16: {

            cd_ = input.readInt32();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_ResetCdMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.mumu.proto.protobuf2.FightMessages.internal_static_ResetCdMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.mumu.proto.protobuf2.ResetCdMessage.class, com.mumu.proto.protobuf2.ResetCdMessage.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <pre>
   * 小兵id
   * </pre>
   *
   * <code>int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  public static final int CD_FIELD_NUMBER = 2;
  private int cd_;
  /**
   * <pre>
   * cd
   * </pre>
   *
   * <code>int32 cd = 2;</code>
   */
  public int getCd() {
    return cd_;
  }

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
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (cd_ != 0) {
      output.writeInt32(2, cd_);
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
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (cd_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, cd_);
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
    if (!(obj instanceof com.mumu.proto.protobuf2.ResetCdMessage)) {
      return super.equals(obj);
    }
    com.mumu.proto.protobuf2.ResetCdMessage other = (com.mumu.proto.protobuf2.ResetCdMessage) obj;

    if (getId()
        != other.getId()) return false;
    if (getCd()
        != other.getCd()) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + CD_FIELD_NUMBER;
    hash = (53 * hash) + getCd();
    hash = (37 * hash) + TEAMID_FIELD_NUMBER;
    hash = (53 * hash) + getTeamId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.mumu.proto.protobuf2.ResetCdMessage parseFrom(
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
  public static Builder newBuilder(com.mumu.proto.protobuf2.ResetCdMessage prototype) {
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
   * 重置cd ProtoId 4
   * </pre>
   *
   * Protobuf type {@code ResetCdMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ResetCdMessage)
      com.mumu.proto.protobuf2.ResetCdMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_ResetCdMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_ResetCdMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.mumu.proto.protobuf2.ResetCdMessage.class, com.mumu.proto.protobuf2.ResetCdMessage.Builder.class);
    }

    // Construct using com.mumu.proto.protobuf2.ResetCdMessage.newBuilder()
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
      id_ = 0;

      cd_ = 0;

      teamId_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.mumu.proto.protobuf2.FightMessages.internal_static_ResetCdMessage_descriptor;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.ResetCdMessage getDefaultInstanceForType() {
      return com.mumu.proto.protobuf2.ResetCdMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.ResetCdMessage build() {
      com.mumu.proto.protobuf2.ResetCdMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.mumu.proto.protobuf2.ResetCdMessage buildPartial() {
      com.mumu.proto.protobuf2.ResetCdMessage result = new com.mumu.proto.protobuf2.ResetCdMessage(this);
      result.id_ = id_;
      result.cd_ = cd_;
      result.teamId_ = teamId_;
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
      if (other instanceof com.mumu.proto.protobuf2.ResetCdMessage) {
        return mergeFrom((com.mumu.proto.protobuf2.ResetCdMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.mumu.proto.protobuf2.ResetCdMessage other) {
      if (other == com.mumu.proto.protobuf2.ResetCdMessage.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getCd() != 0) {
        setCd(other.getCd());
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
      com.mumu.proto.protobuf2.ResetCdMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.mumu.proto.protobuf2.ResetCdMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int id_ ;
    /**
     * <pre>
     * 小兵id
     * </pre>
     *
     * <code>int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <pre>
     * 小兵id
     * </pre>
     *
     * <code>int32 id = 1;</code>
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 小兵id
     * </pre>
     *
     * <code>int32 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private int cd_ ;
    /**
     * <pre>
     * cd
     * </pre>
     *
     * <code>int32 cd = 2;</code>
     */
    public int getCd() {
      return cd_;
    }
    /**
     * <pre>
     * cd
     * </pre>
     *
     * <code>int32 cd = 2;</code>
     */
    public Builder setCd(int value) {
      
      cd_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * cd
     * </pre>
     *
     * <code>int32 cd = 2;</code>
     */
    public Builder clearCd() {
      
      cd_ = 0;
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


    // @@protoc_insertion_point(builder_scope:ResetCdMessage)
  }

  // @@protoc_insertion_point(class_scope:ResetCdMessage)
  private static final com.mumu.proto.protobuf2.ResetCdMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.mumu.proto.protobuf2.ResetCdMessage();
  }

  public static com.mumu.proto.protobuf2.ResetCdMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResetCdMessage>
      PARSER = new com.google.protobuf.AbstractParser<ResetCdMessage>() {
    @java.lang.Override
    public ResetCdMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ResetCdMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResetCdMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResetCdMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.mumu.proto.protobuf2.ResetCdMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

