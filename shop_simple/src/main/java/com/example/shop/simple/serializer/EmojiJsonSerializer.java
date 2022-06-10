package com.example.shop.simple.serializer;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.SneakyThrows;

/**
 * Author: kevin
 * Date: 2022/6/10
 * Description:
 */
public class EmojiJsonSerializer  extends JsonSerializer<String> {

    @Override
    @SneakyThrows
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) {
        gen.writeString(EmojiUtil.toUnicode(StrUtil.isBlank(value)? "" : value));
    }
}
