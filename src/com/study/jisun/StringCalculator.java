package com.study.jisun;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 요구사항
 * 1. 빈 문자열 뚀는 null을 입력할경우 0 반환
 * 2. 쉼표(,) 또는 콜론(;)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분릴한 각 숫자의 합을 반환한다.
 * 3. 기본 구분자 외에 //와 \n사이에 위치하는 문자를 커스텀 구분자로 사용한다.
 * 4. 문자열 계산기에 음수를 전달하는 경우 RuntimeException으로 예외 처리해야힌다.
 */
public class StringCalculator {

    public int add(String text) {

        if(text == null || text.isEmpty()) return 0;

        int result = 0;
        String separator = "";
        String regex = "\\/\\/(?<separator>.)\\/n";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);


        if(matcher.find()) {
            separator = matcher.group("separator");
            text = text.replaceAll(regex,"");
        }

        String separators = ",|:";
        if(!separator.isEmpty())  separators = ",|:|" + separator;

        String[] tokens = text.split(separators);

        for(String token : tokens) {
            if(Pattern.compile(separators).matcher(token).find()) continue;
            int value = Integer.valueOf(token);

            if(value < 0) throw new RuntimeException();

            result += value;
        }

        return result;
    }

}
