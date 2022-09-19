package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> argList = new ArrayList<>();
        String[] str = signatureString.replaceAll("[\\),]", "").split("\\(");

        if (str.length > 1) {
            String[] methodArg = str[1].split(" ");
            for(int i = 0; i < methodArg.length; i += 2) {
                argList.add(new MethodSignature.Argument(methodArg[i], methodArg[i+1]));
            }
            String[] methodSignature = str[0].split(" ");
            if (methodSignature.length > 2) {
                MethodSignature ms = new MethodSignature(methodSignature[2], argList);
                ms.setAccessModifier(methodSignature[0]);
                ms.setReturnType(methodSignature[1]);
                return ms;
            } else if (methodSignature.length <= 2) {
                MethodSignature ms = new MethodSignature(methodSignature[1], argList);
                ms.setReturnType(methodSignature[0]);
                return ms;
            }
        }
        if (str.length == 1) {
            String[] methodSignature = str[0].split(" ");
            if (methodSignature.length < 3) {
                MethodSignature ms = new MethodSignature(methodSignature[1], argList);
                ms.setReturnType(methodSignature[0]);
                return ms;
            } else if (methodSignature.length >= 3) {
                MethodSignature ms = new MethodSignature(methodSignature[2], argList);
                ms.setReturnType(methodSignature[1]);
                ms.setAccessModifier(methodSignature[0]);
                return ms;
            }
        }
        return null;
    }
}
