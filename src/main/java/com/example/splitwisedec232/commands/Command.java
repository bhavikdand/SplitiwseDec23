package com.example.splitwisedec232.commands;

import com.example.splitwisedec232.exceptions.InvalidCommandException;

public interface Command {

    public void execute(String input) throws InvalidCommandException;
}
