package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task3Test {

    @Test
    @DisplayName("Создан новый класс и метод вычисления числа Фибоначчи, используя ByteCodeAppender и ASM")
    void test1() throws NoSuchMethodException, InvocationTargetException, InstantiationException,
        IllegalAccessException {
        // given
        Class<?> newFib = new ByteBuddy()
            .subclass(Object.class)
            .name("NewFib")
            .defineMethod("fib", long.class, Modifier.PUBLIC).withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public ByteCodeAppender appender(Target target) {
                    return (
                        MethodVisitor methodVisitor,
                        Implementation.Context implementationContext,
                        MethodDescription instrumentedMethod
                    ) -> {
                        Label label = new Label();
                        methodVisitor.visitCode();
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, label);
                        methodVisitor.visitInsn(Opcodes.LCONST_1);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitLabel(label);
                        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_1);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "NewFib", "fib", "(I)J");
                        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
                        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
                        methodVisitor.visitInsn(Opcodes.ICONST_2);
                        methodVisitor.visitInsn(Opcodes.ISUB);
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "NewFib", "fib", "(I)J");
                        methodVisitor.visitInsn(Opcodes.LADD);
                        methodVisitor.visitInsn(Opcodes.LRETURN);
                        methodVisitor.visitEnd();
                        return new ByteCodeAppender.Size(5, 2);
                    };
                }
                @Override
                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        // when
        long actual = (long) newFib.getDeclaredMethod("fib", int.class)
            .invoke(newFib.getDeclaredConstructor().newInstance(), 5);

        // then
        assertNotNull(newFib);
        assertEquals(5, actual);
    }

}
