package com.machine_factotry.kursovaya.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DtoConverter {

    public static <T, R> List<R> convert(
            Iterable<T> entity,
            Function<T, R> converter) {

        return StreamSupport.stream(entity.spliterator(), false)
                .map(converter)
                .collect(Collectors.toList());
    }

}
