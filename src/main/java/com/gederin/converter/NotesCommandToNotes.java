package com.gederin.converter;

import com.gederin.command.NotesCommand;
import com.gederin.model.Notes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.Synchronized;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (Objects.isNull(source)){
            return null;
        }
        Notes notes = new Notes();

        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());

        return notes;
    }
}
