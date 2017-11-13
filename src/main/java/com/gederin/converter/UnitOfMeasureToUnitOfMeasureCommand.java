package com.gederin.converter;

import com.gederin.command.UnitOfMeasureCommand;
import com.gederin.model.UnitOfMeasure;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.Synchronized;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (Objects.isNull(unitOfMeasure)) {
            return null;
        }

        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();

        uomc.setId(unitOfMeasure.getId());
        uomc.setDescription(unitOfMeasure.getDescription());

        return uomc;
    }
}
