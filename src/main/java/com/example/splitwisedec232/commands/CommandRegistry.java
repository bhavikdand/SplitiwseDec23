package com.example.splitwisedec232.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandRegistry {

    private final Map<String, Command> map;

    private CommandRegistry(){
        map = new HashMap<>();
    }

    private static final CommandRegistry INSTANCE = new CommandRegistry();

    public static CommandRegistry getInstance(){
        return INSTANCE;
    }

    public void register(String key, Command command){
        map.put(key, command);
    }

    public Optional<Command> get(String input){
        String[] splits = input.split(" ");
        for(String split: splits){
            if(map.containsKey(split)){
                return Optional.of(map.get(split));
            }
        }
        return Optional.empty();
    }

}
