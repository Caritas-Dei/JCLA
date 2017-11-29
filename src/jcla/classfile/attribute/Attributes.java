package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.AttributeInfo;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public enum Attributes {
	;

	public static AttributeInfo[] load(DataInput in, ConstantPool owner) throws IOException {
		AttributeInfo[] attributes = new AttributeInfo[in.readUnsignedShort()];
		for (int i = 0; i < attributes.length; i++) {
			final int           name         = in.readUnsignedShort();
			final int           length       = in.readInt();
			final Constant.UTF8 resolvedname = owner.getUTF8(name);
			switch (resolvedname.getValue()) {
				case "ConstantValue":
					attributes[i] = new ConstantValue<>(name, length, in.readUnsignedShort(), owner);
					break;
				case "Code":
					attributes[i] = new Code(name, length, in, owner);
					break;
				case "StackMapTable":
					attributes[i] = new StackMapTable(name, length, in, owner);
					break;
				case "Exceptions":
					attributes[i] = new Exceptions(name, length, in, owner);
					break;
				case "InnerClasses":
					attributes[i] = new InnerClasses(name, length, in, owner);
					break;
				case "EnclosingMethod":
					attributes[i] = new EnclosingMethod(name, length, in, owner);
					break;
				case "Synthetic":
					attributes[i] = new Synthetic(name, length, in, owner);
					break;
				case "Signature":
					attributes[i] = new Signature(name, length, in, owner);
					break;
				case "SourceFile":
					attributes[i] = new SourceFile(name, length, in, owner);
					break;
				case "SourceDebugExtension":
					attributes[i] = new SourceDebugExtension(name, length, in, owner);
					break;
				case "LineNumberTable":
					attributes[i] = new LineNumberTable(name, length, in, owner);
					break;
				case "LocalVariableTable":
					attributes[i] = new LocalVariableTable(name, length, in, owner);
					break;
				case "LocalVariableTypeTable":
					attributes[i] = new LocalVariableTypeTable(name, length, in, owner);
					break;
				case "Deprecated":
					attributes[i] = new Deprecated(name, length, owner);
					break;
				case "RuntimeVisibleAnnotations":
					attributes[i] = new RuntimeVisibleAnnotations(name, length, in, owner);
					break;
				case "RuntimeInvisibleAnnotations":
					attributes[i] = new RuntimeInvisibleAnnotations(name, length, in, owner);
					break;
				case "RuntimeVisibleParameterAnnotations":
					attributes[i] = new RuntimeVisibleParameterAnnotations(name, length, in, owner);
					break;
				case "RuntimeInvisibleParameterAnnotations":
					attributes[i] = new RuntimeInvisibleParameterAnnotations(name, length, in, owner);
					break;
				case "RuntimeVisibleTypeAnnotations":
					attributes[i] = new RuntimeVisibleTypeAnnotations(name, length, in, owner);
					break;
				case "RuntimeInvisibleTypeAnnotations":
					attributes[i] = new RuntimeInvisibleTypeAnnotations(name, length, in, owner);
					break;
				case "AnnotationDefault":
					attributes[i] = new AnnotationDefault(name, length, in, owner);
					break;
				case "BootstrapMethods":
					attributes[i] = new BootstrapMethods(name, length, in, owner);
					break;
				case "MethodParameters":
					attributes[i] = new MethodParameters(name, length, in, owner);
					break;
				default:
					attributes[i] = new CustomAttribute(name, length, in, owner);
			}
		}

		return attributes;
	}

}
