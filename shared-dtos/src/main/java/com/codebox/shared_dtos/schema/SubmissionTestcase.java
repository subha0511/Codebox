/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.codebox.shared_dtos.schema;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SubmissionTestcase extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6948389431499282737L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SubmissionTestcase\",\"namespace\":\"com.codebox.shared_dtos.schema\",\"fields\":[{\"name\":\"input\",\"type\":\"string\"},{\"name\":\"output\",\"type\":[\"null\",\"string\"],\"default\":null},{\"name\":\"expectedOutput\",\"type\":\"string\"},{\"name\":\"type\",\"type\":\"string\"},{\"name\":\"status\",\"type\":[\"null\",\"string\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SubmissionTestcase> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SubmissionTestcase> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SubmissionTestcase> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SubmissionTestcase> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SubmissionTestcase> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SubmissionTestcase to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SubmissionTestcase from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SubmissionTestcase instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SubmissionTestcase fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence input;
  private java.lang.CharSequence output;
  private java.lang.CharSequence expectedOutput;
  private java.lang.CharSequence type;
  private java.lang.CharSequence status;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SubmissionTestcase() {}

  /**
   * All-args constructor.
   * @param input The new value for input
   * @param output The new value for output
   * @param expectedOutput The new value for expectedOutput
   * @param type The new value for type
   * @param status The new value for status
   */
  public SubmissionTestcase(java.lang.CharSequence input, java.lang.CharSequence output, java.lang.CharSequence expectedOutput, java.lang.CharSequence type, java.lang.CharSequence status) {
    this.input = input;
    this.output = output;
    this.expectedOutput = expectedOutput;
    this.type = type;
    this.status = status;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return input;
    case 1: return output;
    case 2: return expectedOutput;
    case 3: return type;
    case 4: return status;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: input = (java.lang.CharSequence)value$; break;
    case 1: output = (java.lang.CharSequence)value$; break;
    case 2: expectedOutput = (java.lang.CharSequence)value$; break;
    case 3: type = (java.lang.CharSequence)value$; break;
    case 4: status = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'input' field.
   * @return The value of the 'input' field.
   */
  public java.lang.CharSequence getInput() {
    return input;
  }


  /**
   * Sets the value of the 'input' field.
   * @param value the value to set.
   */
  public void setInput(java.lang.CharSequence value) {
    this.input = value;
  }

  /**
   * Gets the value of the 'output' field.
   * @return The value of the 'output' field.
   */
  public java.lang.CharSequence getOutput() {
    return output;
  }


  /**
   * Sets the value of the 'output' field.
   * @param value the value to set.
   */
  public void setOutput(java.lang.CharSequence value) {
    this.output = value;
  }

  /**
   * Gets the value of the 'expectedOutput' field.
   * @return The value of the 'expectedOutput' field.
   */
  public java.lang.CharSequence getExpectedOutput() {
    return expectedOutput;
  }


  /**
   * Sets the value of the 'expectedOutput' field.
   * @param value the value to set.
   */
  public void setExpectedOutput(java.lang.CharSequence value) {
    this.expectedOutput = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.CharSequence getType() {
    return type;
  }


  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.CharSequence value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public java.lang.CharSequence getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(java.lang.CharSequence value) {
    this.status = value;
  }

  /**
   * Creates a new SubmissionTestcase RecordBuilder.
   * @return A new SubmissionTestcase RecordBuilder
   */
  public static com.codebox.shared_dtos.schema.SubmissionTestcase.Builder newBuilder() {
    return new com.codebox.shared_dtos.schema.SubmissionTestcase.Builder();
  }

  /**
   * Creates a new SubmissionTestcase RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SubmissionTestcase RecordBuilder
   */
  public static com.codebox.shared_dtos.schema.SubmissionTestcase.Builder newBuilder(com.codebox.shared_dtos.schema.SubmissionTestcase.Builder other) {
    if (other == null) {
      return new com.codebox.shared_dtos.schema.SubmissionTestcase.Builder();
    } else {
      return new com.codebox.shared_dtos.schema.SubmissionTestcase.Builder(other);
    }
  }

  /**
   * Creates a new SubmissionTestcase RecordBuilder by copying an existing SubmissionTestcase instance.
   * @param other The existing instance to copy.
   * @return A new SubmissionTestcase RecordBuilder
   */
  public static com.codebox.shared_dtos.schema.SubmissionTestcase.Builder newBuilder(com.codebox.shared_dtos.schema.SubmissionTestcase other) {
    if (other == null) {
      return new com.codebox.shared_dtos.schema.SubmissionTestcase.Builder();
    } else {
      return new com.codebox.shared_dtos.schema.SubmissionTestcase.Builder(other);
    }
  }

  /**
   * RecordBuilder for SubmissionTestcase instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SubmissionTestcase>
    implements org.apache.avro.data.RecordBuilder<SubmissionTestcase> {

    private java.lang.CharSequence input;
    private java.lang.CharSequence output;
    private java.lang.CharSequence expectedOutput;
    private java.lang.CharSequence type;
    private java.lang.CharSequence status;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.codebox.shared_dtos.schema.SubmissionTestcase.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.input)) {
        this.input = data().deepCopy(fields()[0].schema(), other.input);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.output)) {
        this.output = data().deepCopy(fields()[1].schema(), other.output);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.expectedOutput)) {
        this.expectedOutput = data().deepCopy(fields()[2].schema(), other.expectedOutput);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.status)) {
        this.status = data().deepCopy(fields()[4].schema(), other.status);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing SubmissionTestcase instance
     * @param other The existing instance to copy.
     */
    private Builder(com.codebox.shared_dtos.schema.SubmissionTestcase other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.input)) {
        this.input = data().deepCopy(fields()[0].schema(), other.input);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.output)) {
        this.output = data().deepCopy(fields()[1].schema(), other.output);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.expectedOutput)) {
        this.expectedOutput = data().deepCopy(fields()[2].schema(), other.expectedOutput);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.type)) {
        this.type = data().deepCopy(fields()[3].schema(), other.type);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.status)) {
        this.status = data().deepCopy(fields()[4].schema(), other.status);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'input' field.
      * @return The value.
      */
    public java.lang.CharSequence getInput() {
      return input;
    }


    /**
      * Sets the value of the 'input' field.
      * @param value The value of 'input'.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder setInput(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.input = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'input' field has been set.
      * @return True if the 'input' field has been set, false otherwise.
      */
    public boolean hasInput() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'input' field.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder clearInput() {
      input = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'output' field.
      * @return The value.
      */
    public java.lang.CharSequence getOutput() {
      return output;
    }


    /**
      * Sets the value of the 'output' field.
      * @param value The value of 'output'.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder setOutput(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.output = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'output' field has been set.
      * @return True if the 'output' field has been set, false otherwise.
      */
    public boolean hasOutput() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'output' field.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder clearOutput() {
      output = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'expectedOutput' field.
      * @return The value.
      */
    public java.lang.CharSequence getExpectedOutput() {
      return expectedOutput;
    }


    /**
      * Sets the value of the 'expectedOutput' field.
      * @param value The value of 'expectedOutput'.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder setExpectedOutput(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.expectedOutput = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'expectedOutput' field has been set.
      * @return True if the 'expectedOutput' field has been set, false otherwise.
      */
    public boolean hasExpectedOutput() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'expectedOutput' field.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder clearExpectedOutput() {
      expectedOutput = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.CharSequence getType() {
      return type;
    }


    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder setType(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.type = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder clearType() {
      type = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public java.lang.CharSequence getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder setStatus(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.status = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public com.codebox.shared_dtos.schema.SubmissionTestcase.Builder clearStatus() {
      status = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SubmissionTestcase build() {
      try {
        SubmissionTestcase record = new SubmissionTestcase();
        record.input = fieldSetFlags()[0] ? this.input : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.output = fieldSetFlags()[1] ? this.output : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.expectedOutput = fieldSetFlags()[2] ? this.expectedOutput : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.type = fieldSetFlags()[3] ? this.type : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.status = fieldSetFlags()[4] ? this.status : (java.lang.CharSequence) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SubmissionTestcase>
    WRITER$ = (org.apache.avro.io.DatumWriter<SubmissionTestcase>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SubmissionTestcase>
    READER$ = (org.apache.avro.io.DatumReader<SubmissionTestcase>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.input);

    if (this.output == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.output);
    }

    out.writeString(this.expectedOutput);

    out.writeString(this.type);

    if (this.status == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.status);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.input = in.readString(this.input instanceof Utf8 ? (Utf8)this.input : null);

      if (in.readIndex() != 1) {
        in.readNull();
        this.output = null;
      } else {
        this.output = in.readString(this.output instanceof Utf8 ? (Utf8)this.output : null);
      }

      this.expectedOutput = in.readString(this.expectedOutput instanceof Utf8 ? (Utf8)this.expectedOutput : null);

      this.type = in.readString(this.type instanceof Utf8 ? (Utf8)this.type : null);

      if (in.readIndex() != 1) {
        in.readNull();
        this.status = null;
      } else {
        this.status = in.readString(this.status instanceof Utf8 ? (Utf8)this.status : null);
      }

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.input = in.readString(this.input instanceof Utf8 ? (Utf8)this.input : null);
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.output = null;
          } else {
            this.output = in.readString(this.output instanceof Utf8 ? (Utf8)this.output : null);
          }
          break;

        case 2:
          this.expectedOutput = in.readString(this.expectedOutput instanceof Utf8 ? (Utf8)this.expectedOutput : null);
          break;

        case 3:
          this.type = in.readString(this.type instanceof Utf8 ? (Utf8)this.type : null);
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.status = null;
          } else {
            this.status = in.readString(this.status instanceof Utf8 ? (Utf8)this.status : null);
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










