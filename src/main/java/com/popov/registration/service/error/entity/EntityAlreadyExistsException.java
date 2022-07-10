package com.popov.registration.service.error.entity;

import com.popov.registration.service.error.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
public class EntityAlreadyExistsException extends ServiceException {

    public EntityAlreadyExistsException(Class clazz, String... searchParamsMap) {
        super(EntityAlreadyExistsException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        log.error(entity + " is already exists with parameters " + searchParams);
        return StringUtils.capitalize(entity) +
                " already exists with parameters " +
                searchParams;
    }

    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
