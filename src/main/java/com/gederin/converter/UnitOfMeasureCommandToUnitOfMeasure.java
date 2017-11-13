package com.gederin.converter;

import com.gederin.command.UnitOfMeasureCommand;
import com.gederin.model.UnitOfMeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (Objects.isNull(source)) {
            return null;
        }

        UnitOfMeasure uom = new UnitOfMeasure();

        uom.setId(source.getId());
        uom.setDescription(source.getDescription());

        return uom;
    }
}
