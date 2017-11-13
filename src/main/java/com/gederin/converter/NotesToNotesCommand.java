package com.gederin.converter;

import com.gederin.command.NotesCommand;
import com.gederin.model.Notes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand>{

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (Objects.isNull(source)){
            return null;
        }

        NotesCommand notesCommand = new NotesCommand();

        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());

        return notesCommand;
    }
}
