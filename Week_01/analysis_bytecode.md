```
Classfile /Users/malinghan/IdeaProjects/learning-space/interview/jvm/src/main/java/com/someecho/HelloByteCode.class
  Last modified 2020-10-16; size 301 bytes
  MD5 checksum 800c68d755e1ab7bb824767797f9e8e9
  Compiled from "HelloByteCode.java"
public class com.someecho.HelloByteCode
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #4.#13         // java/lang/Object."<init>":()V
   #2 = Class              #14            // com/someecho/HelloByteCode
   #3 = Methodref          #2.#13         // com/someecho/HelloByteCode."<init>":()V
   #4 = Class              #15            // java/lang/Object
   #5 = Utf8               <init>
   #6 = Utf8               ()V
   #7 = Utf8               Code
   #8 = Utf8               LineNumberTable
   #9 = Utf8               main
  #10 = Utf8               ([Ljava/lang/String;)V
  #11 = Utf8               SourceFile
  #12 = Utf8               HelloByteCode.java
  #13 = NameAndType        #5:#6          // "<init>":()V
  #14 = Utf8               com/someecho/HelloByteCode
  #15 = Utf8               java/lang/Object
{
  public com.someecho.HelloByteCode();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 9: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #2                  // class com/someecho/HelloByteCode
         3: dup
         4: invokespecial #3                  // Method "<init>":()V
         7: astore_1
         8: return
      LineNumberTable:
        line 12: 0
        line 13: 8
}
SourceFile: "HelloByteCode.java"
```
